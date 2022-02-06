package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageGetConversas extends MessageBase {

    private String username;

    public String getUsername() {
        return username;
    }

    public MessageGetConversas setUsername(String username) {
        this.username = username;
        return this;
    }
    
    @Override
    protected String getId() {
        return "getConversas";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsername()
        };
    }
    
}