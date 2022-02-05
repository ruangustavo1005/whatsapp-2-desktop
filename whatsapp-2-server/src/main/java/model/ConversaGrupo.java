package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo e Ruan
 */
public class ConversaGrupo extends Conversa {
    
    private String nomeConversa;
    
    private List<Usuario> usuarios;
    
    public ConversaGrupo() {
        this.usuarios = new ArrayList<>();
    }

    @Override
    public String getTitulo() {
        return this.nomeConversa;
    }

    @Override
    public List<Usuario> getUsuariosNotificar() {
        return usuarios;
    }
    
}
