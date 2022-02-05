package model;

import java.util.HashMap;

/**
 * @author Leonardo e Ruan
 */
public class Dao {
    
    private static Dao instance;
    
    protected HashMap<String, Usuario> usuarios;
    protected HashMap<String, Conversa> conversas;
    
    private Dao() {
        this.usuarios = new HashMap<>();
        this.conversas = new HashMap<>();
    }
    
    public static Dao getInstance() {
        if(instance == null) {
            instance = new Dao();
        }
        return instance;
    }
    
    public HashMap<String, Usuario> getUsuarios() {
        return this.usuarios;
    }
    
    public HashMap<String, Conversa> getConversas() {
        return this.conversas;
    }
    
}