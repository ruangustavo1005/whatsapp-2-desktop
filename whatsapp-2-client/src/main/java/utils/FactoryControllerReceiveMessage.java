package model;

import controller.ControllerMessageBase;
import java.io.IOException;
import java.net.Socket;
import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 */
public class FactoryControllerClientMessage {
    
    public static ControllerMessageBase getControllerClientMessage(MessageBase classe, Socket connection) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ControllerMessageBase controller =  (ControllerMessageBase) Class.forName("controller.Controller" + classe.getClass().getSimpleName()).newInstance();
        controller.setMessageBase(classe);
        controller.setConnection(connection);
        return controller;
    }
    
}
