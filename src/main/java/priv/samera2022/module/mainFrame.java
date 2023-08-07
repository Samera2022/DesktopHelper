package priv.samera2022.module;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.dnd.DnDConstants;

public class mainFrame {
    public static void main(String[] args) {
        //<---窗体头--->
        JFrame frame = new JFrame();
        frame.setSize(300, 700);
        frame.setLocation(1244, 0);
        frame.setUndecorated(true);
        frame.enableInputMethods(false);
        JPanel totalPanel = new JPanel();
        totalPanel.setSize(300,700);
        totalPanel.setLocation(1244,0);
        //<---INPUT_ANALYZE--->
        DefaultStyledDocument dsdInput = new DefaultStyledDocument(FontStyle.sc);
        JTextPane jtpInput = new JTextPane(dsdInput); // 显示文件内容区域
        DropTarget dtsInput = new DropTarget(DropTarget.INPUT_ANALYZE,dsdInput,jtpInput);
        jtpInput.setEditable(true);
        jtpInput.setText("-->HERE TO INPUT<--");
        ScrollPane sp1 = new ScrollPane();
        sp1.add(jtpInput);
        sp1.setSize(300,100);
        sp1.setLocation(1244,0);
//        sp1.setBounds(1244,0,300,100);
        new java.awt.dnd.DropTarget(jtpInput, DnDConstants.ACTION_COPY_OR_MOVE, dtsInput);
        totalPanel.add(sp1);

        //<---FILE_CONTENT_ANALYZE--->
        DefaultStyledDocument dsdFileContent = new DefaultStyledDocument(FontStyle.sc);
        JTextPane jtpFileContent = new JTextPane(dsdFileContent); // 显示文件内容区域
        DropTarget dtsFileContent = new DropTarget(DropTarget.FILE_CONTENT_ANALYZE,dsdFileContent,jtpFileContent);
        jtpFileContent.setEditable(false);
        ScrollPane sp2 = new ScrollPane();
        sp2.add(jtpFileContent);
        sp2.setBounds(1244,400,300,400);
//        sp.setDropTarget();-----------------------------------------------------------------------------------------------------
        new java.awt.dnd.DropTarget(jtpFileContent, DnDConstants.ACTION_COPY_OR_MOVE, dtsFileContent);
        totalPanel.add(sp2);
        //<---窗体尾--->
        frame.add(totalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /*
    public static void main2(String[] args) {

        //<---窗体头--->
        JFrame frame = new JFrame();
//        frame.setSize(300,900);
//        frame.setLocation(1620,0);

//        frame.setBounds(1620,0,300,900);

        frame.setSize(300, 700);
        frame.setLocation(1244, 0);
        frame.setUndecorated(true);
//        frame.setLayout(new BorderLayout());
        frame.enableInputMethods(false);
        JPanel totalPanel = new JPanel();


        JTextPane jtpInput = new JTextPane();


        //<---DropTarget模块进行--->
        JTextPane jtp = DropTarget.jtp;
//        new java.awt.dnd.DropTarget(jtp, DnDConstants.ACTION_COPY_OR_MOVE, DropTarget.readDoc);
//        jtp.setEditable(false);
//        ScrollPane sp = new ScrollPane();
//        sp.add(jtp);
//        sp.setBounds(1244,100,300,400);
//        totalPanel.add(sp);
        DropTarget.DropTarget(totalPanel);
//        frame.add(sp, BorderLayout.CENTER);


        //<---窗体尾--->
        frame.add(totalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

     */
}
