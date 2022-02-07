package utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Leonardo e Ruan
 */
public class SocketConnection {
    
    public static Socket createSocketInstance(String ip, int porta) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, porta), 3000);
        return socket;
    }
    
}