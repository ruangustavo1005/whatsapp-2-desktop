package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import message.MessageGetMensagens;
import message.MessageSendMensagem;
import model.Conversa;
import model.Mensagem;
import model.Usuario;
import utils.Connection;
import view.ViewConversa;

/**
 * @author Leonardo & Ruan
 */
public class ControllerConversa extends ControllerBase<ViewConversa>{

    static private ControllerConversa instance;
    
    private Conversa conversa;
    private ControllerListenNewMessages controllerListenNewMessages;
    
    private ControllerConversa() {
        
    }

    public static synchronized ControllerConversa getInstance() {
        if (ControllerConversa.instance == null) {
            ControllerConversa.instance = new ControllerConversa();
        }
        return ControllerConversa.instance;
    }
    
    @Override
    protected ViewConversa getInstanceView() {
        return new ViewConversa();
    }

    @Override
    public void abreTela() {
        try {
            this.controllerListenNewMessages = new ControllerListenNewMessages(ControllerIndex.getInstance().getUsuarioLogado().getPorta());
            new Thread(this.controllerListenNewMessages).start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível iniciar o notificador de mensagens novas: ".concat(ex.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        this.getView().getTxtHistorico().setText(this.getMensagens(this.getConversa()));
        this.getView().setTitle(conversa.getTitulo());
        super.abreTela();
    }

    @Override
    protected void addActionListeners(ViewConversa view) {
        this.addActionListenerEnviar(view);
        this.addActionListenersWindow(view);
    }
    
    public Conversa getConversa() {
        return conversa;
    }

    public ControllerConversa setConversa(Conversa conversa) {
        this.conversa = conversa;
        return this;
    }

    private void addActionListenerEnviar(ViewConversa view) {
        view.getBtnEnviar().addActionListener((e) -> {
            String texto = this.getView().getTxtMensagem().getText();
            
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this.getView(), "Digite uma mensagem", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                Mensagem mensagem = new Mensagem()
                        .setUsuario(ControllerIndex.getInstance().getUsuarioLogado())
                        .setDataHora(new Date())
                        .setMensagem(texto);
                
                if (this.sendMessage(mensagem, this.getConversa())) {
                    this.appendMensagem(mensagem);
                    this.getView().getTxtMensagem().setText("");
                }
                else {
                    JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar enviar sua mensagem", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void appendMensagem(Mensagem mensagem) {
        if (!this.getView().getTxtHistorico().getText().isEmpty()) {
            this.getView().getTxtHistorico().append("\n\n");
        }
        this.getView().getTxtHistorico().append(mensagem.toString());
    }

    public ControllerListenNewMessages getControllerListenNewMessages() {
        return controllerListenNewMessages;
    }
    
    private void addActionListenersWindow(ViewConversa view) {
        view.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                ControllerConversa.getInstance().getControllerListenNewMessages().stop();
                ControllerIndex.getInstance().abreTela();
                ControllerConversa.getInstance().getView().dispose();
            }

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    private String getMensagens(Conversa conversa) {
        ArrayList<String> mensagens = new ArrayList<>();
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageGetMensagens messageGetMensagens = new MessageGetMensagens()
                        .setConversa(conversa.getId());
                socket.getOutputStream().write(messageGetMensagens.getMessageBytes());
                
                InputStream inputStream = socket.getInputStream();
                byte[] dadosBrutos = new byte[1024];
                String response = new String(dadosBrutos, 0, inputStream.read(dadosBrutos));
                
                if (!response.equals("0")) {
                    String[] responseLines = response.split("\n");
                    
                    for (String line : responseLines) {
                        String[] linePieces = line.split(";");
                        
                        mensagens.add(new Mensagem()
                                .setDataHora(linePieces[2])
                                .setUsuario(new Usuario()
                                        .setNome(linePieces[0]))
                                .setMensagem(linePieces[1])
                                .toString());
                    }
                }
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar conectar com o servidor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return String.join("\n\n", mensagens);
    }

    private boolean sendMessage(Mensagem mensagem, Conversa conversa) {
        boolean retorno = false;
        
        try {
            try (Socket socket = (new Connection()).getInstanceSocket()) {
                MessageSendMensagem messageSendMensagem = new MessageSendMensagem()
                        .setConversa(conversa.getId())
                        .setMensagem(mensagem);
                socket.getOutputStream().write(messageSendMensagem.getMessageBytes());
                
                InputStream inputStream = socket.getInputStream();
                byte[] dadosBrutos = new byte[1024];
                String response = new String(dadosBrutos, 0, inputStream.read(dadosBrutos));
                retorno = response.equals("1");
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(this.getView(), "Houve um erro ao tentar conectar com o servidor", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        
        return retorno;
    }

}