package utils;

import java.io.File;
import java.io.IOException;

/**
 * @author Leonardo & Ruan
 */
public class FileUtils {

    static public final String CONFIG_FILE_NAME = "config.txt";
    
    static public File openConfigFile() throws IOException {
        File file = new File(CONFIG_FILE_NAME);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
    
}