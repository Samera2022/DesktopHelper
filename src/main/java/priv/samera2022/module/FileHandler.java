package priv.samera2022.module;

import java.io.*;
import java.nio.charset.StandardCharsets;

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
                    output.append(content+"\n");
                }
                if (output.toString().contains("\n")) result = output.substring(0,output.lastIndexOf("\n"));
                else result = output.toString();
            }
        } catch (IOException e) {
            //ignore it.
        }
        return result;
    }

    public static void write(String content, String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                FileWriter fw = new FileWriter(file,StandardCharsets.UTF_8,false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
                fw.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
