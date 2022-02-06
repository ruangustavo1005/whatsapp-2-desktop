package message;

import model.Mensagem;
import model.Usuario;

/**
 * @author Leonardo & Ruan
 */
public class MessageSendNotificacaoMensagem extends MessageReceiveBase {

    public String getConversa() {
        return this.getInfo(1);
    }
    
    public Mensagem getMensagem() {
        return new Mensagem()
                .setUsuario(new Usuario()
                        .setNome(this.getInfo(2)))
                .setMensagem(this.getInfo(3))
                .setDataHora(this.getInfo(4));
    }
    
}