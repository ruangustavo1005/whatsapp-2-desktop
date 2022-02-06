package controller;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageNewConversaPrivada;
import model.Conversa;
import model.ConversaPrivada;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageNewConversaPrivada extends ControllerMessageBase<MessageNewConversaPrivada> {

    @Override
    public void execute() {
        try {
            Usuario usuario1 = Dao.getInstance().getUsuarios().get(this.getMessageBase().getUsername1());
            Usuario usuario2 = Dao.getInstance().getUsuarios().get(this.getMessageBase().getUsername2());
            
            if(usuario1 != null && usuario2 != null) {
                if(!this.hasSameConversa(usuario1.getUsername(), usuario2.getUsername())) {
                    UUID uuid = UUID.randomUUID();
                    String uuidAsString = uuid.toString();
                    ConversaPrivada conversaPrivada = new ConversaPrivada(uuidAsString, usuario1, usuario2, 0);
                    Dao.getInstance().getConversas().put(uuidAsString, conversaPrivada);
                    this.write(uuidAsString);
                } else {
                    this.write("0");
                }
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageNewConversaPrivada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean hasSameConversa(String usuario1, String usuario2) {
        for (Conversa conversa : Dao.getInstance().getConversas().values()) {
            if(conversa instanceof ConversaPrivada) {
                if((((ConversaPrivada) conversa).getUsuario1().getUsername().equals(usuario1) && ((ConversaPrivada) conversa).getUsuario2().getUsername().equals(usuario2)) ||
                    (((ConversaPrivada) conversa).getUsuario1().getUsername().equals(usuario2) && ((ConversaPrivada) conversa).getUsuario2().getUsername().equals(usuario1))) {
                    return true;
                }
            }
        }
        return false;
    }
    
}