package message;

import model.Usuario;

/**
 * @author Leonardo & Ruan
 */
public class MessageLoginFinal extends MessageSendBase {

    private Usuario usuario;

    public Usuario getUsuario() {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        return this.usuario;
    }

    public MessageLoginFinal setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }
    
    @Override
    protected String getId() {
        return "loginFinal";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsuario().getUsername(),
            this.getUsuario().getSenha()
        };
    }

}