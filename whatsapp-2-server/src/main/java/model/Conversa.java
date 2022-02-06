package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo e Ruan
 */
abstract public class Conversa {
    
    private String id;
    private int qtdMensagensNaoLidas;
    private List<Mensagem> mensagens;

    public Conversa(String id, int qtdMensagensNaoLidas) {
        this.id = id;
        this.qtdMensagensNaoLidas = qtdMensagensNaoLidas;
        this.mensagens = new ArrayList<>();
    }
    
    public abstract List<Usuario> getUsuariosNotificar();
    
    public void addMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
    }
    
    public List<Mensagem> getMensagens() {
        return this.mensagens;
    }

    public String getId() {
        return id;
    }
    
    public int getQtdMensagensNaoLidas() {
        return qtdMensagensNaoLidas;
    }

    public void setQtdMensagensNaoLidas(int qtdMensagensNaoLidas) {
        this.qtdMensagensNaoLidas = qtdMensagensNaoLidas;
    }

    public void setId(String id) {
        this.id = id;
    }

}