package utils;

import controller.ControllerMessageReceiveBase;
import java.io.IOException;
import java.net.Socket;
import message.MessageReceiveBase;

/**
 * @author Leonardo e Ruan
 */
public class FactoryControllerReceiveMessage {
    
    public static ControllerMessageReceiveBase getControllerClientMessage(MessageReceiveBase classe, Socket connection) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        ControllerMessageReceiveBase controller = (ControllerMessageReceiveBase) Class.forName("controller.Controller" + classe.getClass().getSimpleName()).newInstance();
        controller.setMessageBase(classe);
        controller.setConnection(connection);
        return controller;
    }
    
}
