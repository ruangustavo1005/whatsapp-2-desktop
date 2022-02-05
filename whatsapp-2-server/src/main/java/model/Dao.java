package model;

import java.util.ArrayList;

/**
 * @author Leonardo e Ruan
 */
public class Dao {
    
    private static Dao instance;
    
    protected ArrayList<Usuario> usuarios;
    protected ArrayList<Conversa> conversas;
    
    private Dao() {
        this.usuarios = new ArrayList<>();
        this.conversas = new ArrayList<>();
    }
    
    public static Dao getInstance() {
        if(instance == null) {
            instance = new Dao();
        }
        return instance;
    }
    
    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public ArrayList<Conversa> getConversas() {
        return this.conversas;
    }
    
}