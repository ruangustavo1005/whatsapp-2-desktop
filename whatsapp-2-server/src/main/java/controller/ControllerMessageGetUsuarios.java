package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dao;
import messages.MessageGetUsuarios;
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
                retorno = retorno + usuario.toString() + "\n";
            }
            if(!retorno.isEmpty()) {
                this.write(retorno);
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}