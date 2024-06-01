package priv.samera2022.module.notification;

import priv.samera2022.module.file.FileHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NotificationFileHandler {
    private static File notification = new File(FileHandler.FOLDER_PATH+FileHandler.NOTIFICATION_NAME);
    public static ArrayList<Notification> notifications;
    static {
        notifications = read();
    }

    //警示！在append的时候已经自动检测是否是第一行，若非第一行则执行换行操作。
    public static void append(String content){
        String inContent = FileHandler.read(FileHandler.FOLDER_PATH+FileHandler.NOTIFICATION_NAME);
        String enter = inContent.length()!=0?"\n":"";
        FileHandler.write(FileHandler.FOLDER_PATH+FileHandler.NOTIFICATION_NAME,enter+content,true);
    }

    public static void insertString(String content, int index){
        FileHandler.insertLine(FileHandler.FOLDER_PATH+FileHandler.NOTIFICATION_NAME,content,index);
    }

    public static ArrayList<Notification> read(){
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            if (notification.isFile() && notification.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(notification), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String content;
                while ((content = br.readLine()) != null) {
                    String notificationHEAD = content.substring(content.indexOf("{")+1,content.indexOf("}"));
                    String[] heads = notificationHEAD.split(",");
                    String rContent = content.substring(content.indexOf("}")+1);
                    boolean isFinished = Boolean.parseBoolean(heads[0]);
                    String startDate = heads[1];
                    String endDate = heads[2];
                    NotificationContent nc = new NotificationContent(rContent);
                    Notification notification = new Notification(nc,isFinished,startDate,endDate);
                    notifications.add(notification);
                }
                br.close();
                isr.close();
            }
        } catch (IOException e){
            //ignore it.
        }
        return notifications;
    }
}
