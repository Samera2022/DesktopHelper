package priv.samera2022.module.config;

import priv.samera2022.module.FileHandler;
import priv.samera2022.module.mainFrame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static final String CONFIG_PATH = FileHandler.FOLDER_PATH+FileHandler.CONFIG_PATH+FileHandler.CONFIG_NAME;
    public static Config CONFIG = read();

    public static void write(){
        Config config = new Config();
        config.setCommandOutput(true);

        Properties pro = new Properties();
        pro.setProperty(Concept.COMMAND_OUTPUT,String.valueOf(config.isCommandOutput()));

        try {
            FileWriter f = new FileWriter(CONFIG_PATH);
            pro.store(f,null);
            FileHandler.deleteLine(CONFIG_PATH,1);
        } catch (IOException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }

    }

    private static Config read(){
        Config config = new Config();
        Properties pro = new Properties();
        try {
            FileReader f = new FileReader(CONFIG_PATH);
            pro.load(f);
        } catch (IOException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
        config.setCommandOutput(Boolean.parseBoolean(pro.getProperty(Concept.COMMAND_OUTPUT)));
        return config;
    }

    public static void reload(){ CONFIG = read(); }
}
