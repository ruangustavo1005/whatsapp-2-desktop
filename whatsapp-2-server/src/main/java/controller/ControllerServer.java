package controller;

import model.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageBase;
import view.TelaServer;

public class ControllerServer extends Thread  {
    
    private ServerSocket server;
    private InputStream input;
    private OutputStream out;
    
    public void initConnection(int port) throws IOException, Exception {
        this.server = new ServerSocket(port);
        server.setReuseAddress(true);
        this.start();
    }

    @Override
    public void run() {
        while (true) {            
            TelaServer.getInstance().addLog("Aguardando conexão");
            try {
                try (Socket conn = server.accept()) {
                    TelaServer.getInstance().addLog("Conectado com: " + conn.getInetAddress().getHostAddress() + ":" + conn.getLocalPort());
                    this.out = conn.getOutputStream();
                    this.input = conn.getInputStream();
                    byte[] dadosBrutos = new byte[1024];
                    int dadosLidos = input.read(dadosBrutos);
                    if(dadosLidos >= 0)  {
                        String dadosStr = new String(dadosBrutos, 0, dadosLidos);
                        TelaServer.getInstance().addLog(dadosStr);
                        MessageBase mensagem = FactoryClientMessage.getClientMessage(dadosStr);
                        ControllerMessageBase controller = FactoryControllerClientMessage.getControllerClientMessage(mensagem, conn);
                        controller.execute();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ControllerServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControllerServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void throwMessage(String message) throws IOException {
        out.write(message.getBytes());
        TelaServer.getInstance().addLog(message);
    }
    
}