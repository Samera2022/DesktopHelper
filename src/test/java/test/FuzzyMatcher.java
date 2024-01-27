package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuzzyMatcher {
    //"delete","print","store","broaden","quiz"
    private static final ArrayList<String> COMMAND_NAME = new ArrayList<>();
    static {
        COMMAND_NAME.add("delete");
        COMMAND_NAME.add("print");
        COMMAND_NAME.add("store");
        COMMAND_NAME.add("broaden");
        COMMAND_NAME.add("quiz");
    }
    public static String fuzzyMatch(String target, ArrayList<String> list) {
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

    public static void main(String[] args) {
        System.out.println(fuzzyMatch("delete",COMMAND_NAME));
        System.out.println(fuzzyMatch("quiz",COMMAND_NAME));
        System.out.println(fuzzyMatch("delete",COMMAND_NAME));
    }
}
