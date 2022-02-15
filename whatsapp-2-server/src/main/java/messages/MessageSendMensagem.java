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
        String mensagem = this.getInfo(4);
        for (int i = 5; i < this.getInfos().length; i++) {
            mensagem = mensagem.concat(";" + this.getInfo(i));
        }
        return mensagem;
    }
    
}