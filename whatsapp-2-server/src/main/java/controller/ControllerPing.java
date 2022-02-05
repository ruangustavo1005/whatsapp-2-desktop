package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.MessagePing;

/**
 * @author Leonardo e Ruan
 */
public class ControllerPing extends ControllerMessageBase<MessagePing> {

    @Override
    public void execute() {
        try {
            this.write("pong");
        } catch (IOException ex) {
            Logger.getLogger(ControllerPing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
