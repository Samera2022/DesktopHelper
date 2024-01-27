package priv.samera2022.module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FuzzyMatcher {
    //真的没有想到，居然在方法的参数列表里使用的参数就是本体。
    //举个例子，这个方法调用了CommandHandler.COMMAND_NAME，在参数里名字为list。那么方法中对于list的修改就是对COMMAND_NAME的直接修改。

    //后记：List<String> cloneList = list;
    //这是一种浅拷贝，得到的将会直接和本体相关联

    //需要深拷贝的话需要用其他方法，网上也有很多方法，但是我认为最简单的还是ArrayList<String> cloneList = new ArrayList(list);

    //克隆的步骤应该放在循环体外面！！！放在里面就失去了remove的效果了！！！
    public static String fuzzyMatch(String target, List<String> list) {
        String output = "";
        ArrayList<String> cloneList = new ArrayList<>(list);
        for (int i = 0; i < target.length(); i++) {
            //i是第几位
            if (cloneList.size() != 1) {
                for (int j = 0; j < cloneList.size(); j++) {
                    String element = cloneList.get(j);
                    if (element.charAt(i) != target.charAt(i) && cloneList.size() != 1) {
                        cloneList.remove(element);
                        j--;
                    } else {
                        output = cloneList.get(0);
                        break;
                    }
                }
            } else {
                //这段代码究竟有没有可能会运行？
                output = cloneList.get(0);
                break;
            }
        }
        return output;
    }

}
