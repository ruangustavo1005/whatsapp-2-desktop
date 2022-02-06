package message;

import model.Mensagem;

/**
 * @author Leonardo & Ruan
 */
public class MessageSendMensagem extends MessageSendBase {

    private String conversa;
    private Mensagem mensagem;

    public String getConversa() {
        return conversa;
    }

    public MessageSendMensagem setConversa(String conversa) {
        this.conversa = conversa;
        return this;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public MessageSendMensagem setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
        return this;
    }
    
    @Override
    protected String getId() {
        return "sendMensagem";
    }

    @Override
    protected String[] getParams() {
        return new String[] {
            this.getMensagem().getUsuario().getUsername(),
            this.getConversa(),
            this.getMensagem().getDataHoraAsString(),
            this.getMensagem().getMensagem()
        };
    }

}