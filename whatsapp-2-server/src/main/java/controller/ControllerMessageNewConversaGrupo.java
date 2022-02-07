package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageNewConversaGrupo;
import model.Conversa;
import model.ConversaGrupo;
import model.Dao;
import model.Usuario;
import utils.SocketConnection;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageNewConversaGrupo extends ControllerMessageBase<MessageNewConversaGrupo> {

    @Override
    public void execute() {
        try {
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Dao dao = Dao.getInstance();
            Usuario usuarioCriador = dao.getUsuarios().get(this.getMessageBase().getUserName());
            ArrayList<Usuario> usuarios = new ArrayList<>();
            if(usuarioCriador != null) {
                this.getMessageBase().getUsuarios().forEach((String user) -> {
                    Usuario usuarioGroup = dao.getUsuarios().get(user);
                    if (usuarioGroup != null) {
                        usuarios.add(usuarioGroup);
                    }
                });
                usuarios.add(usuarioCriador);
                ConversaGrupo conversaGrupo = new ConversaGrupo(uuidAsString, this.getMessageBase().getTitulo(), usuarios, 0);
                dao.getConversas().put(uuidAsString, conversaGrupo);
                this.sendNotificacaoConversa(conversaGrupo, usuarioCriador.getUsername());
                this.write(uuidAsString);
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageNewConversaGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void sendNotificacaoConversa(ConversaGrupo conversaGrupo, String usuarioCriador) throws IOException {
        for(Usuario usuario : conversaGrupo.getUsuariosNotificar()) {
            if(!usuario.getUsername().equals(usuarioCriador)) {
                try (Socket socket = SocketConnection.createSocketInstance(usuario.getIp(), usuario.getPorta())) {
                    String retorno = "sendNotificacaoNewConversa;" + conversaGrupo.getId() + ";" + conversaGrupo.getTitulo();
                    socket.getOutputStream().write(retorno.getBytes());
                }
            }
        }
    }

}