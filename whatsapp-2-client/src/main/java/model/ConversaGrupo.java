package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leonardo e Ruan
 */
public class ConversaGrupo extends Conversa {
    
    private Map<String, Usuario> usuarios;
    
    public Map<String, Usuario> getUsuarios() {
        if (this.usuarios == null) {
            this.usuarios = new HashMap<>();
        }
        return this.usuarios;
    }

    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void addUsuario(Usuario usuario) {
        this.getUsuarios().put(usuario.getUsername(), usuario);
    }
    
}
