package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageLoginFinal extends MessageBase {
    
    public String getUsername() {
        return this.getInfo(1);
    }
    
    public String getPassword() {
        return this.getInfo(2);
    }
    
}