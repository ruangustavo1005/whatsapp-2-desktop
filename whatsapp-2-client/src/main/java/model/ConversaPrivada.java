package model;

/**
 * @author Leonardo e Ruan
 */
public class ConversaPrivada extends Conversa {
    
    private Usuario usuario1;
    private Usuario usuario2;

    public Usuario getUsuario1() {
        if (this.usuario1 == null) {
            this.usuario1 = new Usuario();
        }
        return this.usuario1;
    }

    public ConversaPrivada setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
        return this;
    }

    public Usuario getUsuario2() {
        if (this.usuario2 == null) {
            this.usuario2 = new Usuario();
        }
        return this.usuario2;
    }

    public ConversaPrivada setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
        return this;
    }

}
