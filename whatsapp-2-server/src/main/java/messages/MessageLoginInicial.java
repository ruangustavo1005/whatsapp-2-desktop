package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageLoginInicial extends MessageBase {
    
    public String getUsername() {
        return this.getInfo(1);
    }
    
}