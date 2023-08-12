package priv.samera2022.module;

//import com.sun.jna.platform.FileUtils;
import priv.samera2022.module.notification.Notification;
import priv.samera2022.module.notification.NotificationContent;
import priv.samera2022.module.notification.NotificationFileHandler;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class mainFrame {
    private static boolean isCommand = false;//如果有人不符合所有的已知命令头的话，那就说明他不在输入命令呗

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
        DefaultStyledDocument dsdInput = new DefaultStyledDocument(FontStyle.sc);
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
        DefaultStyledDocument dsdFileContent = new DefaultStyledDocument(FontStyle.sc);
        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE, dsdFileContent, jtpFileContent);
        jtpFileContent.setEditable(false);
        ScrollPane sp2 = new ScrollPane();
        sp2.add(jtpFileContent);
        sp2.setBounds(x, 400, 300, 400);
        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);
        totalPanel.add(sp2);

        //<---Notification--->
        DefaultStyledDocument dsdNotification = new DefaultStyledDocument(FontStyle.sc);
        JTextPane jtpNotification = new JTextPane(dsdNotification);
        if (NotificationFileHandler.notifications.size() != 0) {
            for (int i = 0; i<NotificationFileHandler.notifications.size(); i++) {
                Notification notification = NotificationFileHandler.notifications.get(i);
                if (notification.isFinished()){
                    dsdNotification.insertString(dsdNotification.getLength(),"[√]",FontStyle.greenStyle);
                } else {
                    dsdNotification.insertString(dsdNotification.getLength(),"[×]",FontStyle.darkRedStyle);
                }
                notification.getContent().append(dsdNotification, dsdNotification.getLength());
                dsdNotification.insertString(dsdNotification.getLength(),"\n",FontStyle.blackStyle);
//                System.out.println("Notification.toString(): "+notification.toString());
//                System.out.println("NotificationContent.contentToString(): "+notification.getContent().contentToString());
//                if (i!=NotificationFileHandler.notifications.size()-1) dsdNotification.insertString(dsdNotification.getLength(),"\n",FontStyle.blackStyle);
            }
        }
        jtpNotification.setEditable(false);
        ScrollPane sp3 = new ScrollPane();
        sp3.add(jtpNotification);
        sp3.setBounds(x, 500, 300, 200);
        totalPanel.add(sp3);

        //<---窗体尾--->
        jtaInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //ignore it.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //当键盘上的某个键(q w e r etc.)被按下时
                try {
                    System.out.println("keyPressed");
                    String content = ((JTextArea) e.getSource()).getText();
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String[] contents = content.split(" ", 2);
                        ArrayList<Mixture<String, Style>> messages = new ArrayList<>();
                        switch (contents[0]) {
                            case "usage":
                                String targetCommand = contents[1];
                                if (!targetCommand.contains(" ")) {
                                    switch (targetCommand){
                                        case "print":
                                            messages.add(new Mixture<>("Usage: print <content>",FontStyle.blackStyle));
                                            messages.add(new Mixture<>("Description: Just to print some words.",FontStyle.blackStyle));
                                            break;
                                        case "clear":
                                            messages.add(new Mixture<>("Usage: clear",FontStyle.blackStyle));
                                            messages.add(new Mixture<>("Description: Clear all the history.",FontStyle.blackStyle));
                                            break;
//                                        case "del":
//                                        case "delete":
//                                            messages.add(new Mixture<>("Usage: del||delete",FontStyle.blackStyle));
//                                            messages.add(new Mixture<>("Description: Delete a file or a folder.",FontStyle.blackStyle));
//                                            break;
                                        case "notification":
                                            messages.add(new Mixture<>("Usage: notification <subCommand> \"{(isFinished,startDate,)endDate}<message>\" <index>",FontStyle.blackStyle));
                                            messages.add(new Mixture<>("Description: For more helps of notification, please enter: notification help",FontStyle.blackStyle));
                                    }
                                } else messages.add(new Mixture<>("Illegal Command!",FontStyle.darkRedStyle));
                                break;
                            case "exit":
                                System.exit(0);
                                break;
                            case "print":
                                isCommand = true;
                                messages.add(new Mixture<>(contents[1], FontStyle.plainStyle));
                                break;
                            case "clear":
                                isCommand = true;
                                dsdFileContent.remove(0, dsdFileContent.getLength());
                                messages.add(new Mixture<>("Cleared Successfully.", FontStyle.greenStyle));
                                break;
//                            case "del":
//                            case "delete":
//                                isCommand = true;
//                                File f = new File(contents[1]);
//                                if (!f.exists()) {
//                                    messages.add(new Mixture<>("File or Folder doesn't exist!", FontStyle.darkRedStyle));
//                                } else {
//                                    FileUtils fu = FileUtils.getInstance();
//                                    fu.moveToTrash(new File[]{f});
//                                    if (!f.exists()) {
//                                        messages.add(new Mixture<>("File or Folder deleted successfully.", FontStyle.greenStyle));
//                                    } else {
//                                        messages.add(new Mixture<>("File or Folder didn't delete properly!", FontStyle.darkRedStyle));
//                                    }
//                                }
//                                break;
                            case "notification":
                                isCommand = true;
                                String rawContent = contents[1];
//                                System.out.println("rawContent: "+rawContent);
                                int breakPoint = rawContent.indexOf(" ");
//                                System.out.println("breakPoint: "+breakPoint);
                                if (breakPoint != -1) {
                                    String command_2 = rawContent.substring(0, rawContent.indexOf(" "));
//                                    System.out.println("command_2: "+command_2);

//                                    rawContent = rawContent.substring(breakPoint);
//                                    System.out.println("rawContent: "+rawContent);

                                    //notification <command_2>
                                    switch (command_2) {
                                        case "add":
                                            int temp = rawContent.split("\"").length;
                                            System.out.println(temp);
                                            if ((rawContent.indexOf("{") < rawContent.indexOf("}")) && temp >= 2) {
                                                String message = rawContent.substring(rawContent.indexOf("\"") + 1, rawContent.lastIndexOf("\""));
                                                String rContent = message.substring(message.indexOf("}") + 1);
                                                System.out.println(rContent);
                                                String head = message.substring(message.indexOf("{") + 1, message.indexOf("}"));
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
                                                        startDate = splits[0];
                                                        isFinished = Boolean.parseBoolean(splits[1]);
                                                        endDate = splits[2];
                                                        n = new Notification(new NotificationContent(rContent), isFinished, startDate, endDate);
                                                        break;
                                                    default:
                                                        System.out.println("No Matching Result!");
                                                        n = null;
                                                        break;
                                                }
                                                if (n != null) {
                                                    NotificationFileHandler.notifications.add(n);
                                                    NotificationFileHandler.append(n.toString());
                                                    int index;
                                                    if (rawContent.lastIndexOf("\"") + 1 + 1 < rawContent.length()) {
                                                        index = Integer.parseInt(rawContent.substring(rawContent.lastIndexOf("\"") + 1 + 1));
                                                        if (NotificationFileHandler.notifications.size() < index) {
                                                            messages.add(new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
                                                        } else {
                                                            NotificationFileHandler.notifications.add(index - 1, n);
                                                            int length = 0;
                                                            for (int i = 0; i < index - 1; i++) {
                                                                Notification nLoop = NotificationFileHandler.notifications.get(i);
                                                                length += nLoop.getContent().contentToString().length() + 3;
                                                                System.out.println("length: " + length);
                                                            }
                                                            System.out.println("totalLength: " + length);
                                                            int lengthOfN;//字面意思，为换行所预留的长度
                                                            if (index != 1) {
                                                                dsdNotification.insertString(length, "\n", FontStyle.blackStyle);
                                                                lengthOfN = 1;
                                                            } else lengthOfN = 0;
                                                            if (n.isFinished())
                                                                dsdNotification.insertString(length + lengthOfN, "[√]", FontStyle.greenStyle);
                                                            else
                                                                dsdNotification.insertString(length + lengthOfN, "[×]", FontStyle.darkRedStyle);
//                                                            dsdNotification.insertString(length + lengthOfN + 3, "", FontStyle.blackStyle);
                                                            n.getContent().append(dsdNotification, length + lengthOfN + 3);
                                                            if (index == 1)
                                                                dsdNotification.insertString(n.getContent().contentToString().length() + 3, "\n", FontStyle.blackStyle);
//                                                        dsdNotification.insertString(length+n.getContent().contentToString().length()+1+3,"\n",FontStyle.blackStyle);
                                                        }
                                                    } else {
                                                        if (n.isFinished())
                                                            dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
                                                        else
                                                            dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
//                                                        dsdNotification.insertString(dsdNotification.getLength(), "", FontStyle.blackStyle);
                                                        n.getContent().append(dsdNotification, dsdNotification.getLength());
                                                        dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.blackStyle);
                                                        //startDate和endDate暂未加入使用
                                                    }
                                                    messages.add(new Mixture<>("Notification added Successfully!", FontStyle.greenStyle));
                                                } else {
                                                    messages.add(new Mixture<>("Error In Notification Pattern!", FontStyle.darkRedStyle));
                                                }
                                            } else if (rawContent.indexOf("{")>=rawContent.indexOf("}")){
                                                if (rawContent.indexOf("{")==rawContent.indexOf("}")){
                                                    //意思就是两个都不存在，只有两个都不存在的时候才可能相等均为-1
                                                    messages.add(new Mixture<>("You should add \"{\" and \"}\" before your message to function as the head.",FontStyle.darkRedStyle));
                                                } else if (rawContent.indexOf("{")>rawContent.indexOf("}")){
                                                    messages.add(new Mixture<>("Illegal message head. You should use it as \"{\"endDate\"}\"",FontStyle.darkRedStyle));
                                                }
                                            } else if (temp<2){
                                                messages.add(new Mixture<>("You should use \"\" as \"message\" to cover your message.",FontStyle.darkRedStyle));
                                            }
                                            break;
                                        case "remove":

                                            break;
                                        default:
                                            break;
                                    }
                                } else {
                                    messages.add(new Mixture<>("You might want to execute commands:", FontStyle.darkRedStyle));
                                    messages.add(new Mixture<>("notification add \"<message>\" <index>", FontStyle.darkRedStyle));
                                    messages.add(new Mixture<>("notification remove <index>", FontStyle.darkRedStyle));
                                }
                        }
                        if (isCommand) {
                            String time = "[" + getTime() + "]";
                            if (dsdFileContent.getLength() != 0)
                                dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
                            //如果不是第一行那就换行
                            dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
                            for (Mixture<String, Style> message : messages) {
//                                dsdFileContent.insertString(dsdFileContent.getLength(), "", FontStyle.plainStyle);
                                dsdFileContent.insertString(dsdFileContent.getLength(), message.getKey(), message.getValue());
                                //如果他正在输入命令的话
                            }
                        }
                    }
                } catch (BadLocationException badLocationException) {
                    //ignore it.
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && isCommand) {
                        dsdInput.remove(0, dsdInput.getLength());
                        isCommand = false;
                    }
                } catch (BadLocationException badLocationException) {
                    //ignore it
                }
            }
        });
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

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String getTime() {
        return sdf.format(System.currentTimeMillis());
    }
}
