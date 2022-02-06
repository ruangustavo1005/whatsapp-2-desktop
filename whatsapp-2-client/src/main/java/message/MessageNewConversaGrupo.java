package message;

import java.util.ArrayList;

/**
 * @author Leonardo & Ruan
 */
public class MessageNewConversaGrupo extends MessageSendBase {

    private String username;
    private String titulo;
    private ArrayList<String> usuarios;

    public String getUsername() {
        return username;
    }

    public MessageNewConversaGrupo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public MessageNewConversaGrupo setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public MessageNewConversaGrupo setUsuarios(ArrayList<String> usuarios) {
        this.usuarios = usuarios;
        return this;
    }
    
    @Override
    protected String getId() {
        return "newConversaGrupo";
    }

    @Override
    protected String[] getParams() {
        return new String[]{
            this.getUsername(),
            this.getTitulo(),
            String.join(";", this.getUsuarios())
        };
    }

}