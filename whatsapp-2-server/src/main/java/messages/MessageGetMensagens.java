package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageGetMensagens extends MessageBase {
    
    public String getConversa() {
        return this.getInfo(1);
    }
    
    public String getUsuario() {
        return this.getInfo(2);
    }
    
}