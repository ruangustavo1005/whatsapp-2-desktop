package model;

import java.util.List;

/**
 * @author Leonardo e Ruan
 */
abstract public class Conversa {
    
    private List<Mensagem> mensagens;
    
    public abstract String getTitulo();
    
    public abstract List<Usuario> getUsuariosNotificar();
    
    public void addMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
    }
    
    public List<Mensagem> getMensagens() {
        return this.mensagens;
    }
    
}