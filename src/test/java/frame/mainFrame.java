package frame;

import priv.samera2022.module.DropTarget;
import priv.samera2022.module.Logger;
import priv.samera2022.module.Mixture;
import priv.samera2022.module.commands.registry.CommandHeads;
import priv.samera2022.module.file.FileHandler;
import priv.samera2022.module.font.FontStyle;
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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class mainFrame {
    public static Logger logger = new Logger();

    public static DefaultStyledDocument dsdInput = new DefaultStyledDocument(FontStyle.sc);
    public static DefaultStyledDocument dsdFileContent = new DefaultStyledDocument(FontStyle.sc);
    public static DefaultStyledDocument dsdNotification = new DefaultStyledDocument(FontStyle.sc);
    public static final String inputAsst = "-->HERE TO INPUT<--";

    public static void main(String[] args){
        try {
            frame();
        } catch (BadLocationException e){
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }

    public static void frame() throws BadLocationException {
        //警示：对于控件内文字的更改，不应采用xxx.setDocument(xxx)，而应该修改该控件内的DefaultStyledDocument！
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
//        int screenHeight = (int) screenSize.getHeight();
        int x = screenWidth-300;
        //<---窗体头--->
        JFrame frame = new JFrame();
        frame.setSize(300, 700);
        frame.setLocation(x, 0);
        frame.setUndecorated(true);
        frame.enableInputMethods(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setSize(300, 700);
        totalPanel.setLocation(0, 0);
        totalPanel.setLayout(null);
        //修改为绝对布局的，需要对每个组件重新设置Location
        totalPanel.setOpaque(false);

        //<---BACKGROUND--->
        JLabel background = new JLabel(new ImageIcon("D:\\_S_A_M\\Desktop\\1.png"));
        background.setBounds(0,0,300,700);
        background.setOpaque(true);
        background.setIconTextGap(0);

        //<---INPUT_ANALYZE--->
        JTextArea jtaInput = new JTextArea(dsdInput);
        DropTarget dtsInput = new DropTarget(DropTarget.INPUT_ANALYZE, dsdInput, jtaInput);
        jtaInput.setEditable(true);
        jtaInput.setLineWrap(true);
        jtaInput.setWrapStyleWord(true);
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(jtaInput);
        sp1.getViewport().setOpaque(false);
        sp1.setOpaque(false);
        jtaInput.setOpaque(false);
        //        JScrollPane sp1 = new JScrollPane(jtaInput);
        //        ScrollPane sp1 = new ScrollPane();
        //注：JScrollPane的构造方式和ScrollPane的构造方式不一样。
        //        sp1.add(jtaInput);
        //注：JScrollPane在构造的时候必须要传入参数，不像ScrollPane可以后续通过pane.add();的方式再加入组件
        //注：JScrollPane虽然也能使用pane.add();但是似乎不起什么作用。应该使用sp1.setViewportView(Component);
//        sp1.setPreferredSize(new Dimension(301,100));
        sp1.setBounds(0,0,301,100);
        //注：设计非整百数是为了让sp1的右侧能够紧贴窗体，虽然我也不知道为什么会发生这种情况
//        sp1.setLocation(x,0);
        //        sp1.setSize(300,100);
        //        sp1.setBounds(x,0,300,100);
        //注：JScrollPane不能使用ScrollPane的setSize来调整大小，否则大小设置总会出现问题


//        JScrollPane scrollPane = new JScrollPane(jtaInput) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                // 允许背景显示为透明
//            }
//        };
//        scrollPane.setBounds(x, 0, 300, 100);
//        totalPanel.add(scrollPane);

        new java.awt.dnd.DropTarget(jtaInput, DnDConstants.ACTION_COPY_OR_MOVE, dtsInput);
        dsdInput.insertString(0, inputAsst, FontStyle.plainStyle);
//        frame.add(sp1);

        //<---FILE_CONTENT_ANALYZE--->
        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE, dsdFileContent, jtpFileContent);
        jtpFileContent.setEditable(false);
        JScrollPane sp2 = new JScrollPane();
        sp2.setViewportView(jtpFileContent);
        sp2.getViewport().setOpaque(false);
        sp2.setOpaque(false);
        jtpFileContent.setOpaque(false);
        sp2.setBounds(0, 99, 301, 400-40);
        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);

        //<---Countdown--->
        DefaultStyledDocument dsdCountdown = new DefaultStyledDocument(FontStyle.sc);
        JTextPane jtpCountdown = new JTextPane(dsdCountdown);
        LocalDateTime today = LocalDateTime.now();
        logger.info("Current Date: "+today);
        LocalDateTime specificDate = LocalDateTime.of(2025, 6, 7, 0, 0); // 2025年6月7日
        long daysBetween = ChronoUnit.DAYS.between(today, specificDate);
        if (daysBetween>0){
            dsdCountdown.insertString(0,"               ->距离高考还有",FontStyle.plainStyle);
            dsdCountdown.insertString(dsdCountdown.getLength()," "+daysBetween+" ",FontStyle.specialStyle);
            dsdCountdown.insertString(dsdCountdown.getLength(),"天<-",FontStyle.plainStyle);
        } else if (daysBetween >=-2)
            dsdCountdown.insertString(0,"加油！",FontStyle.plainStyle);
            else dsdCountdown.insertString(0,"高考结束了！",FontStyle.plainStyle);
        jtpCountdown.setEditable(false);
//        JScrollPane spCd = new JScrollPane();
////        spCd.add(jtpCountdown);
//        spCd.setViewportView(jtpCountdown);
//        spCd.getViewport().setOpaque(false);
//        spCd.setOpaque(false);
//        jtpCountdown.setOpaque(false);
        jtpCountdown.setEditable(false);
        JScrollPane spCd = new JScrollPane();
        spCd.setViewportView(jtpCountdown);
        spCd.getViewport().setOpaque(false);
        spCd.setOpaque(false);
        jtpCountdown.setOpaque(false);
        spCd.setBounds(0, 460, 301, 40);

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
                dsdNotification.insertString(dsdNotification.getLength(), "\n", FontStyle.plainStyle);
            }
        }
        jtpNotification.setEditable(false);
        JScrollPane sp3 = new JScrollPane();
        sp3.setViewportView(jtpNotification);
        sp3.getViewport().setOpaque(false);
        sp3.setOpaque(false);
        jtpNotification.setOpaque(false);
        sp3.setBounds(0, 497, 301, 204);

        //<---ADDING AREA--->
        totalPanel.add(sp1);
        totalPanel.add(sp2);
        totalPanel.add(spCd);
        totalPanel.add(sp3);
        totalPanel.add(background);

        //<---窗体尾--->
        jtaInput.addKeyListener(new EnterKeyListener());
        jtaInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //假如鼠标点击到该控件内，即焦点位于控件内
                //获取焦点的输出代码在这里
                JTextArea jta = (JTextArea) e.getSource();
                if (jta.getText().equals(inputAsst)||jta.getText().equals(inputAsst+"\n")) {
                    jta.setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    //假如鼠标点击到该控件外，即焦点位于控件外
                    //获取焦点的输出代码在这里
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
        frame.setVisible(true);
        totalPanel.revalidate();
        totalPanel.repaint();
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

    public static void ExceptionHandler(Exception exception){
        CommandHeads.formatter(false,new Mixture<>("Cause: ",FontStyle.plainStyle));
        CommandHeads.formatter(false,new Mixture<>(exception.getCause().getMessage(),FontStyle.darkRedStyle));
        CommandHeads.formatter(false,new Mixture<>("LocalizedMessage: ",FontStyle.plainStyle));
        CommandHeads.formatter(false,new Mixture<>(exception.getLocalizedMessage(),FontStyle.darkRedStyle));
        CommandHeads.formatter(false,new Mixture<>("StackTrace: ",FontStyle.plainStyle));
        CommandHeads.formatter(false,new Mixture<>(Arrays.toString(exception.getStackTrace()),FontStyle.darkRedStyle));
        CommandHeads.formatter(false,new Mixture<>("Suppressed: ",FontStyle.plainStyle));
        CommandHeads.formatter(false,new Mixture<>(Arrays.toString(exception.getSuppressed()),FontStyle.darkRedStyle));
    }

}
