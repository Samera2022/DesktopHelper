package test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String str = "del (A C D)";
        output(str);
//        List<String> result = output(str);
//        System.out.println(result);
    }

    private static void output(String str){
        String copy = str;
        List<String> result = new ArrayList<>();
        for (String bracket : findBracket(str)) {
            int index = copy.indexOf(bracket);
            copy = copy.substring(0,index-1) + copy.substring(index+1+bracket.length());
        }
        System.out.println(copy);
        result = removeSpaces(copy);
        for (String content : result)
            System.out.println("\""+content+"\"");
        for (int i = 0; i<result.size(); i++){

        }
    }

    private static List<String> findBracket(String str) {
        List<String> result = new ArrayList<>();
        String pattern = "\\(.*?\\)"; // 正则表达式，匹配任意字符在'('和')'之间
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        while (m.find()) {
            String matchedText = m.group(); // 获取匹配的字符串
            String cleanText = matchedText.replace("(", "").replace(")", ""); // 去除'('和')'
            result.add(cleanText); // 添加去除'('和')'后的字符串到结果列表中
        }
        return result;
    }

    private static List<String> removeSpaces(String input) {
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