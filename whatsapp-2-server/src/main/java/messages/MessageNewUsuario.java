package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageNewUsuario extends MessageBase {
    
    public String getUsername() {
        return this.getInfo(1);
    }
    
    public String getMomeCompleto() {
        return this.getInfo(2);
    }
    
    public String getPassword() {
        return this.getInfo(3);
    }
    
    public String getPort() {
        return this.getInfo(4);
    }
    
}