package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageNewConversaPrivada extends MessageSendBase {

    private String username1;
    private String username2;

    public String getUsername1() {
        return username1;
    }

    public MessageNewConversaPrivada setUsername1(String username1) {
        this.username1 = username1;
        return this;
    }

    public String getUsername2() {
        return username2;
    }

    public MessageNewConversaPrivada setUsername2(String username2) {
        this.username2 = username2;
        return this;
    }
    
    @Override
    protected String getId() {
        return "newConversaPrivada";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsername1(),
            this.getUsername2(),
        };
    }

}