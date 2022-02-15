package controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageSendMensagem;
import model.Conversa;
import model.Dao;
import model.Mensagem;
import model.Usuario;
import utils.DateUtils;
import utils.SocketConnection;

/**
 * @author Leonardo e Ruan
 */
public class ControllerMessageSendMensagem extends ControllerMessageBase<MessageSendMensagem> {

    @Override
    public void execute() {
        try {
            Dao dao = Dao.getInstance();

            Usuario usuario = dao.getUsuarios().get(this.getMessageBase().getUsername());
            Conversa conversa = dao.getConversas().get(this.getMessageBase().getConversa());
            if (usuario != null && conversa != null) {
                Mensagem mensagem = new Mensagem(usuario, this.getMessageBase().getConteudoMensagem(), DateUtils.stringToDateHour(this.getMessageBase().getDataHora()));
                conversa.addMensagem(mensagem);
                this.sendNotificacaoMensagem(conversa, mensagem, usuario);
                this.write("1");
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageSendMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendNotificacaoMensagem(Conversa conversa, Mensagem mensagem, Usuario usuarioLogado) throws IOException {
        for (Usuario usuario : conversa.getUsuariosNotificar()) {
            if(!usuario.getUsername().equals(usuarioLogado.getUsername())) {
                int notificacoes = conversa.getNotificacoes().get(usuario.getUsername());
                conversa.getNotificacoes().put(usuario.getUsername(), notificacoes + 1);
                try (Socket socket = SocketConnection.createSocketInstance(usuario.getIp(), usuario.getPorta())) {
                    String retorno = "sendNotificacaoMensagem;" + conversa.getId() + ";" + mensagem.toString();
                    socket.getOutputStream().write(retorno.getBytes());
                }
            }
        }
    }

}
