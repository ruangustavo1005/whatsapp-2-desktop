package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageGetMensagens extends MessageSendBase {

    private String conversa;
    private String usuario;

    public String getConversa() {
        return conversa;
    }

    public MessageGetMensagens setConversa(String conversa) {
        this.conversa = conversa;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public MessageGetMensagens setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }
    
    @Override
    protected String getId() {
        return "getMensagens";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getConversa(),
            this.getUsuario()
        };
    }

}