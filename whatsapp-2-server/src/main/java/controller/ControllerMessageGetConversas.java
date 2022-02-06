package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageGetConversas;
import model.Conversa;
import model.ConversaGrupo;
import model.ConversaPrivada;
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
            if (usuario != null) {
                String retorno = "";
                for (Conversa conversa : dao.getConversas().values()) {
                    String nome;
                    if (conversa instanceof ConversaPrivada) {
                        nome = usuario.getUsername().equals(((ConversaPrivada) conversa).getUsuario1().getUsername()) ? ((ConversaPrivada) conversa).getUsuario2().getUsername() : ((ConversaPrivada) conversa).getUsuario1().getUsername();
                    } else {
                        nome = ((ConversaGrupo) conversa).getTitulo();
                    }
                    for (Usuario user : conversa.getUsuariosNotificar()) {
                        if (user.getUsername().equals(usuario.getUsername())) {
                            retorno += (conversa.getId() + ";" + nome + "\n");
                        }
                    }
                }
                if (!retorno.isEmpty()) {
                    this.write(retorno);
                } else {
                    this.write("0");
                }
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageGetConversas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
