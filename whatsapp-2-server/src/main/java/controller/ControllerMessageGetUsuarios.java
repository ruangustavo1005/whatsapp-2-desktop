package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dao;
import model.MessageGetUsuarios;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageGetUsuarios extends ControllerMessageBase<MessageGetUsuarios> {

    @Override
    public void execute() {
        String retorno = "";
        Dao.getInstance().getUsuarios().forEach((key, value) -> {
            retorno.concat(value.toString() + "\n");
        });
        try {
            this.write(retorno);
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}