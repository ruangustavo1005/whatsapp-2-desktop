package utils;

import controller.ControllerApp;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Leonardo e Ruan
 */
public class SocketConnection {
    
    public static Socket createSocketInstance(String ip, int porta) throws IOException {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip, porta), 1000);
        }
        catch (ConnectException exception) {
            ControllerApp.getInstance().getInstanceView().addLog("Não foi possível conectar ao host " + ip + ":" + porta);
        }
        return socket;
    }
    
}