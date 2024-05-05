package priv.samera2022.module.file;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipHandler {

    /**
     * 直接以txt格式读取zip目录下的某一文件
     * @param path zip路径
     * @param name 文件名称
     * */
    public static String readFromZip(String path, String name){
        String content = "";
        try (ZipFile zipFile = new ZipFile(path)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().equals(name)) { // 要读取的文件名
                    InputStream inputStream = zipFile.getInputStream(entry);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    StringBuilder contentBuilder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        contentBuilder.append(line).append("\n");
                    }
                    content = contentBuilder.toString();
                    break; // 只需读取第一个符合条件的文件，若不需要则去除此行
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * @param zipPath zip路径
     * @param target 压缩包中的对应目录
     * @param output 输出路径
     * */
    public static void extract(String zipPath, String target, String output) throws IOException {
        try (ZipFile zipFile = new ZipFile(zipPath)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryName = entry.getName();

                // 如果条目是以folderToExtract开头的，我们需要解压它
                if (entryName.startsWith(target + "/")) {
                    // 去除folderToExtract部分以获取在解压目录中的相对路径
                    String relativePath = entryName.substring(target.length() + 1);
                    Path extractPath = Paths.get(output, relativePath);

                    // 如果是目录，则创建它
                    if (entry.isDirectory()) {
                        Files.createDirectories(extractPath);
                    } else {
                        // 如果是文件，则创建必要的父目录并解压文件
                        Files.createDirectories(extractPath.getParent());
                        try (InputStream is = zipFile.getInputStream(entry);
                             OutputStream os = Files.newOutputStream(extractPath)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = is.read(buffer)) > 0) {
                                os.write(buffer, 0, len);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param source 源文件/文件夹地址
     * @param output 输出地址
     */
    public static void compress(String source, String output) {
        try (FileOutputStream fos = new FileOutputStream(output);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            // 调用递归方法压缩文件或文件夹
            addToZipFile(source, source, zos);

            System.out.println("文件已成功打包成 " + output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // 要压缩的文件或文件夹
        String sourceFile = "D:/_S_A_M/Files/AppData/DesktopHelper/";

        // 压缩后的ZIP文件名
        String zipFileName = "D:/_S_A_M/Desktop/"+"output.zip";

        compress(sourceFile,zipFileName);
    }

    private static void addToZipFile(String path, String sourceFile, ZipOutputStream zos) throws IOException {
        File file = new File(sourceFile);

        // 如果是文件夹，则获取其内容并递归调用此方法
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                for (String fileName : fileList) {
                    addToZipFile(path, sourceFile + File.separator + fileName, zos);
                }
            }
            return;
        }

        // 如果是文件，则将其添加到ZIP文件中
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
            String entryName = sourceFile.substring(path.length() + 1); // 获取ZIP中的条目名称
            ZipEntry zipEntry = new ZipEntry(entryName);
            zos.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
        }
    }

}
