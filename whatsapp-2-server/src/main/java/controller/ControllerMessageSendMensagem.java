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
                Mensagem mensagem = new Mensagem(usuario, this.getMessageBase().getConteudoMensagem(), DateUtils.stringToDate(this.getMessageBase().getDataHora()));
                conversa.addMensagem(mensagem);
                this.sendNotificacaoMensagem(conversa, mensagem);
                this.write("1");
            } else {
                this.write("0");
            }
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageSendMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendNotificacaoMensagem(Conversa conversa, Mensagem mensagem) throws IOException {
        for (Usuario usuario : conversa.getUsuariosNotificar()) {
            try (Socket socket = this.createSocketInstance(usuario.getIp(), usuario.getPorta())) {
                String retorno = "sendNotificacaoMensagem;" + conversa.getId() + ";" + mensagem.toString();
                socket.getOutputStream().write(retorno.getBytes());
            }
        }
    }

    private Socket createSocketInstance(String ip, int porta) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, porta), 3000);
        return socket;
    }

}
