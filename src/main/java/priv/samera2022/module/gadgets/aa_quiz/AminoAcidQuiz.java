package priv.samera2022.module.gadgets.aa_quiz;

import priv.samera2022.module.Mixture;
import priv.samera2022.module.gadgets.chemistry_quiz.Element;

import javax.swing.*;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.Random;

public class AminoAcidQuiz {
    public static void main2(String[] args) {
//        quiz();
        for (;;){
            int random = randomInt(1-1,AminoAcid.amino_acids.size());//这是为了适配list中元素的序号
            AminoAcid aa = AminoAcid.amino_acids.get(random);
            System.out.println(aa.getTribleName());
            if (aa.getTribleName().equals("Val")) break;
        }
    }
    public static int structureQuiz(boolean isBreak){
        int time = 1;
        int total = 60; //一共有60种题目组合，本来想做让所有题目都过一遍的，但是想了想逻辑比较复杂，还是之后再做吧
        ImageFrame frame = new ImageFrame();
        frame.setVisible(true);
        for (;;) {
            int random = randomInt(1-1,AminoAcid.amino_acids.size());//这是为了适配list中元素的序号
            AminoAcid aa = AminoAcid.amino_acids.get(random);
            frame.updatePic("D:/_S_A_M/Files/AppData/DesktopHelper/quizzes/resources/amino_acids/"+aa.getSingleName()+".png");
            int randomInt2 = randomInt(1,3);//次项类型
            String q2;
            switch (randomInt2){
                case 1:
                    q2 = "中文名";
                    break;
                case 2:
                    q2 = "三字母缩写";
                    break;
                case 3:
                    q2 = "单字母缩写";
                    break;
                default:
                    q2 = null;
                    break;
            }
            String an = switcher(randomInt2,aa);
            String answer = JOptionPane.showInputDialog(null,"该物质的"+q2+"是？","第"+time+"次",JOptionPane.QUESTION_MESSAGE);
            if (!answer.equals(an)){
                JOptionPane.showMessageDialog(null,"回答错了！答案应该是"+an+"。你这次答对了"+time+"次！","回答错误",JOptionPane.WARNING_MESSAGE);	//消息对话框
                if (isBreak) break;
            }
            if ((!isBreak)&&answer.equals("exit")) break;
            time++;
        }
        frame.dispose();
        return time;
    }
    public static int plainQuiz(boolean isBreak){
        int time = 1;
        for (;;) {
            int random = randomInt(1-1,AminoAcid.amino_acids.size());//这是为了适配list中元素的序号
            AminoAcid aa = AminoAcid.amino_acids.get(random);
            int randomInt1 = randomInt(1,4);//首项类型
            int randomInt2 = randomInt(1,3);//次项类型
            while (randomInt2==randomInt1) { randomInt2 = randomInt(1,3); }
            String q1 = switcher(randomInt1,aa);
            String q2;
            switch (randomInt2){
                case 1:
                    q2 = "中文名";
                    break;
                case 2:
                    q2 = "三字母缩写";
                    break;
                case 3:
                    q2 = "单字母缩写";
                    break;
                case 4:
                    q2 = "英文名";
                    break;
                default:
                    q2 = null;
                    break;
            }
            String an = switcher(randomInt2,aa);
            String answer = JOptionPane.showInputDialog(null,q1+"的"+q2+"是？","第"+time+"次",JOptionPane.QUESTION_MESSAGE);
            if (!answer.equals(an)){
                JOptionPane.showMessageDialog(null,"回答错了！答案应该是"+an+"。你这次答对了"+time+"次！","回答错误",JOptionPane.WARNING_MESSAGE);	//消息对话框
                if (isBreak) break;
            }
            if ((!isBreak)&&answer.equals("exit")) break;
            time++;
        }
        return time;
    }
    private static String switcher(int type, AminoAcid aa){
        switch (type){
            case 1:
                return aa.getChName();
            case 2:
                return aa.getTribleName();
            case 3:
                return aa.getSingleName();
            case 4:
                return aa.getEnName();
            default:
                return null;
        }
    }
    //生成[min,max]内的随机整数
    private static int randomInt(int min,int max){
        return new Random().nextInt(max)%(max-min+1)+min;
    }
}

