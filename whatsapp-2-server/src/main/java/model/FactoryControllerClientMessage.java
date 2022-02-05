package model;

import controller.ControllerMessageBase;
import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 */
public class FactoryControllerClientMessage {
    
    public static ControllerMessageBase getControllerClientMessage(MessageBase classe) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ControllerMessageBase controller =  (ControllerMessageBase) Class.forName("controller.Controller" + MessageBase.class.getName()).newInstance();
        controller.setMessageBase(classe);
        return controller;
    }
    
}
