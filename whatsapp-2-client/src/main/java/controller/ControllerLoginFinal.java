package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import message.MessageLoginFinal;
import model.Usuario;
import utils.Connection;
import utils.MD5;
import view.ViewLoginFinal;

/**
 * @author Leonardo & Ruan
 */
public class ControllerLoginFinal extends ControllerBase<ViewLoginFinal> {

    private final Usuario usuario;

    public ControllerLoginFinal(String username) {
        this.usuario = new Usuario()
                .setUsername(username);
    }
    
    @Override
    protected ViewLoginFinal getInstanceView() {
        return new ViewLoginFinal();
    }

    @Override
    protected void addActionListeners(ViewLoginFinal view) {
        this.addActionListenerVoltar(view);
        this.addActionListenerEntrar(view);
    }

    private void addActionListenerVoltar(ViewLoginFinal view) {
        view.getBtnVoltar().addActionListener((e) -> {
            new ControllerLoginInicial(this.usuario.getUsername()).abreTela();
            this.getView().setVisible(false);
        });
    }

    private void addActionListenerEntrar(ViewLoginFinal view) {
        view.getBtnEntrar().addActionListener((e) -> {
            String senha = MD5.md5(this.getView().getTxtSenha().getPassword());
            
            this.usuario.setSenha(senha);
            
            if (this.doLogin(this.usuario)) {
                ControllerIndex.getInstance().setUsuarioLogado(this.usuario).abreTela();
                this.getView().setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(this.getView(), "Senha incorreta", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    private boolean doLogin(Usuario usuario) {
        boolean retorno = false;
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageLoginFinal messageLoginFinal = new MessageLoginFinal().setUsuario(usuario);
                socket.getOutputStream().write(messageLoginFinal.getMessageBytes());
                
                InputStream inputStream = socket.getInputStream();
                byte[] dadosBrutos = new byte[1024];
                String response = new String(dadosBrutos, 0, inputStream.read(dadosBrutos));
                String[] responsePieces = response.split(";");
                retorno = responsePieces[0].equals("1");
                
                if (retorno) {
                    usuario.setNome(responsePieces[1])
                            .setPorta(Integer.valueOf(responsePieces[2]));
                }
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar conectar com o servidor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return retorno;
    }
    
}