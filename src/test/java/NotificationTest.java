import priv.samera2022.module.FuzzyMatcher;
import priv.samera2022.module.Mixture;
import priv.samera2022.module.file.FileHandler;
import priv.samera2022.module.font.FontStyle;
import priv.samera2022.module.mainFrame;
import priv.samera2022.module.notification.Notification;
import priv.samera2022.module.notification.NotificationContent;
import priv.samera2022.module.notification.NotificationFileHandler;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static priv.samera2022.module.mainFrame.dsdNotification;

public class NotificationTest {

    public static void main(String[] args) {

    }

    public static void notification(ArrayList<String> commands){
        try {
            String command_2 = commands.get(1);
            String _string_switcher_2 = FuzzyMatcher.fuzzyMatch(command_2, Arrays.asList("add", "remove", "finished", "unfinished"));
            //notification <command_2>
            switch (_string_switcher_2) {
                case "add": {
                    String rawContent = commands.get(2);
                    String rContent = rawContent.substring(rawContent.indexOf("}") + 1);
                    mainFrame.logger.info("提示内容："+rContent);
                    String head = rawContent.substring(rawContent.indexOf("{") + 1, rawContent.indexOf("}"));
                    String[] splits = head.split(",");

                    String startDate;
                    boolean isFinished;
                    String endDate;
                    Notification n;

                    switch (splits.length) {
                        case 1:
                            endDate = splits[0];
                            n = new Notification(new NotificationContent(rContent), false, getTime(), endDate);
                            break;
                        case 3:
                            isFinished = Boolean.parseBoolean(splits[0]);
                            startDate = splits[1];
                            endDate = splits[2];
                            n = new Notification(new NotificationContent(rContent), isFinished, startDate, endDate);
                            break;
                        default:
                            mainFrame.logger.warn("No Matching Result!");
                            n = null;
                            break;
                    }
                    if (n != null) {
                        NotificationFileHandler.notifications.add(n);
                        //index是预备序号的意思
                        //下列逻辑是为了判断是否存在预备序号
                        if (commands.size()>3) {
                            String content = dsdNotification.getText(0,dsdNotification.getLength());
                            int index = Integer.parseInt(commands.get(3));
                            NotificationFileHandler.insertString(n.toString(),index);
                            if (NotificationFileHandler.notifications.size() < index) {
                                formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
                            } else {
                                NotificationFileHandler.notifications.add(index - 1, n);
                                int length = 0;
                                for (int i = 0; i < index - 1; i++) {
                                    Notification nLoop = NotificationFileHandler.notifications.get(i);
                                    length += nLoop.getContent().contentToString().length() + 3 + 1;
                                }
                                int lengthOfN;//字面意思，为换行所预留的长度
                                if (index != 1) {
                                    dsdNotification.insertString(length, "\n", FontStyle.plainStyle);
                                    lengthOfN = 1;
                                    length -= 1;//不知道为什么，但是这么加就能用了。
                                } else
                                    lengthOfN = 0;
                                if (n.isFinished()) dsdNotification.insertString(length + lengthOfN, "[√]", FontStyle.greenStyle);
                                else dsdNotification.insertString(length + lengthOfN, "[×]", FontStyle.darkRedStyle);
                                n.getContent().append(dsdNotification, length + lengthOfN + 3);
                                if (index == 1)
                                    dsdNotification.insertString(n.getContent().contentToString().length() + 3, "\n", FontStyle.plainStyle);
                            }
                        } else {
                            NotificationFileHandler.append(n.toString());
                            if (n.isFinished())
                                dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
                            else
                                dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
                            n.getContent().append(dsdNotification, dsdNotification.getLength());
                            dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.plainStyle);
                            //startDate和endDate暂未加入使用
                        }
                        formatter(true, new Mixture<>("Notification added Successfully!", FontStyle.greenStyle));
                    } else {
                        formatter(true, new Mixture<>("Error In Notification Pattern!", FontStyle.darkRedStyle));
                    }
                    break;
                }
                case "remove": {
                    int indexForHuman = Integer.parseInt(commands.get(2));
                    int index = indexForHuman - 1;
                    if (NotificationFileHandler.notifications.size() < index) {
                        formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
                    } else {
                        Notification backNotification = NotificationFileHandler.notifications.get(index);
                        if (index!=0) {
                            int length = 0;
                            //应当考虑index为最后一个的情况
                            for (int i = 0; i < index + 1; i++) {
                                Notification notification = NotificationFileHandler.notifications.get(i);
                                length += notification.getContent().contentToString().length() + 3;
                            }
                            NotificationFileHandler.notifications.remove(index);
                            dsdNotification.remove(length, backNotification.getContent().contentToString().length() + 3 + 1);
                            //+3是用于消除[×]和[√]，+1是为了消除换行
                        }
                        dsdNotification.remove(0, backNotification.getContent().contentToString().length() + 3 + 1);
                        NotificationFileHandler.notifications.remove(index);
                        FileHandler.deleteLine(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME, indexForHuman);
                        formatter(true, new Mixture<>("Notification Removed!", FontStyle.greenStyle));
                    }
                    break;
                }
                case "unfinished":
                    _finish(false, Integer.parseInt(commands.get(2)), dsdNotification);
                    break;
                case "finished":
                    _finish(true, Integer.parseInt(commands.get(2)), dsdNotification);
                    break;
                default:
                    formatter(true, new Mixture<>("\nYou might want to execute commands:\nnotification add \"{heads}<message>\" <index>\nnotification remove <index>\n", FontStyle.darkRedStyle));
                    break;
            }
        } catch (BadLocationException e){
            e.printStackTrace();;
        }
    }

    private static void _finish(boolean finishingMode, int indexForHuman, DefaultStyledDocument dsdNotification) {
        try {
            int index = indexForHuman - 1;
            if (NotificationFileHandler.notifications.size() < index) {
                formatter(true, new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
            } else {
                Notification previousNotification = NotificationFileHandler.notifications.get(index);
                previousNotification.setFinished(finishingMode);
                NotificationFileHandler.notifications.set(index, previousNotification);
                int length = 0;
                for (int i = 0; i < index; i++) {
                    Notification notification = NotificationFileHandler.notifications.get(i);
                    length += notification.getContent().contentToString().length() + 3;
                }
                dsdNotification.replace(length, 3, finishingMode ? "[√]" : "[×]", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle);
                formatter(true, new Mixture<>(finishingMode ? "Notification Task Completed!" : "Notification Task Uncompleted!", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle));
                __finish(index, previousNotification);
            }
        } catch (BadLocationException e) {
            //ignore it.
            e.printStackTrace();
        }
    }

    private static void __finish(int lineIndex, Notification subsequentNotification) {
        try {
            File f = new File(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME);
            if (f.isFile() && f.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String content;
                ArrayList<String> allContent = new ArrayList<>();
                while ((content = br.readLine()) != null) {
                    allContent.add(content);
                }
                allContent.set(lineIndex, subsequentNotification.toString());
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

    private static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }

    @SafeVarargs
    public static void formatter(boolean delete, Mixture<String, Style>... objects) {
        for (Mixture<String,Style> object : objects){
            System.out.println(object.getKey());
        }
    }
}
