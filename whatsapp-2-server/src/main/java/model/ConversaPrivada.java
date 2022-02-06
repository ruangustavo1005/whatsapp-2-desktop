package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leonardo e Ruan
 */
public class ConversaPrivada extends Conversa {
    
    private Usuario usuario1;
    private Usuario usuario2;

    public ConversaPrivada(String id, Usuario usuario1, Usuario usuario2, int qtdMensagensNaoLidas) {
        super(id, qtdMensagensNaoLidas);
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
    }
    
    @Override
    public List<Usuario> getUsuariosNotificar() {
        List<Usuario> users = new ArrayList<>();
        users.add(usuario1);
        users.add(usuario2);
        return users;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }
    
}
