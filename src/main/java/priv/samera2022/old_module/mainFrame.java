package priv.samera2022.old_module;

import priv.samera2022.module.DropTarget;
import priv.samera2022.module.FontStyle;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

public class mainFrame {
    public static void main(String[] args) throws BadLocationException {
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
        sp1.setSize(300, 100);
        sp1.setLocation(x, 0);
        new java.awt.dnd.DropTarget(jtaInput, DnDConstants.ACTION_COPY_OR_MOVE, dtsInput);
//        DefaultStyledDocument dsdInputAsst = new DefaultStyledDocument();
//        try {
//            dsdInputAsst.insertString(0,"-->HERE TO INPUT<--",FontStyle.greyStyle);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
//        jtpInput.setDocument(dsdInputAsst);
        String inputAsst = "-->HERE TO INPUT<--";
        dsdInput.insertString(0,inputAsst,FontStyle.plainStyle);

//        //<---INPUT_ASSISTANCE>
//        DefaultStyledDocument dsdInputAsst = new DefaultStyledDocument();
//        try {
//            dsdInputAsst.insertString(0,"-->HERE TO INPUT<--",FontStyle.greyStyle);
//        } catch (BadLocationException e) {
//            e.printStackTrace();
//        }
//        JTextPane jtpInputAsst = new JTextPane();
//        jtpInputAsst.setDocument(dsdInputAsst);
//        jtpInputAsst.setSize(100,50);
//        jtpInputAsst.setLocation(1244,0);
//        jtpInputAsst.setEditable(false);
//        jtpInputAsst.setVisible(true);
//        InputMethodListener iml = new InputMethodListener() {
//            @Override
//            public void inputMethodTextChanged(InputMethodEvent event) {
//                if (event.getText().getEndIndex()==0) jtpInputAsst.setVisible(true);
//                else jtpInputAsst.setVisible(false);
//            }
//
//            @Override
//            public void caretPositionChanged(InputMethodEvent event) {
////no changes
//            }
//        };
//        jtpInputAsst.addInputMethodListener(iml);
//        totalPanel.add(jtpInputAsst);
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
                    boolean isCommand = false;//如果有人不符合所有的已知命令头的话，那就说明他不在输入命令呗
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        String output = "";//output待定输出
                        String[] contents = content.split(" ",2);
                        switch (contents[0]) {
                            case "print":
                                isCommand = true;
                                output = contents[1];
                                break;
                            case "clear":
                                isCommand = true;
                                dsdFileContent.remove(0,dsdFileContent.getLength());
                                output = "Cleared Successfully";
                                break;
                            default:
                                break;
                        }
                        if (isCommand) {
//                            dsdInput.remove(0,dsdInput.getLength());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String time = "[" + sdf.format(System.currentTimeMillis()) + "]";
                            if (dsdFileContent.getLength() != 0)
                                dsdFileContent.insertString(dsdFileContent.getLength(), "\n", FontStyle.plainStyle);
                            //如果不是第一行那就换行
                            dsdFileContent.insertString(dsdFileContent.getLength(), time + " ", FontStyle.greyStyle);
                            dsdFileContent.insertString(dsdFileContent.getLength(), output, FontStyle.plainStyle);
                            //如果他正在输入命令的话
                        }
                    }
//                    if (e.getKeyCode() == KeyEvent.VK_ENTER){
//                        dsdInput.remove(0,dsdInput.getLength());
//                    }
//                    else {
//                        dsdInput.insertString(dsdInput.getLength(), "\n", FontStyle.plainStyle);
//                        //如果他不在输入命令的话
//                    }
                } catch (BadLocationException badLocationException) {
                    //ignore it.
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        dsdInput.remove(0, dsdInput.getLength());
                    }
                } catch (BadLocationException badLocationException){
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
//                    System.out.println("Text: " + jta.getText());
                    if (jta.getText().isEmpty()) {
//                        System.out.println("It is Empty!");
//                        System.out.println("Text: " + jta.getText());
                        dsdInput.insertString(0,inputAsst,FontStyle.plainStyle);//JTextArea并不支持字体颜色
//                        System.out.println("Text After Setting Document: " + jta.getText());
//                        jta.setText(inputAsst);
//                        System.out.println("Text After Setting Text: " + jta.getText());
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
