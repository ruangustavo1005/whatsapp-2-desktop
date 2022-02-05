package controller;

import messages.MessageBase;

/**
 * @author Leonardo e Ruan
 */
abstract public class ControllerMessageBase<TypeMessage extends MessageBase> {
    
    private TypeMessage message;
    
    public void setMessageBase(TypeMessage message) {
        this.message = message;
    }
    
    public TypeMessage geMessageBase() {
        return this.message;
    }
    
    public abstract String execute();
    
}