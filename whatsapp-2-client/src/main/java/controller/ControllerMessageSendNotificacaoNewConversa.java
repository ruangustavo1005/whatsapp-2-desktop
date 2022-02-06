package controller;

import message.MessageSendNotificacaoNewConversa;

/**
 * @author Leonardo & Ruan
 */
public class ControllerMessageSendNotificacaoNewConversa extends ControllerMessageReceiveBase<MessageSendNotificacaoNewConversa> {

    @Override
    public void execute() {
        ControllerIndex controllerIndex = ControllerIndex.getInstance();
        if (controllerIndex.getView().isVisible()) {
            controllerIndex.getView().getTableModel().addConversa(this.getMessageBase().getConversa());
        }
    }

}