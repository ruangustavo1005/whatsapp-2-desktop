package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageNewUsuario;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageNewUsuario extends ControllerMessageBase<MessageNewUsuario> {

    @Override
    public void execute() {
        try {
            Usuario usuario = new Usuario(
                this.getMessageBase().getUsername(), 
                this.getMessageBase().getPassword(), 
                this.getMessageBase().getMomeCompleto(), 
                this.getConnection().getInetAddress().getHostAddress(), 
                Integer.valueOf(this.getMessageBase().getPort()));

            Dao.getInstance().getUsuarios().put(usuario.getUsername(), usuario);
        
            this.write("1");
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageNewUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}