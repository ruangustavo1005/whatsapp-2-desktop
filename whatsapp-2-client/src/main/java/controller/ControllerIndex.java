package controller;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
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
        if (this.controllerListenNewMessages == null) {
            try {
                this.controllerListenNewMessages = new ControllerListenNewMessages(this.getUsuarioLogado().getPorta());
                new Thread(this.controllerListenNewMessages).start();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível iniciar o notificador de mensagens novas: ".concat(ex.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        this.getView().getTableModel().clear();
        this.getConversas().forEach(conversa -> this.getView().getTableModel().addConversa(conversa));
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
            this.controllerListenNewMessages.stop();
            this.controllerListenNewMessages = null;
            new ControllerLoginInicial().abreTela();
            this.getView().dispose();
        });
    }
    
    
    private void addActionListenerNewConversaPrivada(ViewIndex view) {
        view.getBtnNewConversaPrivada().addActionListener((e) -> {
            new ControllerCadastroConversaPrivada().abreTela();
        });
    }
    
    private void addActionListenerNewConversaGrupo(ViewIndex view) {
        view.getBtnNewConversaGrupo().addActionListener((e) -> {
            new ControllerCadastroConversaGrupo().abreTela();
            this.getView().dispose();
        });
    }
    
    private void addActionListenerAbrirConversa(ViewIndex view) {
        view.getBtnAbrirConversa().addActionListener((e) -> {
            JTable table = this.getView().getTable();
            
            if (table.getSelectedRowCount() == 1) {
                TableModelConversa tableModelConversa = this.getView().getTableModel();
                Conversa conversaSelecionada = tableModelConversa.getConversas().get(table.getSelectedRow());
                
                abreConversa(conversaSelecionada);
            }
        });
        
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
        }
        return this;
    }

}