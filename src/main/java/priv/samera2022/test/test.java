package priv.samera2022.test;

import priv.samera2022.module.FontStyle;
import priv.samera2022.module.notification.NotificationContent;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.Arrays;

public class test {
    private static String content = "Hello![RED]That's good![BLUE]GOOD!";
    public static void main(String[]args) throws BadLocationException {
//        append();
        test6();
    }
    public static void test6(){
        String output = "1`4514\n";
        System.out.println(output.substring(0,output.lastIndexOf("\n")));
    }
    public static void test5() throws BadLocationException {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(100,200,300,400);
        JTextPane jtp = new JTextPane();
        DefaultStyledDocument dsd = new DefaultStyledDocument(FontStyle.sc);
        dsd.insertString(dsd.getLength(),"test1",FontStyle.greenStyle);
        dsd.insertString(dsd.getLength(),"test2",FontStyle.blackStyle);
        jtp.setDocument(dsd);
        jFrame.add(jtp);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void test4(){
        ArrayList arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        arr.add(3,"test");
        System.out.println(arr.get(3));
    }
    public static void test3(){
        String content = "11111[][][][]1? 1";
        System.out.println(content.substring(content.indexOf("?")+1+1));
        System.out.println("Index: "+(content.indexOf("?")+1+1));
        System.out.println("Length: "+content.length());
    }
    public static void test2(){
        ArrayList<String> arr = new ArrayList<>();
        arr.addAll(Arrays.asList(new String[]{"1","2","3"}));
    }
    public static void test(){
        NotificationContent nc = new NotificationContent("[RED]I have a dream, that one day,[BLACK] the son of the slave.[GREY]");
        System.out.println(nc.contentToString());
    }
    public static void append() {
        String copy = content;
        int colorHead = copy.indexOf("[");
        String preColorContent = copy.substring(0,colorHead);
        if (!preColorContent.isEmpty()) {
            copy = copy.substring(colorHead);
        }
        while (!copy.isEmpty()){
            int colorEnd = copy.indexOf("]");
            Style style = FontStyle.blackStyle;
            String colorType = copy.substring(1,colorEnd);
            System.out.println("ColorType: "+colorType);
            switch (colorType){
                //也许可以用style.getName()然后遍历。
                case "blue":
                case "BLUE":
                    style = FontStyle.blueStyle;
                    break;
                case "dark_red":
                case "DARK_RED":
                    style = FontStyle.darkRedStyle;
                    break;
                case "yellow":
                case "YELLOW":
                    style = FontStyle.yellowStyle;
                    break;
                case "green":
                case "GREEN":
                    style = FontStyle.greenStyle;
                    break;
                case "grey":
                case "GREY":
                    style = FontStyle.greyStyle;
                    break;
                case "black":
                case "BLACK":
                    style = FontStyle.blackStyle;
                    break;
                default:
                    for (int i = 0; i<FontStyle.colorStyles.length; i++) {
                        Style element = FontStyle.colorStyles[i];
                        if (colorType.equals(element.getName())){
                            style = element;
                        } else if (i==FontStyle.colorStyles.length-1) {
                            style = FontStyle.blackStyle;
                            System.out.println("Color Not Found.");
                            break;
                        }
                    }
                    break;
            }
            copy = copy.substring(colorEnd);
            int nextColorStart = copy.indexOf("[");
            String content = copy.substring(1,nextColorStart!=-1?nextColorStart:copy.length());
            System.out.println("content: "+content);
            if (nextColorStart!=-1) copy = copy.substring(nextColorStart);
            else copy = "";
        }
    }
}
