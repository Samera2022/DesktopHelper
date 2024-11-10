////import com.sun.jna.platform.FileUtils;
//
//import priv.samera2022.module.DropTarget;
//import priv.samera2022.module.font.FontStyle;
//import priv.samera2022.module.Info;
//import priv.samera2022.module.Mixture;
//import priv.samera2022.module.commands.CommandHandler;
//import priv.samera2022.module.gadgets.chemistry_quiz.ChemistryQuiz;
//import priv.samera2022.module.notification.Notification;
//import priv.samera2022.module.notification.NotificationContent;
//import priv.samera2022.module.notification.NotificationFileHandler;
//
//import javax.swing.*;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.DefaultStyledDocument;
//import javax.swing.text.Style;
//import java.awt.*;
//import java.awt.dnd.DnDConstants;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
//public class mainFrame {
//    private static boolean isCommand = false;//如果有人不符合所有的已知命令头的话，那就说明他不在输入命令呗
//
//    public static void main(String[] args) throws BadLocationException {
//        frame();
//    }
//
//    public static void frame() throws BadLocationException {
//        //警示：对于控件内文字的更改，不应采用xxx.setDocument(xxx)，而应该修改该控件内的DefaultStyledDocument！
//        int x = 1236;
//        //<---窗体头--->
//        JFrame frame = new JFrame();
//        frame.setSize(300, 700);
//        frame.setLocation(x, 0);
//        frame.setUndecorated(true);
//        frame.enableInputMethods(false);
//        JPanel totalPanel = new JPanel();
//        totalPanel.setSize(300, 700);
//        totalPanel.setLocation(x, 0);
//
//        //<---INPUT_ANALYZE--->
//        DefaultStyledDocument dsdInput = new DefaultStyledDocument(FontStyle.sc);
//        JTextArea jtaInput = new JTextArea(dsdInput); // 显示文件内容区域
//        DropTarget dtsInput = new DropTarget(DropTarget.INPUT_ANALYZE, dsdInput, jtaInput);
//        jtaInput.setEditable(true);
//        jtaInput.setLineWrap(true);
//        jtaInput.setWrapStyleWord(true);
//        ScrollPane sp1 = new ScrollPane();
//        sp1.add(jtaInput);
//        sp1.setBounds(x, 0, 300, 100);
//        new java.awt.dnd.DropTarget(jtaInput, DnDConstants.ACTION_COPY_OR_MOVE, dtsInput);
//        String inputAsst = "-->HERE TO INPUT<--";
//        dsdInput.insertString(0, inputAsst, FontStyle.plainStyle);
//        totalPanel.add(sp1);
//
//        //<---FILE_CONTENT_ANALYZE--->
//        DefaultStyledDocument dsdFileContent = new DefaultStyledDocument(FontStyle.sc);
//        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
//        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE, dsdFileContent, jtpFileContent);
//        jtpFileContent.setEditable(false);
//        ScrollPane sp2 = new ScrollPane();
//        sp2.add(jtpFileContent);
//        sp2.setBounds(x, 400, 300, 400);
//        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);
//        totalPanel.add(sp2);
//
//        //<---Notification--->
//        DefaultStyledDocument dsdNotification = new DefaultStyledDocument(FontStyle.sc);
//        JTextPane jtpNotification = new JTextPane(dsdNotification);
//        if (NotificationFileHandler.notifications.size() != 0) {
//            for (int i = 0; i < NotificationFileHandler.notifications.size(); i++) {
//                Notification notification = NotificationFileHandler.notifications.get(i);
//                if (notification.isFinished()) {
//                    dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
//                } else {
//                    dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
//                }
//                notification.getContent().append(dsdNotification, dsdNotification.getLength());
//                dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.plainStyle);
////                System.out.println("Notification.toString(): "+notification.toString());
////                System.out.println("NotificationContent.contentToString(): "+notification.getContent().contentToString());
////                if (i!=NotificationFileHandler.notifications.size()-1) dsdNotification.insertString(dsdNotification.getLength(),"\n",FontStyle.plainStyle);
//            }
//        }
//        jtpNotification.setEditable(false);
//        ScrollPane sp3 = new ScrollPane();
//        sp3.add(jtpNotification);
//        sp3.setBounds(x, 500, 300, 200);
//        totalPanel.add(sp3);
//
//        //<---窗体尾--->
//        jtaInput.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                //ignore it.
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                //当键盘上的某个键(q w e r etc.)被按下时
//                try {
//                    System.out.println("keyPressed");
//                    String content = ((JTextArea) e.getSource()).getText();
//
//                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                        String[] contents = content.split(" ", 2);
//                        System.out.println(contents[0]);
//                        ArrayList<Mixture<String, Style>> messages = new ArrayList<>();
//                        String _string_switcher_1 = fuzzyMatch(contents[0], null);
//                        //不可用的代码↑
//                        boolean _boolean_switcher_1 = _string_switcher_1.isEmpty();
//                        //判断_string_switcher是不是空的，空的意味着没找到这个指令
//                        String switcher_1 = _boolean_switcher_1 ? contents[0] : _string_switcher_1;
//                        System.out.println("switcher_1: " + switcher_1);
//                        if (!_boolean_switcher_1 && !_string_switcher_1.equals(contents[0])) {
//                            _fuzzyMatchInfo(dsdFileContent,switcher_1);
//                        }
//                        switch (switcher_1) {
//                            case "usage":
//                                isCommand = true;
//                                String targetCommand = contents[1];
//                                if (!targetCommand.contains(" ")) {
//                                    switch (targetCommand) {
//                                        case "print":
//                                            messages.add(new Mixture<>("Usage: print <content>\n", FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("Description: Just to print some words.", FontStyle.plainStyle));
//                                            break;
//                                        case "clear":
//                                            messages.add(new Mixture<>("Usage: clear\n", FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("Description: Clear all the history.", FontStyle.plainStyle));
//                                            break;
////                                        case "del":
////                                        case "delete":
////                                            messages.add(new Mixture<>("Usage: del||delete",FontStyle.plainStyle));
////                                            messages.add(new Mixture<>("Description: Delete a file or a folder.",FontStyle.plainStyle));
////                                            break;
//                                        case "notification":
//                                            messages.add(new Mixture<>("\nUsage: notification <subCommand> \"{(isFinished,startDate,)endDate}<message>\" <index>\n", FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("Description: For more helps of notification, please enter: notification help", FontStyle.plainStyle));
//                                            break;
//                                        case "all":
//                                            messages.add(new Mixture<>("\nCommand List is as followed: \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("1. usage \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("2. exit \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("3. print \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("4. clear \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("5. notification \n",FontStyle.plainStyle));
//                                            messages.add(new Mixture<>("6. ChemistryQuiz ",FontStyle.plainStyle));
//                                            break;
//                                    }
//                                } else messages.add(new Mixture<>("Illegal Command!", FontStyle.darkRedStyle));
//                                break;
//                            case "ChemistryQuiz":
//                                isCommand = true;
//                                dsdInput.remove(0,dsdInput.getLength());
//                                ChemistryQuiz.quiz();
//                                break;
//                            case "exit":
//                                isCommand = true;
//                                System.exit(0);
//                                break;
//                            case "print":
//                                isCommand = true;
//                                messages.add(new Mixture<>(contents[1], FontStyle.plainStyle));
//                                break;
//                            case "clear":
//                                isCommand = true;
//                                dsdFileContent.remove(0, dsdFileContent.getLength());
//                                messages.add(new Mixture<>("Cleared Successfully.", FontStyle.greenStyle));
//                                break;
////                            case "del":
////                            case "delete":
////                                isCommand = true;
////                                File f = new File(contents[1]);
////                                if (!f.exists()) {
////                                    messages.add(new Mixture<>("File or Folder doesn't exist!", FontStyle.darkRedStyle));
////                                } else {
////                                    FileUtils fu = FileUtils.getInstance();
////                                    fu.moveToTrash(new File[]{f});
////                                    if (!f.exists()) {
////                                        messages.add(new Mixture<>("File or Folder deleted successfully.", FontStyle.greenStyle));
////                                    } else {
////                                        messages.add(new Mixture<>("File or Folder didn't delete properly!", FontStyle.darkRedStyle));
////                                    }
////                                }
////                                break;
//                            case "notification":
//                                isCommand = true;
//                                String rawContent = contents[1];
//                                int breakPoint = rawContent.indexOf(" ");
//                                if (breakPoint != -1) {
//                                    String command_2 = rawContent.substring(0, rawContent.indexOf(" "));
//                                    String _string_switcher_2 = fuzzyMatch(command_2, Info.NOTIFICATION_COMMANDS);
//                                    boolean _boolean_switcher_2 = _string_switcher_2.isEmpty();
//                                    String switcher_2 = _boolean_switcher_2 ? command_2 : _string_switcher_2;
//                                    if (!_boolean_switcher_2 && !_string_switcher_2.equals(command_2)) {
//                                        _fuzzyMatchInfo(dsdFileContent,switcher_2);
//                                    }
//                                    //notification <command_2>
//                                    switch (switcher_2) {
//                                        case "add": {
//                                            int temp = rawContent.split("\"").length;
//                                            System.out.println(temp);
//                                            if ((rawContent.indexOf("{") < rawContent.indexOf("}")) && temp >= 2) {
//                                                String message = rawContent.substring(rawContent.indexOf("\"") + 1, rawContent.lastIndexOf("\""));
//                                                String rContent = message.substring(message.indexOf("}") + 1);
//                                                System.out.println(rContent);
//                                                String head = message.substring(message.indexOf("{") + 1, message.indexOf("}"));
//                                                String[] splits = head.split(",");
//
//                                                String startDate;
//                                                boolean isFinished;
//                                                String endDate;
//                                                Notification n;
//
//                                                switch (splits.length) {
//                                                    case 1:
//                                                        endDate = splits[0];
//                                                        n = new Notification(new NotificationContent(rContent), false, getTime(), endDate);
//                                                        break;
//                                                    case 3:
//                                                        startDate = splits[0];
//                                                        isFinished = Boolean.parseBoolean(splits[1]);
//                                                        endDate = splits[2];
//                                                        n = new Notification(new NotificationContent(rContent), isFinished, startDate, endDate);
//                                                        break;
//                                                    default:
//                                                        System.out.println("No Matching Result!");
//                                                        n = null;
//                                                        break;
//                                                }
//                                                if (n != null) {
//                                                    NotificationFileHandler.notifications.add(n);
//                                                    NotificationFileHandler.append(n.toString());
//                                                    int index;
//                                                    if (rawContent.lastIndexOf("\"") + 1 + 1 < rawContent.length()) {
//                                                        index = Integer.parseInt(rawContent.substring(rawContent.lastIndexOf("\"") + 1 + 1));
//                                                        if (NotificationFileHandler.notifications.size() < index) {
//                                                            messages.add(new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
//                                                        } else {
//                                                            NotificationFileHandler.notifications.add(index - 1, n);
//                                                            int length = 0;
//                                                            for (int i = 0; i < index - 1; i++) {
//                                                                Notification nLoop = NotificationFileHandler.notifications.get(i);
//                                                                length += nLoop.getContent().contentToString().length() + 3;
////                                                                System.out.println("length: " + length);
//                                                            }
////                                                            System.out.println("totalLength: " + length);
//                                                            int lengthOfN;//字面意思，为换行所预留的长度
//                                                            if (index != 1) {
//                                                                dsdNotification.insertString(length, "\n", FontStyle.plainStyle);
//                                                                lengthOfN = 1;
//                                                            } else lengthOfN = 0;
//                                                            if (n.isFinished())
//                                                                dsdNotification.insertString(length + lengthOfN, "[√]", FontStyle.greenStyle);
//                                                            else
//                                                                dsdNotification.insertString(length + lengthOfN, "[×]", FontStyle.darkRedStyle);
////                                                            dsdNotification.insertString(length + lengthOfN + 3, "", FontStyle.plainStyle);
//                                                            n.getContent().append(dsdNotification, length + lengthOfN + 3);
//                                                            if (index == 1)
//                                                                dsdNotification.insertString(n.getContent().contentToString().length() + 3, "\n", FontStyle.plainStyle);
////                                                        dsdNotification.insertString(length+n.getContent().contentToString().length()+1+3,"\n",FontStyle.plainStyle);
//                                                        }
//                                                    } else {
//                                                        if (n.isFinished())
//                                                            dsdNotification.insertString(dsdNotification.getLength(), "[√]", FontStyle.greenStyle);
//                                                        else
//                                                            dsdNotification.insertString(dsdNotification.getLength(), "[×]", FontStyle.darkRedStyle);
////                                                        dsdNotification.insertString(dsdNotification.getLength(), "", FontStyle.plainStyle);
//                                                        n.getContent().append(dsdNotification, dsdNotification.getLength());
//                                                        dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.plainStyle);
//                                                        //startDate和endDate暂未加入使用
//                                                    }
//                                                    messages.add(new Mixture<>("Notification added Successfully!", FontStyle.greenStyle));
//                                                } else {
//                                                    messages.add(new Mixture<>("Error In Notification Pattern!", FontStyle.darkRedStyle));
//                                                }
//                                            } else if (rawContent.indexOf("{") >= rawContent.indexOf("}")) {
//                                                if (rawContent.indexOf("{") == rawContent.indexOf("}")) {
//                                                    //意思就是两个都不存在，只有两个都不存在的时候才可能相等均为-1
//                                                    messages.add(new Mixture<>("You should add \"{\" and \"}\" before your message to function as the head.", FontStyle.darkRedStyle));
//                                                } else if (rawContent.indexOf("{") > rawContent.indexOf("}")) {
//                                                    messages.add(new Mixture<>("Illegal message head. You should use it as \"{\"endDate\"}\"", FontStyle.darkRedStyle));
//                                                }
//                                            } else if (temp < 2) {
//                                                messages.add(new Mixture<>("You should use \"\" as \"message\" to cover your message.", FontStyle.darkRedStyle));
//                                            }
//                                            break;
//                                        }
//                                        case "remove": {
//                                            int indexForHuman = Integer.parseInt(contents[1].split(" ", 2)[1]);
//                                            int index = indexForHuman - 1;
//                                            if (NotificationFileHandler.notifications.size() < index) {
//                                                messages.add(new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
//                                            } else {
//                                                Notification backNotification = NotificationFileHandler.notifications.get(index);
//                                                NotificationFileHandler.notifications.remove(index);
//                                                int length = 0;
//                                                for (int i = 0; i < index; i++) {
//                                                    Notification notification = NotificationFileHandler.notifications.get(i);
//                                                    length += notification.getContent().contentToString().length() + 3;
//                                                }
//                                                dsdNotification.remove(length, backNotification.getContent().contentToString().length() + 3);
//                                                FileHandler.deleteLine(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME, indexForHuman);
//                                                messages.add(new Mixture<>("Notification Removed!", FontStyle.greenStyle));
//                                            }
//                                            break;
//                                        }
//                                        case "unfinished":
//                                            _finish(false, Integer.parseInt(contents[1].split(" ", 2)[1]), messages, dsdNotification);
//                                            break;
//                                        case "finished":
//                                            _finish(true, Integer.parseInt(contents[1].split(" ", 2)[1]), messages, dsdNotification);
//                                            break;
//                                        default:
//                                            messages.add(new Mixture<>("No Commands Matches!", FontStyle.darkRedStyle));
//                                            break;
//                                    }
//                                } else {
//                                    messages.add(new Mixture<>("\nYou might want to execute commands:\n", FontStyle.darkRedStyle));
//                                    messages.add(new Mixture<>("notification add \"{heads}<message>\" <index>\n", FontStyle.darkRedStyle));
//                                    messages.add(new Mixture<>("notification remove <index>\n", FontStyle.darkRedStyle));
//                                }
//                        }
//                        if (isCommand) {
////                            String time = "[" + getTime() + "]";
////                            if (dsdFileContent.getLength() != 0)
////                                dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
////                            //如果不是第一行那就换行
////                            dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
//                            _timePrefix(dsdFileContent);
//                            for (Mixture<String, Style> message : messages) {
////                                dsdFileContent.insertString(dsdFileContent.getLength(), "", FontStyle.plainStyle);
//                                dsdFileContent.insertString(dsdFileContent.getLength(), message.getKey(), message.getValue());
//                                //如果他正在输入命令的话
//                            }
//                        }
//                    }
//                } catch (BadLocationException badLocationException) {
//                    //ignore it.
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                try {
//                    if (e.getKeyCode() == KeyEvent.VK_ENTER && isCommand) {
//                        dsdInput.remove(0, dsdInput.getLength());
//                        isCommand = false;
//                    }
//                } catch (BadLocationException badLocationException) {
//                    //ignore it
//                }
//            }
//        });
//        jtaInput.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                //假如鼠标点击到该控件内，即焦点位于控件内
//                System.out.println("focusGained!");
//                JTextArea jta = (JTextArea) e.getSource();
//                if (jta.getText().equals(inputAsst)) {
//                    jta.setText(null);
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                try {
//                    //假如鼠标点击到该控件外，即焦点位于控件外
//                    System.out.println("focusLost!");
//                    JTextArea jta = (JTextArea) e.getSource();
//                    if (jta.getText().isEmpty()) {
//                        dsdInput.insertString(0, inputAsst, FontStyle.plainStyle);//JTextArea并不支持字体颜色
//                    }
//                } catch (BadLocationException badLocationException) {
//                    //ignore it.
//                }
//            }
//        });
//        frame.add(totalPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//
//    private static void _finish(boolean finishingMode, int indexForHuman, ArrayList<Mixture<String, Style>> messages, DefaultStyledDocument dsdNotification) {
//        try {
//            int index = indexForHuman - 1;
//            if (NotificationFileHandler.notifications.size() < index) {
//                messages.add(new Mixture<>("Index Out Of Bounds!", FontStyle.darkRedStyle));
//            } else {
//                Notification previousNotification = NotificationFileHandler.notifications.get(index);
//                previousNotification.setFinished(finishingMode);
//                NotificationFileHandler.notifications.set(index, previousNotification);
//                int length = 0;
//                for (int i = 0; i < index; i++) {
//                    Notification notification = NotificationFileHandler.notifications.get(i);
//                    length += notification.getContent().contentToString().length() + 3;
//                }
//                dsdNotification.replace(length, 3, finishingMode ? "[√]" : "[×]", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle);
//                messages.add(new Mixture<>(finishingMode ? "Notification Task Completed!" : "Notification Task Uncompleted!", finishingMode ? FontStyle.greenStyle : FontStyle.darkRedStyle));
//                __finish(index, previousNotification);
////                FileHandler.deleteLine(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME, indexForHuman);
//            }
//        } catch (BadLocationException e) {
//            //ignore it.
//        }
//    }
//
//    private static void __finish(int lineIndex, Notification subsequentNotification) {
//        try {
//            File f = new File(FileHandler.FOLDER_PATH + FileHandler.NOTIFICATION_NAME);
//            if (f.isFile() && f.exists()) {
//                InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
//                BufferedReader br = new BufferedReader(isr);
//                String content;
//                ArrayList<String> allContent = new ArrayList<>();
//                while ((content = br.readLine()) != null) {
//                    allContent.add(content);
//                }
//                allContent.set(lineIndex, subsequentNotification.toString());
//                br.close();
//                isr.close();
//                FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8, false);
//                BufferedWriter bw = new BufferedWriter(fw);
//                StringBuilder processedContent = new StringBuilder();
//                for (int i = 0; i < allContent.size(); i++) {
//                    String line = allContent.get(i);
//                    processedContent.append(line).append(i == allContent.size() - 1 ? "" : "\n");
//                }
//                bw.write(processedContent.toString());
//                bw.close();
//                fw.close();
//            }
//        } catch (IOException e) {
//            //ignore it.
//        }
//    }
//
//
//    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    private static String getTime() {
//        return sdf.format(System.currentTimeMillis());
//    }
//
//    private static void _timePrefix(DefaultStyledDocument dsdFileContent) throws BadLocationException {
//        String time = "[" + getTime() + "]";
//        if (dsdFileContent.getLength() != 0)
//            dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
//        //如果不是第一行那就换行
//        dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
//    }
//
//    private static void _fuzzyMatchInfo(DefaultStyledDocument dsdFileContent, String command) throws BadLocationException {
//        _timePrefix(dsdFileContent);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\nCouldn't find that command! Guess you want to use command \"", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\nExecuting Command \"", FontStyle.blueStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), command, FontStyle.darkRedStyle);
//        dsdFileContent.insertString(dsdFileContent.getLength(), "\"!", FontStyle.blueStyle);
//    }
//
//    private static String fuzzyMatch(String target, ArrayList<String> list) {
//        String output = "";
//        for (int i = 0; i < target.length(); i++) {
//            //i是第几位
//            if (list.size() != 1) {
//                for (int j = 0; j < list.size(); j++) {
//                    String element = list.get(j);
//                    if (element.charAt(i) != target.charAt(i) && list.size() != 1) {
//                        list.remove(element);
//                        j--;
//                    } else {
//                        output = list.get(0);
//                        break;
//                    }
//                }
//            } else {
//                //这段代码究竟有没有可能会运行？
//                output = list.get(0);
//                break;
//            }
//        }
//        return output;
//    }
//}
