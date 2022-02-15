package message;

import java.util.ArrayList;
import java.util.Arrays;
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
                .setDataHora(this.getInfo(3))
                .setMensagem(new ArrayList<>(Arrays.asList(this.getInfos()))
                        .subList(4, this.getInfos().length)
                        .stream()
                        .reduce((String t, String u) -> {
                            return t == null ? u : (u == null ? t : t.concat(";").concat(u));
                        })
                        .get());
    }
    
}