package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import message.MessageGetConversas;
import model.Conversa;
import model.Usuario;
import utils.Connection;
import view.TableModelConversa;
import view.ViewIndex;

/**
 * @author Leonardo & Ruan
 */
public class ControllerIndex extends ControllerBase<ViewIndex> {

    static private ControllerIndex instance;
    
    private Usuario usuarioLogado;
    private ControllerListenNewMessages controllerListenNewMessages;
    
    private ControllerIndex() {
        
    }
    
    @Override
    protected ViewIndex getInstanceView() {
        return new ViewIndex();
    }

    @Override
    public void abreTela() {
        try {
            if (this.controllerListenNewMessages == null) {
                this.controllerListenNewMessages = new ControllerListenNewMessages(this.getUsuarioLogado().getPorta());
                this.controllerListenNewMessages.start();
            }
        }
        catch (BindException ex) {
            JOptionPane.showMessageDialog(null, "A porta que foi configurada para este usuário já está em uso na sua máquina", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível iniciar o notificador de mensagens novas: ".concat(ex.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        super.abreTela();
    }

    public static synchronized ControllerIndex getInstance() {
        if (ControllerIndex.instance == null) {
            ControllerIndex.instance = new ControllerIndex();
        }
        return instance;
    }

    @Override
    protected void addActionListeners(ViewIndex view) {
        this.addActionListenerLogout(view);
        this.addActionListenerNewConversaPrivada(view);
        this.addActionListenerNewConversaGrupo(view);
        this.addActionListenerAbrirConversa(view);
    }
    
    private void addActionListenerLogout(ViewIndex view) {
        view.getBtnLogout().addActionListener((e) -> {
            this.setUsuarioLogado(null);
            this.controllerListenNewMessages.interromper();
            new ControllerLoginInicial().abreTela();
            this.getView().dispose();
        });
    }
    
    
    private void addActionListenerNewConversaPrivada(ViewIndex view) {
        view.getBtnNewConversaPrivada().addActionListener((e) -> {
            new ControllerCadastroConversaPrivada().abreTela();
            this.getView().dispose();
        });
    }
    
    private void addActionListenerNewConversaGrupo(ViewIndex view) {
        view.getBtnNewConversaGrupo().addActionListener((e) -> {
            new ControllerCadastroConversaGrupo().abreTela();
            this.getView().dispose();
        });
    }
    
    private void addActionListenerAbrirConversa(ViewIndex view) {
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    Conversa conversa = ControllerIndex.getInstance().getView().getTableModel().getConversas().get(row);
                    abreConversa(conversa);
                }
            }
        });
    }
    
    private static void abreConversa(Conversa conversa) {
        ControllerIndex.getInstance().getView().dispose();
        ControllerIndex.getInstance().getView().getTableModel().resetNotificacoesConversa(conversa);
        ControllerConversa.getInstance().setConversa(conversa).abreTela();
    }
    
    private ArrayList<Conversa> getConversas() {
        ArrayList<Conversa> conversas = new ArrayList<>();
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageGetConversas messageGetConversas = new MessageGetConversas().setUsername(this.getUsuarioLogado().getUsername());
                socket.getOutputStream().write(messageGetConversas.getMessageBytes());
                
                InputStream inputStream = socket.getInputStream();
                byte[] dadosBrutos = new byte[1024];
                String response = new String(dadosBrutos, 0, inputStream.read(dadosBrutos));
                
                if (!response.equals("0")) {
                    String[] responseLines = response.split("\n");
                    
                    for (String line : responseLines) {
                        String[] linePieces = line.split(";");
                        
                        conversas.add(new Conversa()
                                .setId(linePieces[0])
                                .setTitulo(linePieces[1]));
                    }
                }
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar conectar com o servidor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return conversas;
    }
    
    public Usuario getUsuarioLogado() {
        if (this.usuarioLogado == null) {
            this.usuarioLogado = new Usuario();
        }
        return this.usuarioLogado;
    }

    public ControllerIndex setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        if (usuarioLogado != null) {
            this.getView().getLabelUsuarioLogado().setText(usuarioLogado.getNome());
            this.getView().getTableModel().clear();
            this.getConversas().forEach(conversa -> this.getView().getTableModel().addConversa(conversa));
        }
        return this;
    }

}