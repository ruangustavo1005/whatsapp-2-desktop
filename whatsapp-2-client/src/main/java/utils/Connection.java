package utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Ruan
 */
public class Connection {

    private int timeout = 3000;

    public Connection() {
        
    }

    public Connection(int timeout) {
        this.timeout = timeout;
    }
    
    public Socket getInstanceSocket() throws IOException {
        Socket socket = null;
        String config = ConfigUtils.getConfig();
        if (config != null && !config.isEmpty()) {
            String[] configs = config.split(":");
            if (configs.length == 2) {
                socket = new Socket();
                socket.connect(new InetSocketAddress(configs[0], Integer.valueOf(configs[1])), this.getTimeout());
            }
        }
        return socket;
    }

    public int getTimeout() {
        return timeout;
    }
    
}