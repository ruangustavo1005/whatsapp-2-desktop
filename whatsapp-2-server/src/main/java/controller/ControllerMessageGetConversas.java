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
                        nome = usuario.getUsername().equals(((ConversaPrivada) conversa).getUsuario1().getUsername()) ? ((ConversaPrivada) conversa).getUsuario2().getNome() : ((ConversaPrivada) conversa).getUsuario1().getNome();
                    } else {
                        nome = ((ConversaGrupo) conversa).getTitulo();
                    }
                    for (Usuario user : conversa.getUsuariosNotificar()) {
                        if (user.getUsername().equals(usuario.getUsername())) {
                            int notificacoes = conversa.getNotificacoes().get(user.getUsername());
                            retorno += (conversa.getId() + ";" + nome + ";" + notificacoes + "\n");
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
