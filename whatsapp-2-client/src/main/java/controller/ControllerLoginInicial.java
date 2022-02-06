package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import message.MessageLoginInicial;
import utils.Connection;
import view.ViewLoginInicial;

/**
 * @author Leonardo & Ruan
 */
public class ControllerLoginInicial extends ControllerBase<ViewLoginInicial> {

    public ControllerLoginInicial() {
        
    }
    
    public ControllerLoginInicial(String username) {
        this.getView().getTxtUsername().setText(username);
    }
    
    @Override
    protected ViewLoginInicial getInstanceView() {
        return new ViewLoginInicial();
    }

    @Override
    protected void addActionListeners(ViewLoginInicial view) {
        this.addActionListenerContinuar(view);
        this.addActionListenerConfigurarServidor(view);
    }

    private void addActionListenerContinuar(ViewLoginInicial view) {
        view.getBtnContinuar().addActionListener((e) -> {
            String username = this.getView().getTxtUsername().getText();
            
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(this.getView(), "Informe um nome de usuário", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                if (this.isUsuarioCadastrado(username)) {
                    new ControllerLoginFinal(username).abreTela();
                }
                else {
                    new ControllerCadastroUsuario(username).abreTela();
                }
                this.getView().dispose();
            }
        });
    }

    private boolean isUsuarioCadastrado(String username) {
        boolean retorno = false;
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageLoginInicial messageLoginInicial = new MessageLoginInicial().setUsername(username);
                socket.getOutputStream().write(messageLoginInicial.getMessageBytes());
                
                InputStream inputStream = socket.getInputStream();
                byte[] dadosBrutos = new byte[1024];
                String response = new String(dadosBrutos, 0, inputStream.read(dadosBrutos));
                retorno = response.equals("1");
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar conectar com o servidor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return retorno;
    }
    
    private void addActionListenerConfigurarServidor(ViewLoginInicial view) {
        view.getBtnConfigurarServidor().addActionListener((e) -> {
            new ControllerConfiguracoesServidor().abreTela();
        });
    }

}