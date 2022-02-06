package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageGetMensagens extends MessageSendBase {

    private String conversa;

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
            this.getConversa()
        };
    }

}