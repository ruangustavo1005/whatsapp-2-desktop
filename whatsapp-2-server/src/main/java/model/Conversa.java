package model;

import java.util.List;

/**
 * @author Leonardo e Ruan
 */
abstract public class Conversa {
    
    private String id;
    
    private int qtdMensagensNaoLidas;
    
    private List<Mensagem> mensagens;
    
    public abstract String getTitulo();
    
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

    @Override
    public String toString() {
        return this.id + ";" + this.getTitulo();
    }
    
}