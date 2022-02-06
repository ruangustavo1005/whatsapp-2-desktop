package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Leonardo & Ruan
 */
public class DateUtils {

    static public String FORMAT_DATA      = "dd/MM/yyyy";
    static public String FORMAT_HORA      = "HH:mm:ss";
    static public String FORMAT_DATA_HORA = "dd/MM/yyyy HH:mm:ss";
    
    public static SimpleDateFormat getInstanceSimpleDateFormat(String format) {
        return new SimpleDateFormat(format);
    }
    
    public static Date stringToDate(String data) {
        return stringToDate(data, FORMAT_DATA);
    }
    
    public static Date stringToHour(String data) {
        return stringToDate(data, FORMAT_HORA);
    }
    
    public static Date stringToDateHour(String dataHora) {
        return stringToDate(dataHora, FORMAT_DATA_HORA);
    }
    
    public static Date stringToDate(String data, String format) {
        Date retorno = null;
        try {
            retorno = getInstanceSimpleDateFormat(format).parse(data);
        }
        catch (ParseException ex) {
            System.out.println(ex.toString());
        }
        return retorno;
    }
    
    public static String dateToString(Date data) {
        return dateToString(data, FORMAT_DATA);
    }
    
    public static String hourToString(Date data) {
        return dateToString(data, FORMAT_HORA);
    }
    
    public static String dateHourToString(Date dataHora) {
        return dateToString(dataHora, FORMAT_DATA_HORA);
    }
    
    public static String dateToString(Date data, String format) {
        return getInstanceSimpleDateFormat(format).format(data);
    }
    
}