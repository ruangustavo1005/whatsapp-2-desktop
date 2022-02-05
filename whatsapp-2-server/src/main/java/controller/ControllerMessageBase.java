package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 */
abstract public class ControllerMessageBase<TypeMessage extends MessageBase> {
    
    private Socket connection;
    private InputStream input;
    private OutputStream output;
    
    private TypeMessage message;
    
    public ControllerMessageBase() {
        try {
            this.input = this.connection.getInputStream();
            this.output = this.connection.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(ControllerMessageLoginInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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

    public void setConnection(Socket connection) {
        this.connection = connection;
    }

    public InputStream getInput() {
        return input;
    }

    public OutputStream getOutput() {
        return output;
    }
    
    public void write(String retorno) throws IOException {
        this.output.write(retorno.getBytes());
    }
    
}