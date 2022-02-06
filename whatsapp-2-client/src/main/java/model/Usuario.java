package model;

/**
 * @author Leonardo e Ruan
 */
public class Usuario extends Model {
    
    private String username;
    private String senha;
    private String nome;
    private int porta;

    public String getUsername() {
        return username;
    }

    public Usuario setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Usuario setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getPorta() {
        return porta;
    }

    public Usuario setPorta(int porta) {
        this.porta = porta;
        return this;
    }
    
}
