package message;

/**
 * @author Leonardo & Ruan
 */
abstract public class MessageBase {

    abstract protected String getId();
    
    abstract protected String[] getParams();
    
    public byte[] getMessageBytes() {
        String joinedParams;
        
        try {
            joinedParams = String.join(";", this.getParams());
        }
        catch (NullPointerException exception) {
            joinedParams = "";
        }
        
        if (!joinedParams.isEmpty()) {
            joinedParams = ";".concat(joinedParams);
        }
        
        return this.getId().concat(joinedParams).getBytes();
    }
    
}