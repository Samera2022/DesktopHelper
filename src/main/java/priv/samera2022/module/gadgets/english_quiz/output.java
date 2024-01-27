package priv.samera2022.module.gadgets.english_quiz;

import java.util.Scanner;

public class output {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder output = new StringBuilder();
        String answer = "";
        String tips = "";
        while ((!tips.equals("0"))&&(!answer.equals("0"))) {
            System.out.println("Insert answer: ");
            answer = sc.nextLine();
            System.out.println("Insert tips: ");
            tips = sc.nextLine();
            output.append(answer).append("(\"").append(answer).append("\",\"").append(tips).append("\"),");
        }
//        int length = output.toString().length();
//        if (length!=0) output.replace(length-1,length-1,";");
        System.out.println(output);
    }
}
