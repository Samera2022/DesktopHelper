package priv.samera2022.module;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileHandler {
    public static final String FOLDER_PATH = "D:/_S_A_M/Files/AppData/DesktopHelper/";
    public static final String STORAGE_NAME = "Storage.txt";
    public static final String NOTIFICATION_NAME = "Notification.txt";
    public static final String QUIZ_PATH = "quizzes/";
    public static final String CONFIG_PATH = "config/";
    public static final String CONFIG_GPT_PATH = "gpt/";
    public static final String CONFIG_GPT_DEFAULT_NAME = "default.json";
    public static final String CONFIG_NAME = "config.cfg";

    //还是用init方法比较好，相较static块而言更方便初始化。
    public static void init(){
        try {
            File folder = new File(FOLDER_PATH);
            fixError(folder, true);

            File storage = new File(FOLDER_PATH + STORAGE_NAME);
            fixError(storage,false);

            File notification = new File(FOLDER_PATH + NOTIFICATION_NAME);
            fixError(notification, false);

            File quizzes = new File(FOLDER_PATH + QUIZ_PATH);
            fixError(quizzes, true);

            File config_path = new File(FOLDER_PATH + CONFIG_PATH);
            fixError(config_path, true);

            File config_gpt_path = new File(FOLDER_PATH + CONFIG_PATH + CONFIG_GPT_PATH);
            fixError(config_gpt_path, true);

            File config_gpt_default = new File(FOLDER_PATH + CONFIG_PATH + CONFIG_GPT_PATH + CONFIG_GPT_DEFAULT_NAME);
            fixError(config_gpt_default, false);
            if (FileHandler.read(FOLDER_PATH + CONFIG_PATH + CONFIG_GPT_PATH + CONFIG_GPT_DEFAULT_NAME).length()==0)
                priv.samera2022.module.gadgets.web.config.ConfigHandler.write();

            File config = new File(FOLDER_PATH + CONFIG_PATH + CONFIG_NAME);
            fixError(config, false);
            if (FileHandler.read(FOLDER_PATH + CONFIG_PATH + CONFIG_NAME).length()==0)
                priv.samera2022.module.config.ConfigHandler.write();

        } catch (IOException e) {
            e.printStackTrace();
            //ignore it.
        }
    }

    private static void fixError(File file, boolean isDictionary) throws IOException {
        String call = file.isDirectory()?"Directory":"File";
        if (file.isDirectory() != isDictionary) {
            mainFrame.logger.info("There's already "+call+" "+file.getName()+" exists! Deleting...");
            if (file.delete()) mainFrame.logger.info("Delete "+call+" "+file.getName()+" Successfully!");
            else mainFrame.logger.error("Delete "+call+" "+file.getName()+" Unsuccessfully!");
        }
        if (!file.exists()) {
            mainFrame.logger.info(call+" "+file.getName()+" doesn't exist!");
            mainFrame.logger.info("Creating new "+call+" "+file.getName()+"...");
            if (isDictionary) {
                if (file.mkdirs()) mainFrame.logger.info("Create new folder "+file.getName()+" Successfully!");
                else mainFrame.logger.error("Creating new folder "+file.getName()+" Unsuccessfully!");
            } else {
                if (file.createNewFile()) mainFrame.logger.info("Create new file "+file.getName()+" Successfully!");
                else mainFrame.logger.error("Creating new file "+file.getName()+" Unsuccessfully!");
            }
        }
    }

    public static String read(String path) {
        StringBuilder output = new StringBuilder("");
        String result = "";
        try {
            File f = new File(path);
            if (f.isFile() && f.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String content;
                while ((content = br.readLine()) != null) {
                    output.append(content).append("\n");
                }
                if (output.toString().contains("\n")) result = output.substring(0,output.lastIndexOf("\n"));
                else result = output.toString();
                br.close();
                isr.close();
            }
        } catch (IOException e) {
            //ignore it.
        }
        return result;
    }

    public static void write(String path, String content, boolean append) {
        File f = new File(path);
        try {
            if (f.exists()) {
                FileWriter fw = new FileWriter(f,StandardCharsets.UTF_8,append);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                fw.close();
            }
        } catch (Exception e) {
            //ignore it.
        }
    }

    public static void deleteLine(String path, int lineIndex) {
        //lineIndex是人类识别的index，不是机器识别的index。
        try {
            File f = new File(path);
            if (f.isFile() && f.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String content;
                ArrayList<String> allContent = new ArrayList<>();
                while ((content = br.readLine()) != null) {
                    allContent.add(content);
                }
                allContent.remove(lineIndex-1);
                br.close();
                isr.close();
                FileWriter fw = new FileWriter(f,StandardCharsets.UTF_8,false);
                BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder processedContent = new StringBuilder();
                for (int i = 0; i<allContent.size(); i++){
                    String line = allContent.get(i);
                    processedContent.append(line).append(i == allContent.size() - 1 ? "" : "\n");
                }
                bw.write(processedContent.toString());
                bw.close();
                fw.close();
            }
        } catch (IOException e) {
            //ignore it.
        }
    }
}
