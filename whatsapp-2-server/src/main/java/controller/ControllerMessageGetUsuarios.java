package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dao;
import model.MessageGetUsuarios;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageGetUsuarios extends ControllerMessageBase<MessageGetUsuarios> {

    @Override
    public void execute() {
        try {
            String retorno = "";
            for (Usuario usuario : Dao.getInstance().getUsuarios().values()) {
                retorno += usuario.toString() + "\n";
            }
            this.write(retorno);
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}