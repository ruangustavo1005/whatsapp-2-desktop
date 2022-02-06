package message;

/**
 * @author Leonardo & Ruan
 */
public class MessageGetUsuarios extends MessageSendBase {

    @Override
    protected String getId() {
        return "getUsuarios";
    }

    @Override
    protected String[] getParams() {
        return null;
    }
    
}