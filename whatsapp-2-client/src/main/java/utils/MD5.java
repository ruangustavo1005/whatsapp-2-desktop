package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Leonardo & Ruan
 */
public class MD5 {

    static public String md5(String text) {
        String retorno = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(text.getBytes(), 0, text.length());
            retorno = new BigInteger(1, m.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }
    
    static public String md5(char[] chars) {
        return md5(new String(chars));
    }
    
}
