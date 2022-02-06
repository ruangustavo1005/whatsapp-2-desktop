package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 * @param <TypeMessage>
 */
abstract public class ControllerMessageBase<TypeMessage extends MessageBase> {
    
    private Socket connection;
    private InputStream input;
    private OutputStream output;
    
    private TypeMessage message;
    
    public void setMessageBase(TypeMessage message) {
        this.message = message;
    }
    
    public TypeMessage getMessageBase() {
        return this.message;
    }
    
    public abstract void execute();

    public Socket getConnection() {
        return connection;
    }

    public void setConnection(Socket connection) throws IOException {
        this.connection = connection;
        this.input = connection.getInputStream();
        this.output = connection.getOutputStream();
    }

    public InputStream getInput() {
        return input;
    }

    public OutputStream getOutput() {
        return output;
    }
    
    public void write(String retorno) throws IOException {
        ControllerApp.getInstance().getInstanceView().addLog(retorno);
        this.output.write(retorno.getBytes());
    }
    
}