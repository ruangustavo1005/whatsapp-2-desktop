package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageGetConversas extends MessageBase {
    
    public String getUsername() {
        return this.getInfo(1);
    }
    
}