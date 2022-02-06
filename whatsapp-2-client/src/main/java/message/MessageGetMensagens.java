package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageGetMensagens extends MessageBase {

    private String username;
    private String conversa;

    public String getUsername() {
        return username;
    }

    public MessageGetMensagens setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getConversa() {
        return conversa;
    }

    public MessageGetMensagens setConversa(String conversa) {
        this.conversa = conversa;
        return this;
    }
    
    @Override
    protected String getId() {
        return "getMensagens";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsername(),
            this.getConversa()
        };
    }

}