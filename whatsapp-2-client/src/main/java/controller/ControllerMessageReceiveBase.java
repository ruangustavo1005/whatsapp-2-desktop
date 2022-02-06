package controller;

import java.io.IOException;
import java.net.Socket;
import message.MessageReceiveBase;

/**
 * @author Leonardo e Ruan
 * @param <TypeMessage>
 */
abstract public class ControllerMessageReceiveBase<TypeMessage extends MessageReceiveBase> {
    
    private Socket connection;
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
    }

}