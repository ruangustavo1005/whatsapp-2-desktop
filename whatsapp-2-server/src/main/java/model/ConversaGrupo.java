package model;

import java.util.List;

/**
 * @author Leonardo e Ruan
 */
public class ConversaGrupo extends Conversa {
    
    private String nomeConversa;
    
    private List<Usuario> usuarios;

    public ConversaGrupo(String id, String nomeConversa, List<Usuario> usuarios, int qtdMensagensNaoLidas) {
        super(id, qtdMensagensNaoLidas);
        this.nomeConversa = nomeConversa;
        this.usuarios = usuarios;
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
