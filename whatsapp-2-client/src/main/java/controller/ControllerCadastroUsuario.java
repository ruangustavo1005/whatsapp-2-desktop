package controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import message.MessageNewUsuario;
import model.Usuario;
import utils.Connection;
import utils.MD5;
import view.ViewCadastroUsuario;

/**
 * @author Leonardo & Ruan
 */
public class ControllerCadastroUsuario extends ControllerBase<ViewCadastroUsuario> {

    private final Usuario usuario;

    public ControllerCadastroUsuario(String username) {
        this.usuario = new Usuario()
                .setUsername(username);
    }
    
    @Override
    protected ViewCadastroUsuario getInstanceView() {
        return new ViewCadastroUsuario();
    }

    @Override
    protected void addActionListeners(ViewCadastroUsuario view) {
        this.addActionListenerVoltar(view);
        this.addActionListenerCadastrar(view);
    }    

    private void addActionListenerVoltar(ViewCadastroUsuario view) {
        view.getBtnVoltar().addActionListener((e) -> {
            new ControllerLoginInicial(this.usuario.getUsername()).abreTela();
            this.getView().setVisible(false);
        });
    }

    private void addActionListenerCadastrar(ViewCadastroUsuario view) {
        view.getBtnCadastrar().addActionListener((e) -> {
            String nome = this.getView().getTxtNomeCompleto().getText();
            
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this.getView(), "Informe seu nome completo", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                String port = this.getView().getTxtPort().getText();

                if (port.isEmpty()) {
                    JOptionPane.showMessageDialog(this.getView(), "Informe a porta disponível para conexão na sua rede", "Informação", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    String senha = MD5.md5(this.getView().getTxtSenha().getPassword());

                    if (!senha.equals(MD5.md5(this.getView().getTxtConfirmacaoSenha().getPassword()))) {
                        JOptionPane.showMessageDialog(this.getView(), "As senhas não conferem", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        this.usuario.setNome(nome).setSenha(senha).setPorta(Integer.valueOf(port));
                        
                        if (doCadastroUsuario(this.usuario)) {
                            ControllerIndex.getInstance().setUsuarioLogado(this.usuario).abreTela();
                            this.getView().setVisible(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(this.getView(), "Houve um erro não esperado", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }
    
    private boolean doCadastroUsuario(Usuario usuario) {
        boolean retorno = false;
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageNewUsuario messageNewUsuario = new MessageNewUsuario().setUsuario(usuario);
                socket.getOutputStream().write(messageNewUsuario.getMessageBytes());
                
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
    
}