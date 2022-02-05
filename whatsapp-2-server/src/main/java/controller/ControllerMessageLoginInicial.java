package controller;

import messages.MessageLoginInicial;
import model.Dao;
import model.Usuario;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageLoginInicial extends ControllerMessageBase<MessageLoginInicial> {
    
    @Override
    public String execute() {
        String username = this.geMessageBase().getUsername();
        Usuario usuario = Dao.getInstance().getUsuarios().get(username);
        if(usuario != null) {
            return "1";
        }
        return "0";
    }

}