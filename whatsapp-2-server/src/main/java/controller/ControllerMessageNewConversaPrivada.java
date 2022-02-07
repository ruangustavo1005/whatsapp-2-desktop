package controller;

import java.io.IOException;
import java.util.UUID;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageNewConversaPrivada;
import model.Conversa;
import model.ConversaPrivada;
import model.Dao;
import model.Usuario;
import utils.SocketConnection;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageNewConversaPrivada extends ControllerMessageBase<MessageNewConversaPrivada> {

    @Override
    public void execute() {
        try {
            Usuario usuario1 = Dao.getInstance().getUsuarios().get(this.getMessageBase().getUsername1());
            Usuario usuario2 = Dao.getInstance().getUsuarios().get(this.getMessageBase().getUsername2());
            
            if(usuario1 != null && usuario2 != null) {
                if(!this.hasSameConversa(usuario1.getUsername(), usuario2.getUsername())) {
                    UUID uuid = UUID.randomUUID();
                    String uuidAsString = uuid.toString();
                    ConversaPrivada conversaPrivada = new ConversaPrivada(uuidAsString, usuario1, usuario2, 0);
                    Dao.getInstance().getConversas().put(uuidAsString, conversaPrivada);
                    this.sendNotificacaoConversa(conversaPrivada, usuario2.getUsername());
                    this.write(uuidAsString);
                } else {
                    this.write("0");
                }
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageNewConversaPrivada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean hasSameConversa(String usuario1, String usuario2) {
        for (Conversa conversa : Dao.getInstance().getConversas().values()) {
            if(conversa instanceof ConversaPrivada) {
                if((((ConversaPrivada) conversa).getUsuario1().getUsername().equals(usuario1) && ((ConversaPrivada) conversa).getUsuario2().getUsername().equals(usuario2)) ||
                    (((ConversaPrivada) conversa).getUsuario1().getUsername().equals(usuario2) && ((ConversaPrivada) conversa).getUsuario2().getUsername().equals(usuario1))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void sendNotificacaoConversa(ConversaPrivada conversa, String usuarioNaoCriador) throws IOException {
        for(Usuario usuario : conversa.getUsuariosNotificar()) {
            if(usuario.getUsername().equals(usuarioNaoCriador)) {
                try (Socket socket = SocketConnection.createSocketInstance(usuario.getIp(), usuario.getPorta())) {
                    String nome = usuario.getUsername().equals(((ConversaPrivada) conversa).getUsuario1().getUsername()) ? ((ConversaPrivada) conversa).getUsuario2().getNome() : ((ConversaPrivada) conversa).getUsuario1().getNome();
                    String retorno = "sendNotificacaoNewConversa;" + conversa.getId() + ";" + nome;
                    socket.getOutputStream().write(retorno.getBytes());
                }
            }
        }
    }
    
}