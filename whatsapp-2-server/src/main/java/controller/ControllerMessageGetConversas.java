package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageGetConversas;
import model.Conversa;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageGetConversas extends ControllerMessageBase<MessageGetConversas> {

    @Override
    public void execute() {
        try {
            Dao dao = Dao.getInstance();
            Usuario usuario = dao.getUsuarios().get(this.getMessageBase().getUsername());
            if(usuario != null) {
                HashMap<String, Conversa> daoConversas = dao.getConversas();
                String retorno = "";
                daoConversas.forEach((key, value) -> {
                    for(Usuario user : value.getUsuariosNotificar()) {
                        if(user.getUsername().equals(usuario.getUsername())) {
                            retorno.concat(value.toString() + "\n");
                        }
                    }
                });
                this.write(retorno);
            } else {
                this.write("");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetConversas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}