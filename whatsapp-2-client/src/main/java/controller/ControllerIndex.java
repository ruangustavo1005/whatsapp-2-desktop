package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import message.MessageGetConversas;
import model.Conversa;
import model.Usuario;
import utils.Connection;
import view.ViewIndex;

/**
 * @author Leonardo & Ruan
 */
public class ControllerIndex extends ControllerBase<ViewIndex> {

    static private ControllerIndex instance;
    
    private Usuario usuarioLogado;
    
    private ControllerIndex() {
        
    }
    
    @Override
    protected ViewIndex getInstanceView() {
        return new ViewIndex(this.getConversas());
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
    }
    
    private void addActionListenerLogout(ViewIndex view) {
        view.getBtnLogout().addActionListener((e) -> {
            this.setUsuarioLogado(null);
            new ControllerLoginInicial().abreTela();
            this.getView().setVisible(false);
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
            this.getView().setVisible(false);
        });
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
                
                if (!response.isEmpty()) {
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
        this.getView().getLabelUsuarioLogado().setText(usuarioLogado.getNome());
        return this;
    }

}