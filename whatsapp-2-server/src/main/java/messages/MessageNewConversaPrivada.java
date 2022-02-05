package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageNewConversaPrivada extends MessageBase {
    
    public String getUsername1() {
        return this.getInfo(1);
    }
    
    public String getUsername2() {
        return this.getInfo(2);
    }
    
}