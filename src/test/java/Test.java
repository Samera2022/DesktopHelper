
import priv.samera2022.module.mainFrame;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String input = "{\"value1\",\"value2\"}";
        String[] output = convertStringToArray(input);

        // 打印数组内容，以验证结果
        for (String value : output) {
            System.out.println(value);
        }
    }

    public static String[] convertStringToArray(String input) {
        // 去除大括号
        String cleanedInput = input.replaceAll("[{}]", "");

        // 分割字符串
        String[] parts = cleanedInput.split(",");

        // 去除双引号，并创建新的数组
        String[] result = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = parts[i].trim().replaceAll("\"", "");
        }

        return result;
    }
    public static void main3(String[] args) {
        ArrayList<File> drives = new ArrayList<>();
        drives.addAll(Arrays.asList(File.listRoots()));
        for (int i = 0; i<drives.size(); i++){
            File drive = drives.get(i);
            System.out.println(drive.getPath());
        }
    }
    public static void main2(String[] args) throws IOException {
//        fixError(new File("D:/_S_A_M/Desktop/ac"),true);


        String rawName = "C.zip";
        String name = rawName.substring(0,rawName.lastIndexOf("."));
        System.out.println(name);

//        File file = new File("D:/_S_A_M/Desktop/Test");
//        File file2 = new File("D:/_S_A_M/Desktop/Test/");
//        mainFrame.logger.info(file.isDirectory());
//        mainFrame.logger.info(file2.isDirectory());
    }

    public static void fixError(File file, boolean isDirectory) throws IOException {
        String call = isDirectory ? "Folder" : "File";
        String call2 = !isDirectory ? "Folder" : "File";
        if (!file.exists()) {
            mainFrame.logger.warn(call + " " + file.getName() + " doesn't exist!");
            mainFrame.logger.info("Creating new " + call + " " + file.getName() + "...");
            if (isDirectory) {
                if (file.mkdirs()) mainFrame.logger.info("Create new folder " + file.getName() + " Successfully!");
                else mainFrame.logger.warn("Creating new folder " + file.getName() + " Unsuccessfully!");
            } else {
                if (file.createNewFile()) mainFrame.logger.info("Create new file " + file.getName() + " Successfully!");
                else mainFrame.logger.warn("Creating new file " + file.getName() + " Unsuccessfully!");
            }
        } else {
            if (file.isDirectory() != isDirectory) {
                mainFrame.logger.warn("There's already " + call2 + " " + file.getName() + " exists! Deleting...");
                if (file.delete()) mainFrame.logger.info("Delete " + call2 + " " + file.getName() + " Successfully!");
                else mainFrame.logger.warn("Delete " + call2 + " " + file.getName() + " Unsuccessfully!");
                mainFrame.logger.info("Creating new " + call + " " + file.getName() + "...");
                if (isDirectory) {
                    if (file.mkdirs()) mainFrame.logger.info("Create new folder " + file.getName() + " Successfully!");
                    else mainFrame.logger.warn("Creating new folder " + file.getName() + " Unsuccessfully!");
                } else {
                    if (file.createNewFile()) mainFrame.logger.info("Create new file " + file.getName() + " Successfully!");
                    else mainFrame.logger.warn("Creating new file " + file.getName() + " Unsuccessfully!");
                }
            }
        }
    }
}
