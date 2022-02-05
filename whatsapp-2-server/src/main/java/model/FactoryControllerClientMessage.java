package model;

import controller.ControllerMessageBase;
import java.net.Socket;
import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 */
public class FactoryControllerClientMessage {
    
    public static ControllerMessageBase getControllerClientMessage(MessageBase classe, Socket connection) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ControllerMessageBase controller =  (ControllerMessageBase) Class.forName("controller.Controller" + MessageBase.class.getName()).newInstance();
        controller.setMessageBase(classe);
        return controller;
    }
    
}
