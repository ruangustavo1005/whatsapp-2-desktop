package controller;

import message.MessageSendNotificacaoMensagem;
import model.Conversa;

/**
 * @author Leonardo & Ruan
 */
public class ControllerMessageSendNotificacaoMensagem extends ControllerMessageReceiveBase<MessageSendNotificacaoMensagem> {

    @Override
    public void execute() {
        ControllerConversa controllerConversa = ControllerConversa.getInstance();
        if (controllerConversa.getView().isVisible()
         && controllerConversa.getConversa().getId().equals(this.getMessageBase().getConversa())) {
            controllerConversa.appendMensagem(this.getMessageBase().getMensagem());
        }
        else {
            ControllerIndex controllerIndex = ControllerIndex.getInstance();
            controllerIndex.getView()
                    .getTableModel()
                    .addNotificacaoMensagemNova(new Conversa()
                            .setId(this.getMessageBase().getConversa()));
        }
    }

}