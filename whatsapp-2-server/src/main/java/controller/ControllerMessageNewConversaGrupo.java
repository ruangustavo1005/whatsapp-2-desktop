package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageNewConversaGrupo;
import model.ConversaGrupo;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageNewConversaGrupo extends ControllerMessageBase<MessageNewConversaGrupo> {

    @Override
    public void execute() {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Dao dao = Dao.getInstance();
            Usuario usuario = dao.getUsuarios().get(this.getMessageBase().getUserName());
            ArrayList<Usuario> usuarios = new ArrayList<>();
            if(usuario != null) {
                this.getMessageBase().getUsuarios().forEach((String user) -> {
                    Usuario usuarioGroup = dao.getUsuarios().get(user);
                    if (usuarioGroup != null) {
                        usuarios.add(usuarioGroup);
                    }
                });
                usuarios.add(usuario);
                ConversaGrupo conversaGrupo = new ConversaGrupo(uuidAsString, this.getMessageBase().getTitulo(), usuarios, 0);
                dao.getConversas().put(uuidAsString, conversaGrupo);
                this.write(uuidAsString);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageNewConversaGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}