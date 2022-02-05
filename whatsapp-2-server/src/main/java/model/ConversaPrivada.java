package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo e Ruan
 */
public class ConversaPrivada extends Conversa {
    
    private Usuario usuario1;
    private Usuario usuario2;

    @Override
    public String getTitulo() {
        return usuario1.getNome();
    }

    @Override
    public List<Usuario> getUsuariosNotificar() {
        List<Usuario> users = new ArrayList<>();
        users.add(usuario1);
        users.add(usuario2);
        return users;
    }
    
}
