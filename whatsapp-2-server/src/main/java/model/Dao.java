package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    static public String md5(String text) {
        String retorno = "";
        try {
            MessageDigest m = MessageDigest.getInstance(retorno).getInstance("MD5");
            m.update(text.getBytes(), 0, text.length());
            retorno = new BigInteger(1, m.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
}