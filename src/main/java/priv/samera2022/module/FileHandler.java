package priv.samera2022.module;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileHandler {
    public static final String FOLDER_PATH = "D:/_S_A_M/Files/AppData/DesktopHelper/";
    public static final String NOTIFICATION_NAME = "Notification.txt";

    static {
        try {
            File folder = new File(FOLDER_PATH);
            fixError(folder, true);
            File notification = new File(FOLDER_PATH + NOTIFICATION_NAME);
            fixError(notification, false);
        } catch (IOException e) {
            //ignore it.
        }
    }

    private static void fixError(File file, boolean isDictionary) throws IOException {
        if (file.isDirectory() != isDictionary) file.delete();
        if (!file.exists()) {
            if (isDictionary) {
                file.mkdirs();
            } else {
                file.createNewFile();
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

    public static void write(String path, String content) {
        File f = new File(path);
        try {
            if (f.exists()) {
                FileWriter fw = new FileWriter(f,StandardCharsets.UTF_8,false);
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
