package priv.samera2022.module.config;

import priv.samera2022.module.Logger;
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

    static {
        mainFrame.logger.info("Variable command_output = "+CONFIG.isCommandOutput());
        mainFrame.logger.info("Variable cf_api_key = "+ (CONFIG.getCf_api_key()==null?"Missing":"Existed"));
        mainFrame.logger.info("Variable cf_doa = "+CONFIG.isCf_doa());
        mainFrame.logger.info("Variable simple_logger_output = "+CONFIG.isSimpleLoggerOutput());
        mainFrame.logger.info("Variable enable_characterization = "+CONFIG.isEnableCharacterization());
        mainFrame.logger.info("Variable bg_path = "+CONFIG.getBgPath());
        mainFrame.logger.info("Variable dark_mode = "+CONFIG.isDarkMode());
    }

    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    //当config发生更新时，需要更改以下语句：
    public static void write(){
        Config config = new Config();
        //1. 默认config注册
        config.setCommandOutput(true);
        config.setCf_api_key("null");
        config.setCf_doa(true);
        config.setSimpleLoggerOutput(true);
        config.setEnableCharacterization(true);
        config.setBgPath("null");
        config.setDarkMode(true);

        //2. 默认pro设值
        Properties pro = new Properties();
        pro.setProperty(Concept.SIMPLE_LOGGER_OUTPUT, config.getCf_api_key());
        pro.setProperty(Concept.COMMAND_OUTPUT,String.valueOf(config.isCommandOutput()));
        pro.setProperty(Concept.CF_API_KEY,config.getCf_api_key());
        pro.setProperty(Concept.CF_DOA, config.getCf_api_key());
        pro.setProperty(Concept.ENABLE_CHARACTERIZATION, String.valueOf(config.isEnableCharacterization()));
        pro.setProperty(Concept.BG_PATH, config.getBgPath());
        pro.setProperty(Concept.DARK_MODE, String.valueOf(config.isDarkMode()));

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
        //读取config
        config.setSimpleLoggerOutput(nonNullBoolean(pro,Concept.SIMPLE_LOGGER_OUTPUT));

        config.setCommandOutput(nonNullBoolean(pro,Concept.COMMAND_OUTPUT));

        String in1 = nonNullString(pro, Concept.CF_API_KEY);
        if (in1.equals("null")) in1 = null;
        config.setCf_api_key(in1);

        config.setCf_doa(nonNullBoolean(pro,Concept.CF_DOA));
        config.setEnableCharacterization(nonNullBoolean(pro,Concept.ENABLE_CHARACTERIZATION));

        String in2 = nonNullString(pro, Concept.BG_PATH);
        if (in2.equals("null")) in2 = null;
        config.setBgPath(in2);

        config.setDarkMode(nonNullBoolean(pro,Concept.DARK_MODE));
        return config;
    }
    private static String nonNullString(Properties pro, String concept){
        String property = pro.getProperty(concept);
        if (property==null) {
            System.out.println("Key "+concept + " has empty value! Set it as null...");
            return "null";
        }
        if (property.isBlank()||property.isEmpty()) {
            System.out.println("Key "+concept + " has empty value! Set it as null...");
            return "null";
        }
        else return property;
    }
    private static boolean nonNullBoolean(Properties pro, String concept){
        String property = pro.getProperty(concept);
        if (property==null) {
            System.out.println("Key "+concept + " has empty value! Set it as false...");
            return false;
        }
        if (property.isEmpty() && property.isBlank()) {
            System.out.println("Key "+concept + " has empty value! Set it as false...");
            return false;
        }
        else return Boolean.parseBoolean(property);
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
