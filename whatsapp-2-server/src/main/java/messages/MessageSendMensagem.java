package messages;

/**
 * @author Leonardo e Ruan
 */
public class MessageSendMensagem extends MessageBase {   
    
    public String getUsername() {
        return this.getInfo(1);
    }
    
    public String getConversa() {
        return this.getInfo(2);
    }
    
    public String getDataHora() {
        return this.getInfo(3);
    }
    
    public String getConteudoMensagem() {
        return this.getInfo(4);
    }
    
}