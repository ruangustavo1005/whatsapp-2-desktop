package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo e Ruan
 */
public class Conversa {
    
    private String id;
    private String titulo;
    private List<Mensagem> mensagens;
    
    public String getId() {
        return this.id;
    }

    public Conversa setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public Conversa setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    
    public List<Mensagem> getMensagens() {
        if (this.mensagens == null) {
            this.mensagens = new ArrayList<>();
        }
        return this.mensagens;
    }

    public Conversa setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
        return this;
    }

    public Conversa addMensagem(Mensagem mensagem) {
        this.mensagens.add(mensagem);
        return this;
    }
    
}