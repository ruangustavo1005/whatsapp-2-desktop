package messages;

import java.util.ArrayList;

/**
 * @author Leonardo e Ruan
 */
public class MessageNewConversaGrupo extends MessageBase {
    
    public String getUserName() {
        return this.getInfo(1);
    }
    
    public String getTitulo() {
        return this.getInfo(2);
    }
    
    public ArrayList<String> getUsuarios() {
        ArrayList<String> usuarios = new ArrayList<>();
        for(int i = 3; i < this.getInfos().length; i++) {
            usuarios.add(this.getInfo(i));
        }
        return usuarios;
    }
    
}