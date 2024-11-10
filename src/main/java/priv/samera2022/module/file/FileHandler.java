package priv.samera2022.module.file;

import priv.samera2022.module.Logger;
import priv.samera2022.module.mainFrame;

import javax.swing.*;
import javax.swing.text.Style;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandler {
    public static String FOLDER_PATH = "D:/_S_A_M/Files/AppData/DesktopHelper/";
    public static final String STORAGE_NAME = "Storage.txt";
    public static final String NOTIFICATION_NAME = "Notification.txt";
    public static final String QUIZ_PATH = "quizzes/";
    public static final String CONFIG_PATH = "config/";
    public static final String CONFIG_GPT_PATH = "gpt/";
    public static final String CONFIG_GPT_DEFAULT_NAME = "default.json";
    public static final String CONFIG_NAME = "config.cfg";

    //还是用init方法比较好，相较static块而言更方便初始化。
    static {
        System.out.println("---Pre Logger Record---");
        ArrayList<File> drives = new ArrayList<>();
        drives.addAll(Arrays.asList(File.listRoots()));
        for (int i = 0; i<drives.size(); i++){
            File drive = drives.get(i);
            if (drive.getPath().equals("D:\\")) {
                FOLDER_PATH = "D:\\";
                break;
            } else if (i==drives.size()-1){
//                FOLDER_PATH = drives.get(0).getPath();
                FOLDER_PATH = JOptionPane.showInputDialog(null, "Specified A Disk! (D:\\)", "Disk D Not Found!", JOptionPane.INFORMATION_MESSAGE);        //输入对话框
            }
        }
//        FOLDER_PATH = JOptionPane.showInputDialog(null, "Specified A Disk!", "Disk D Not Found!", JOptionPane.INFORMATION_MESSAGE);        //输入对话框
        FOLDER_PATH = FOLDER_PATH + "_S_A_M/Files/AppData/DesktopHelper/";
        FOLDER_PATH = FOLDER_PATH.replace('\\','/');
        System.out.println("[PRE-L] Current AppData Path: "+FOLDER_PATH);

        try {
            File folder = new File(FOLDER_PATH);
            fixError(folder, true);

            File storage = new File(FOLDER_PATH + STORAGE_NAME);
            fixError(storage, false);

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
            if (FileHandler.read(FOLDER_PATH + CONFIG_PATH + CONFIG_GPT_PATH + CONFIG_GPT_DEFAULT_NAME).length() == 0)
                priv.samera2022.module.gadgets.gpt.config.ConfigHandler.write();

            File config = new File(FOLDER_PATH + CONFIG_PATH + CONFIG_NAME);
            fixError(config, false);
            if (FileHandler.read(FOLDER_PATH + CONFIG_PATH + CONFIG_NAME).length() == 0)
                priv.samera2022.module.config.ConfigHandler.write();
            System.out.println("---Pre Logger Record---");
//            priv.samera2022.module.config.ConfigHandler.UpdateIfPossible();
        } catch (IOException e) {
            e.printStackTrace();
            //ignore it.
        }
    }

    public static void fixError(File file, boolean isDirectory) throws IOException {
        String call = isDirectory ? "Folder" : "File";
        String call2 = !isDirectory ? "Folder" : "File";
        if (!file.exists()) {
            mainFrame.logger.pre("[WARN] "+call + " " + file.getName() + " doesn't exist!");
            mainFrame.logger.info("[INFO] "+"Creating new " + call + " " + file.getName() + "...");
            if (isDirectory) {
                if (file.mkdirs()) mainFrame.logger.info("[INFO] "+"Create new folder " + file.getName() + " Successfully!");
                else mainFrame.logger.pre("[WARN] "+"Creating new folder " + file.getName() + " Unsuccessfully!");
            } else {
                if (file.createNewFile()) mainFrame.logger.info("[INFO] "+"Create new file " + file.getName() + " Successfully!");
                else mainFrame.logger.pre("[WARN] "+"Creating new file " + file.getName() + " Unsuccessfully!");
            }
        } else {
            if (file.isDirectory() != isDirectory) {
                mainFrame.logger.pre("[WARN] "+"There's already " + call2 + " " + file.getName() + " exists! Deleting...");
                if (file.delete()) mainFrame.logger.info("[INFO] "+"Delete " + call2 + " " + file.getName() + " Successfully!");
                else mainFrame.logger.pre("[WARN] "+"Delete " + call2 + " " + file.getName() + " Unsuccessfully!");
                mainFrame.logger.info("[INFO] "+"Creating new " + call + " " + file.getName() + "...");
                if (isDirectory) {
                    if (file.mkdirs()) mainFrame.logger.info("[INFO] "+"Create new folder " + file.getName() + " Successfully!");
                    else mainFrame.logger.pre("[WARN] "+"Creating new folder " + file.getName() + " Unsuccessfully!");
                } else {
                    if (file.createNewFile()) mainFrame.logger.info("[INFO] "+"Create new file " + file.getName() + " Successfully!");
                    else mainFrame.logger.pre("[WARN] "+"Creating new file " + file.getName() + " Unsuccessfully!");
                }
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
                if (output.toString().contains("\n")) result = output.substring(0, output.lastIndexOf("\n"));
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
                FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8, append);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                fw.close();
            }
        } catch (Exception e) {
            //ignore it.
        }
    }

    public static void main(String[] args) {
        insertLine("D:/_S_A_M/Desktop/test.txt","test",3);
    }

    public static void insertLine(String filePath, String textToInsert, int lineNumber) {
        // 行号从1开始，所以需要做转换
        lineNumber = lineNumber - 1;

        Path path = Paths.get(filePath);
        // 使用临时文件来避免原文件损坏
        Path tempPath = Paths.get(filePath + ".tmp");

        try (BufferedReader reader = Files.newBufferedReader(path);
             BufferedWriter writer = Files.newBufferedWriter(tempPath)) {

            String line;
            int currentLine = 0;
            boolean inserted = false;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber && !inserted) {
                    // 到达指定行号，插入新行
                    writer.write(textToInsert);
                    writer.newLine();
                    inserted = true;
                }
                // 写入当前行
                writer.write(line);
                writer.newLine();
                currentLine++;
            }

            if (!inserted) {
                // 如果文件结束前未插入，则在文件末尾插入
                writer.write(textToInsert);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // 替换原始文件
            Files.move(tempPath, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
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
                allContent.remove(lineIndex - 1);
                br.close();
                isr.close();
                FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8, false);
                BufferedWriter bw = new BufferedWriter(fw);
                StringBuilder processedContent = new StringBuilder();
                for (int i = 0; i < allContent.size(); i++) {
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

    /**
     * @param url  指文件的网络下载地址
     * @param ask 文件重复时是否询问？
     */
    public static void downloadFile(String url, String path, String name, boolean ask) {
        File saveFile = new File(path, name);
        try {
            fixError(new File(path),true);
        } catch (IOException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
        boolean download;
        if (!ask) download = true;
        else {
            if (saveFile.exists())
                download = JOptionPane.showConfirmDialog(null, "文件" + name + "已存在，是否覆盖？", "下载错误！", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
            else download = true;
        }
        if (download) {
            try {
                URL Url = new URL(url);
                try (InputStream inStream = new BufferedInputStream(Url.openStream());
                     FileOutputStream outStream = new FileOutputStream(saveFile)) {

                    byte[] buffer = new byte[1024];
                    int length;

                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                mainFrame.ExceptionHandler(e);
            }
        }
    }


}
