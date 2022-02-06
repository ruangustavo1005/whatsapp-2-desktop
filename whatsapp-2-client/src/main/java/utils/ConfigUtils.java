package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Ruan
 */
public class ConfigUtils {

    private static String config;

    public static void setConfig(String config) throws IOException {
        File configFile = FileUtils.openConfigFile();
        FileWriter fileWriter = new FileWriter(configFile);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(config);
        }
        ConfigUtils.config = config;
    }
    
    static public String getConfig() throws IOException {
        if (config == null) {
            reloadConfig();
        }
        return config;
    }
    
    static public void reloadConfig() throws IOException {
        File configFile = FileUtils.openConfigFile();
        FileInputStream fileInputStream = new FileInputStream(configFile);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            ConfigUtils.config = bufferedReader.readLine();
        }
    }
    
}