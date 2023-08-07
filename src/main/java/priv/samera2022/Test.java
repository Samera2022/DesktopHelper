package priv.samera2022;

import priv.samera2022.module.Info;

public class Test {
    public static void main2(String[] args) {
        double a = 1L;
        int b = 1;
        String content = "D:/_S_A_M/Desktop/Last Chance.jpeg";
        System.out.println(Info.picSuffix.stream().anyMatch(e->e.equals(content.substring(content.indexOf('.')))));
    }
    public static void main(String[] args) {
        String test = "asd: 999";
        System.out.println(test.substring(0,test.indexOf(": ")));
    }
}
