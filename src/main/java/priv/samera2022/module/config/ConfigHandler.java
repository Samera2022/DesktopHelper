package priv.samera2022.module.config;

import priv.samera2022.module.file.FileHandler;
import priv.samera2022.module.mainFrame;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static final String CONFIG_PATH = FileHandler.FOLDER_PATH+FileHandler.CONFIG_PATH+FileHandler.CONFIG_NAME;
    private static Properties DEFAULT = new Properties();
    private static Properties READ = new Properties();
    public static Config CONFIG = read();


    //当config发生更新时，需要更改以下语句：
    public static void write(){
        Config config = new Config();
        //1. 默认config注册
        config.setCommandOutput(true);
        config.setCf_api_key("null");
        config.setCf_doa(true);

        //2. 默认pro设值
        Properties pro = new Properties();
        pro.setProperty(Concept.COMMAND_OUTPUT,String.valueOf(config.isCommandOutput()));
        pro.setProperty(Concept.CF_API_KEY,config.getCf_api_key());
        pro.setProperty(Concept.CF_DOA, config.getCf_api_key());

        DEFAULT = pro;
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
        READ = pro;
        //3. 读取config
        config.setCommandOutput(Boolean.parseBoolean(pro.getProperty(Concept.COMMAND_OUTPUT)));

        String in1 = pro.getProperty(Concept.CF_API_KEY);
        if (in1.equals("null")) in1 = null;
        config.setCf_api_key(in1);

        config.setCf_doa(Boolean.parseBoolean(pro.getProperty(Concept.CF_DOA)));
        return config;
    }

    public static void reload(){ CONFIG = read(); }

    //这个方法其实没多大用，因为在read的时候就已经设置属性了
    @Deprecated
    public static void UpdateIfPossible(){
        if (READ.size() != DEFAULT.size()) {
            mainFrame.logger.warn("Config已过时！正在更新Config文件！");
            for (Object object : DEFAULT.keySet()){
                if (!READ.containsKey(object)){
                     READ.setProperty((String) object,"Updated");
                }
            }
            writeProperties(READ);
        }
    }

    private static void writeProperties(Properties pro){
        try {
            FileWriter f = new FileWriter(CONFIG_PATH);
            pro.store(f,null);
            FileHandler.deleteLine(CONFIG_PATH,1);
        } catch (IOException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }
}
