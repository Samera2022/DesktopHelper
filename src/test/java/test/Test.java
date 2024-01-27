package test;

import priv.samera2022.module.FontStyle;
import priv.samera2022.module.notification.NotificationContent;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test {
    private static String content = "Hello![RED]That's good![BLUE]GOOD!";

//    public static void main(String[]args) throws BadLocationException {
//
        //        int[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][] a =
//                new int[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]{};
//        deleteToRecycleBin("D:/_S_A_M/Desktop/正视图 - Copy.jpg");

//        String str1 = "Hello111111 World1";
//        String str2 = "123World Hello111111";
//        System.out.println("公共部分的长度: " + getCommonSubstringLength(str1, str2));
//        append();
//        Method method = mainFrame.class.ge;

//        String target = "114514";
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("11451451", "11315123", "91232342", "37293412", "114513", "115314"));
//        System.out.println(test7(target,list));
//        for (String element: list) {
//            System.out.println(element);
//        }
//    }

//public static void main(String[] args) {
//    ArrayList<String> list = new ArrayList<>();
//    list.add("store");
//    list.add("ChemistryQuiz");
//    list.add("print");
//    list.add("notification");
//
//    System.out.println(fuzzyMatch("Che", list));  // Expected output: ABCFS
//}
public static void deleteToRecycleBin(String filePath) {
    Path path = Paths.get(filePath);
    try {
        // 判断文件是否存在
        if (Files.exists(path)) {
            // 创建临时文件，用于将原文件内容复制到回收站
            Path tempPath = Files.createFile(path.getParent().resolve(path.getFileName().toString() + ".tmp"));
            Files.copy(path, tempPath, StandardCopyOption.REPLACE_EXISTING);
            // 删除原文件，并将其移动到回收站
            Files.move(tempPath, path.getParent().resolve(path.getFileName().toString() + ".bak"));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    //只是j--的位置有点变化，不知道有没有区别
    private static String fuzzyMatch2(String target, ArrayList<String> list) {
        String output = "";
        for (int i = 0; i < target.length(); i++) {
            //i是第几位
            if (list.size() != 1) {
                boolean temp = false;
                for (int j = 0; j < list.size(); j++) {
                    if (temp) {
                        j--;
                        temp = false;
                    }
                    String element = list.get(j);
                    if (element.charAt(i) != target.charAt(i) && list.size() != 1) {
                        list.remove(element);
                        temp = true;
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
    public static int getCommonSubstringLength(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static String test7(String target, ArrayList<String> list){
        String output = "";
        for (int i = 0; i<target.length(); i++){
            //i是第几位
            if (list.size()!=1) {
                for (int j = 0; j < list.size(); j++) {
                    String element = list.get(j);
                    if (element.charAt(i) != target.charAt(i) && list.size()!=1) {
                        list.remove(element);
                        j--;
                    } else {
                        output = list.get(0);
                        break;
                    }
                }
            }
            else {
                //这段代码究竟有没有可能会运行？
                output = list.get(0);
                break;
            }
        }
        return output;
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
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
//        arr.add(3,"test");
        arr.remove(3);
        for (int a : arr) {
            System.out.println(a);
        }
//        System.out.println(arr.get(3));
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

//    public static void main(String[] args) {
//        ArrayList<String> split = new ArrayList<>();
//        ArrayList<String> bracket = new ArrayList<>();
//        String str = "1 35 7 0 3";
//        split.add("1");
//        split.add("7");
//        split.add("0");
//        bracket.add("35");
//        HashMap<String,Integer> map = new HashMap<>();
//        test8(split,bracket,map,str);
//    }
    public static void test8(ArrayList<String> split,ArrayList<String> bracket,HashMap<String,Integer> map, String str){
        split.forEach(content -> map.put(content, str.indexOf(content)));
        bracket.forEach(content -> map.put(content, str.indexOf(content)));
        for (String key : map.keySet()){
            System.out.println(key+":"+map.get(key));
        }
    }

    public static void main2(String[] args) {
        String element = "Tips: Question\nAnswers: Answers.";
        String tips = element.substring(element.indexOf("Tips: ")+"Tips: ".length(),element.indexOf("Answers: "));
        String answer = element.substring(element.indexOf("Answers: ")+"Answers: ".length());
        System.out.println(tips+"\n"+answer);
    }

    public static void main3(String[] args) {
        System.out.println(JOptionPane.showConfirmDialog(null,"文件已存在！是否覆盖该文件？","异常警示", JOptionPane.OK_CANCEL_OPTION));
    }

    public static void main4(String[] args) {
//        String a = "A Test";
//        ArrayList<String> list = new ArrayList<>(Arrays.asList(a.split("b")));
//        list.forEach(System.out::println);

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int screenWidth = (int) screenSize.getWidth();
//        int screenHeight = (int) screenSize.getHeight();
//        System.out.println(screenWidth);
        String tips = JOptionPane.showInputDialog(null, "请输入提示", "第" + 1 + "次输入", JOptionPane.QUESTION_MESSAGE);
        System.out.println(tips);
    }
    public static void main5(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("a");
        arr.add("b");
        arr.add("c");
        del2(arr);
        System.out.println("---");
        print(arr);
    }
    private static void del(ArrayList<String> arr){
        String[] _arr = new String[arr.size()];
        arr.toArray(_arr);
        String[] new_arr = Arrays.copyOf(_arr,_arr.length);
        ArrayList<String> arr3 =  new ArrayList<>(Arrays.asList(new_arr));
        arr3.remove(1);
        print(arr);
    }
    private static void del2(ArrayList<String> arr){
        ArrayList<String> arr3 = new ArrayList<>(arr);
        arr3.remove(0);
        print(arr);
    }
    private static void print(ArrayList<String> arr) {
        arr.forEach(System.out::println);
    }

    public static void main6(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.remove(1);
        arr.add(1,2);
        arr.forEach(System.out::println);
    }

    public static void main7(String[] args) {
        String content = "{\n  \"model\": \""+"\",\n  \"messages\": [{\"role\": \""+"\", \"content\": \""+"\"}]\n}";
        String a1 = content.substring(0,61);//61之后是\n结束
        System.out.println(a1);
    }

    public static void main(String[] args) {
//        String replyContent = "{\n" +
//                "  \"id\": \"chatcmpl-8lXIsOspyxkeNtEVYYQhPyoZ4SO7C\",\n" +
//                "  \"object\": \"chat.completion\",\n" +
//                "  \"created\": 1706340126,\n" +
//                "  \"model\": \"gpt-3.5-turbo-1106\",\n" +
//                "  \"choices\": [\n" +
//                "    {\n" +
//                "      \"index\": 0,\n" +
//                "      \"message\": {\n" +
//                "        \"role\": \"assistant\",\n" +
//                "        \"content\": \"Hi there! How can I assist you today?\"\n" +
//                "      },\n" +
//                "      \"logprobs\": null,\n" +
//                "      \"finish_reason\": \"stop\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"usage\": {\n" +
//                "    \"prompt_tokens\": 9,\n" +
//                "    \"completion_tokens\": 10,\n" +
//                "    \"total_tokens\": 19\n" +
//                "  },\n" +
//                "  \"system_fingerprint\": \"fp_b57c83dd65\"\n" +
//                "}";
        String replyContent = "{\"id\":\"chatcmpl-8laSFCPBL2HtmhOOWyhs2dznjbArE\",\"object\":\"chat.completion\",\"created\":1706352239,\"model\":\"gpt-3.5-turbo-1106\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"我是基于大规模预训练语言模型的。这种模型能够通过大量的文本数据进行训练，从而学习到丰富的语言知识和语义理解能力，可以用于生成自然语言文本、回答问题和进行对话等多种任务。\"},\"logprobs\":null,\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":18,\"completion_tokens\":86,\"total_tokens\":104},\"system_fingerprint\":\"fp_b57c83dd65\"}\n";
        String reply = replyContent.substring(replyContent.indexOf("\",\"content\":\"")+13,replyContent.indexOf('}')-1);
        System.out.println(reply);
}
//    public static void main(String[] args) {
//        Body body = new Body();
//        body.setTemperature(1.1);
//        body.setModel("gpt");
//        Message message = new Message();
//        message.setRole("assistant");
//        message.setContent("Hello");
//        body.setMessage(message);
//        System.out.println(body.toString());
//    }
//    private static ArrayList copyArrayList(ArrayList<String> input){
//        return new ArrayList<>(input);
//    }
}