package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {

    public static void main(String[] args) {
        String input = "del properties  ";
        List<String> result = splitAndRemoveSpaces(input);
        for (String content : result)
            System.out.println("\""+content+"\"");
    }

    public static List<String> splitAndRemoveSpaces(String input) {
        // 将字符串按照空格分割成单词，并将结果转换为ArrayList
        List<String> result = new ArrayList<>();
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

}
