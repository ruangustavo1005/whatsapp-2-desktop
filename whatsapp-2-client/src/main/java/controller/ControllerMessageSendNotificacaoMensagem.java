package controller;

import message.MessageSendNotificacaoMensagem;

/**
 * @author Leonardo & Ruan
 */
public class ControllerMessageSendNotificacaoMensagem extends ControllerMessageReceiveBase<MessageSendNotificacaoMensagem> {

    @Override
    public void execute() {
        ControllerConversa controllerConversa = ControllerConversa.getInstance();
        if (controllerConversa.getView().isVisible()) {
            if (controllerConversa.getConversa().getId().equals(this.getMessageBase().getConversa())) {
                controllerConversa.appendMensagem(this.getMessageBase().getMensagem());
            }
        }
    }

}