package utils;

import message.MessageReceiveBase;

public class FactoryReceiveMessage {
    
    public static MessageReceiveBase getClientMessage(String mensagem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String[] infos = mensagem.split(";");
        String identificador = infos[0];
        
        MessageReceiveBase message = (MessageReceiveBase) Class.forName("message.Message" + identificador.substring(0, 1).toUpperCase() + identificador.substring(1)).newInstance();
        message.setInfos(infos);
        return message;
    }
    
}
