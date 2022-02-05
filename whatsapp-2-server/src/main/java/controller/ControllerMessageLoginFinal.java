package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageLoginFinal;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageLoginFinal extends ControllerMessageBase<MessageLoginFinal> {

    
    @Override
    public void execute() {
        try {
            Usuario usuario = Dao.getInstance().getUsuarios().get(this.getMessageBase().getUsername());
            if(this.getMessageBase().getPassword().equals(usuario.getSenha())) {
                this.write("1;" + usuario.getNome());
            }
            this.write("0");
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageLoginFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}