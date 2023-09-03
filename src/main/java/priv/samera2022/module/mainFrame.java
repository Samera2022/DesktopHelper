package priv.samera2022.module;

//import com.sun.jna.platform.FileUtils;

import priv.samera2022.module.keylisteners.EnterKeyListener;
import priv.samera2022.module.notification.Notification;
import priv.samera2022.module.notification.NotificationFileHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class mainFrame {

    public static DefaultStyledDocument dsdInput = new DefaultStyledDocument(FontStyle.sc);
    public static DefaultStyledDocument dsdFileContent = new DefaultStyledDocument(FontStyle.sc);
    public static DefaultStyledDocument dsdNotification = new DefaultStyledDocument(FontStyle.sc);

    public static void main(String[] args) throws BadLocationException {
        frame();
    }

    public static void frame() throws BadLocationException {
        //警示：对于控件内文字的更改，不应采用xxx.setDocument(xxx)，而应该修改该控件内的DefaultStyledDocument！
        int x = 1236;
        //<---窗体头--->
        JFrame frame = new JFrame();
        frame.setSize(300, 700);
        frame.setLocation(x, 0);
        frame.setUndecorated(true);
        frame.enableInputMethods(false);
        JPanel totalPanel = new JPanel();
        totalPanel.setSize(300, 700);
        totalPanel.setLocation(x, 0);

        //<---INPUT_ANALYZE--->
        JTextArea jtaInput = new JTextArea(dsdInput); // 显示文件内容区域
        DropTarget dtsInput = new DropTarget(DropTarget.INPUT_ANALYZE, dsdInput, jtaInput);
        jtaInput.setEditable(true);
        jtaInput.setLineWrap(true);
        jtaInput.setWrapStyleWord(true);
        ScrollPane sp1 = new ScrollPane();
        sp1.add(jtaInput);
        sp1.setBounds(x, 0, 300, 100);
        new java.awt.dnd.DropTarget(jtaInput, DnDConstants.ACTION_COPY_OR_MOVE, dtsInput);
        String inputAsst = "-->HERE TO INPUT<--";
        dsdInput.insertString(0, inputAsst, FontStyle.plainStyle);
        totalPanel.add(sp1);

        //<---FILE_CONTENT_ANALYZE--->
        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE, dsdFileContent, jtpFileContent);
        jtpFileContent.setEditable(false);
        ScrollPane sp2 = new ScrollPane();
        sp2.add(jtpFileContent);
        sp2.setBounds(x, 400, 300, 400);
        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);
        totalPanel.add(sp2);

        //<---Notification--->
        JTextPane jtpNotification = new JTextPane(dsdNotification);
        if (NotificationFileHandler.notifications.size() != 0) {
            for (int i = 0; i < NotificationFileHandler.notifications.size(); i++) {
                Notification notification = NotificationFileHandler.notifications.get(i);
                if (notification.isFinished()) {
                    dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
                } else {
                    dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
                }
                notification.getContent().append(dsdNotification, dsdNotification.getLength());
                dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.blackStyle);
            }
        }
        jtpNotification.setEditable(false);
        ScrollPane sp3 = new ScrollPane();
        sp3.add(jtpNotification);
        sp3.setBounds(x, 500, 300, 200);
        totalPanel.add(sp3);

        //<---窗体尾--->
        jtaInput.addKeyListener(new EnterKeyListener());
        jtaInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //假如鼠标点击到该控件内，即焦点位于控件内
                System.out.println("focusGained!");
                JTextArea jta = (JTextArea) e.getSource();
                if (jta.getText().equals(inputAsst)) {
                    jta.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    //假如鼠标点击到该控件外，即焦点位于控件外
                    System.out.println("focusLost!");
                    JTextArea jta = (JTextArea) e.getSource();
                    if (jta.getText().isEmpty()) {
                        dsdInput.insertString(0, inputAsst, FontStyle.plainStyle);//JTextArea并不支持字体颜色
                    }
                } catch (BadLocationException badLocationException) {
                    //ignore it.
                }
            }
        });
        frame.add(totalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void _finish(boolean finishingMode, int indexForHuman, ArrayList<Mixture<String, Style>> messages, DefaultStyledDocument dsdNotification) {
        try {
            int index = indexForHuman - 1;
            if (NotificationFileHandler.notifications.size() < index) {
                messages.add(new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
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
                messages.add(new Mixture<>(finishingMode ? "Notification Task Completed!" : "Notification Task Uncompleted!", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle));
                __finish(index, previousNotification);
//                FileHandler.deleteLine(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME, indexForHuman);
            }
        } catch (BadLocationException e) {
            //ignore it.
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


    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String getTime() {
        return sdf.format(System.currentTimeMillis());
    }

    private static void _timePrefix(DefaultStyledDocument dsdFileContent) throws BadLocationException {
        String time = "[" + getTime() + "]";
        if (dsdFileContent.getLength() != 0)
            dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
        //如果不是第一行那就换行
        dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
    }

    private static void _fuzzyMatchInfo(DefaultStyledDocument dsdFileContent, String command) throws BadLocationException {
        _timePrefix(dsdFileContent);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\nCouldn't find that command! Guess you want to use command \"", FontStyle.blueStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\nExecuting Command \"", FontStyle.blueStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
    }

    private static String fuzzyMatch(String target, ArrayList<String> list) {
        String output = "";
        for (int i = 0; i < target.length(); i++) {
            //i是第几位
            if (list.size() != 1) {
                for (int j = 0; j < list.size(); j++) {
                    String element = list.get(j);
                    if (element.charAt(i) != target.charAt(i) && list.size() != 1) {
                        list.remove(element);
                        j--;
                    } else {
                        output = list.get(0);
                        break;
                    }
                }
            } else {
                //这段代码究竟有没有可能会运行？
                output = list.get(0);
                break;
            }
        }
        return output;
    }
}
