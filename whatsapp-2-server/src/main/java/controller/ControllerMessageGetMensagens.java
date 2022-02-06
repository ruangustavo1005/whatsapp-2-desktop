package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageGetMensagens;
import model.Conversa;
import model.Dao;
import model.Mensagem;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageGetMensagens extends ControllerMessageBase<MessageGetMensagens> {

    @Override
    public void execute() {
        try {
            Dao dao = Dao.getInstance();
            Conversa conversa = dao.getConversas().get(this.getMessageBase().getConversa());
            String retorno = "";
            if(conversa != null) {
                for(Mensagem mensagem : conversa.getMensagens()) {
                    retorno += (mensagem.toString() + "\n");
                }
                if(!retorno.isEmpty()) {
                    this.write(retorno);
                } else {
                    this.write("0");
                }
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetMensagens.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}