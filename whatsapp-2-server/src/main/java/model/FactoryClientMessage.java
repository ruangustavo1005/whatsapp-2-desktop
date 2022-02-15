package model;

import messages.MessageBase;

public class FactoryClientMessage {
    
    public static MessageBase getClientMessage(String mensagem) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(mensagem.endsWith(";")) {
            mensagem = mensagem.concat(" ");
        }
        String[] infos = mensagem.split(";");
        String identificador = infos[0];
        
        MessageBase message = (MessageBase) Class.forName("messages.Message" + identificador.substring(0, 1).toUpperCase() + identificador.substring(1)).newInstance();
        message.setInfos(infos);
        return message;
    }
    
}
