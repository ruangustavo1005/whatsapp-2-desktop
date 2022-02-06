package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageLoginInicial extends MessageSendBase {

    private String username;

    public String getUsername() {
        return username;
    }

    public MessageLoginInicial setUsername(String username) {
        this.username = username;
        return this;
    }
    
    @Override
    protected String getId() {
        return "loginInicial";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsername()
        };
    }

}