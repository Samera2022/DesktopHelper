package priv.samera2022.module.event;

import priv.samera2022.module.font.FontStyle;
import priv.samera2022.module.commands.CommandHandler;
import priv.samera2022.module.commands.registry.CommandHeads;
import priv.samera2022.module.config.ConfigHandler;
import priv.samera2022.module.event.api.Event;
import priv.samera2022.module.event.events.EnterTypedKeyEvent;
import priv.samera2022.module.mainFrame;

import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventBus {
    public EventBus(){

    }
    public static void register(Event event){
        if (event instanceof EnterTypedKeyEvent){
            KeyEvent keyEvent = ((EnterTypedKeyEvent) event).getKeyEvent();
            String content = ((JTextComponent)keyEvent.getSource()).getText();
            mainFrame.logger.debug("Is Current Command Output: "+ConfigHandler.CONFIG.isCommandOutput());
            if (ConfigHandler.CONFIG.isCommandOutput()) append(content);
            ArrayList<String> commands = split(content);
            CommandHandler.handleCommands(commands);
        }
    }

    //向原有输出框追加输出原指令
    private static void append(String content){
        try {
            CommandHeads._timePrefix(mainFrame.dsdFileContent);
            mainFrame.dsdFileContent.insertString(mainFrame.dsdFileContent.getLength(),"\n[Command] ", FontStyle.blueStyle);
            mainFrame.dsdFileContent.insertString(mainFrame.dsdFileContent.getLength(),content, FontStyle.blackStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
            mainFrame.ExceptionHandler(e);
        }
    }

    //System.out.println("Command Content: "+content);

    //方法有问题。bracket中的列表和split出来的列表应该同属于str，但是并未合并成一个commands列表输出。
    private static ArrayList<String> split(String str){
        String copy = str;
        ArrayList<String> bracket = findBracket(str);
        for (String content : bracket) {
            int index = copy.indexOf(content);
//            copy = copy.substring(0,index-1);//此时copy已更新，不能再在下一行代码用index
//            if (!copy.endsWith(")")) copy = copy + copy.substring(copy.indexOf(content)+1+content.length());
//            System.out.println("index+content.length()==copy.length() : "+(index+content.length()==copy.length()));
//            System.out.println("length : "+copy.length());
//            System.out.println("index+content.length()-1 : "+ (index+content.length()));
//            System.out.println("charAt : "+copy.charAt(index+content.length()-1));
            if (!copy.endsWith(")")) copy = copy.substring(0,index-1) + copy.substring(index+content.length());
            else if (copy.endsWith(")")&&index+content.length()==copy.length()) copy = copy.substring(0,index-1);
//            System.out.println("copy : "+copy);
        }
        ArrayList<String> split = removeSpaces(copy);

        HashMap<String,Integer> map = new HashMap<>();
//        for (String content : split) {
//            map.put(content,str.indexOf(content));
//        }
        split.forEach(content -> map.put(content, str.indexOf(content)));//用于得到每一个元素在str中的位置

//        //这种方法有一定问题，只能适用于特殊情况，即没有内容重复的情况下可以使用。如果出现了字段内容发生重复，就会出现问题。
//        //例如1 (1)，这时候字段排序就会出现问题
//        //现在应该解决了吧？向bracket得到的内容前后都加括号，再把他放在里面进行检索。
//        ArrayList<String> _bracket = new ArrayList<>();
//        bracket.stream().map(item -> "(" + item + ")").forEach(_bracket::add);//用于在bracket元素前后加括号
//        for (String content : _bracket) {
//            map.put(content,str.indexOf(content));
//        }
        bracket.forEach(content -> map.put(content, str.indexOf(content)));//用于得到每一个元素在str中的位置

        // 使用流和Comparator对键值对进行排序
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<>(map.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());

        // 输出排序后的键
        ArrayList<String> sortedKeys = new ArrayList<>();
        sortedList.forEach(entry -> {
            String content = entry.getKey();
            //去除前缀和后缀的括号
            if (content.startsWith("("))
                content = content.substring(1);
            if (content.endsWith(")"))
                content = content.substring(0,content.length()-1);
            sortedKeys.add(content);
        });
        return sortedKeys;
    }

    //得到所有()中的所有内容
    private static ArrayList<String> findBracket(String str) {
        ArrayList<String> result = new ArrayList<>();
        String pattern = "\\(.*?\\)"; // 正则表达式，匹配任意字符在'('和')'之间
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        while (m.find()) {
            String matchedText = m.group(); // 获取匹配的字符串
//            String cleanText = matchedText.replace("(", "").replace(")", ""); // 去除'('和')'
//            result.add(cleanText); // 添加去除'('和')'后的字符串到结果列表中
            //这一步不再有必要，因为
            result.add(matchedText);
        }
        return result;
    }

    //去除数组中内容为空的字符串
    private static ArrayList<String> removeSpaces(String input) {
        // 将字符串按照空格分割成单词，并将结果转换为ArrayList
        ArrayList<String> result = new ArrayList<>();
        String[] words = input.split("\\s+"); // 使用正则表达式"\s+"来匹配一个或多个空格
        for (String word : words) {
            if (!word.isEmpty()) { // 去除空字符串
//                if (word.contains(" ")) word = word.substring(word.indexOf(" ")+1); // 去除字符串两端的空格
                word = word.trim();
                result.add(word);
            }
        }
        return result;
    }

    @Deprecated
    public static ArrayList<String> split_deprecated(String content) {
        String copy = content;
        ArrayList<String> commands = new ArrayList<>();
        while (copy.length()>0){
                if ((copy.indexOf("\"") > copy.indexOf(" ")&&copy.contains(" ")&&copy.contains("\""))||(copy.contains(" ")&&!copy.contains("\""))) {
                    commands.add(copy.substring(0, copy.indexOf(" ")));
                    copy = copy.substring(copy.indexOf(" ") + 1);
                } else if ((copy.indexOf("\"") < copy.indexOf(" ")&&copy.contains(" ")&&copy.contains("\""))||(copy.contains("\"")&&!copy.contains(" "))){
                    copy = copy.substring(copy.indexOf("\"") + 1);
                    commands.add("\"" + copy.substring(0, copy.indexOf("\"")) + "\"");
                    if (copy.charAt(copy.length()-1)=='\"') {
                        copy = "";
                    } else {
                        copy = copy.substring(copy.indexOf("\"")+2);
                    }
                } else if (!copy.contains(" ")&&!copy.contains("\"")) {
                    commands.add(copy);
                    copy = "";
                }
        }
        return commands;
    }
}
