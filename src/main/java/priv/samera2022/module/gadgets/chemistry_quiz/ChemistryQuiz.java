package priv.samera2022.module.gadgets.chemistry_quiz;

import javax.swing.*;
import java.util.Random;

public class ChemistryQuiz {
    public static void main(String[] args) {
        quiz();
    }
    public static int quiz(){
        int time = 1;
        for (;;) {
            int random = randomInt(1-1,18-1);//这是为了适配list中元素的序号
            Element element = Element.elements.get(random);
            boolean isCh = new Random().nextBoolean();
            String answer;
            if (isCh){
                answer = JOptionPane.showInputDialog(null,element.getChName()+"的电子排布式是？","第"+time+"次",JOptionPane.QUESTION_MESSAGE);		//输入对话框
            } else {
                answer = JOptionPane.showInputDialog(null,element.getElName()+"的电子排布式是？","第"+time+"次",JOptionPane.QUESTION_MESSAGE);		//输入对话框
            }
            if (!answer.equals(element.getCEE())){
                JOptionPane.showMessageDialog(null,"回答错了！答案应该是"+element.getCEE()+"。你这次答对了"+time+"次！","回答错误",JOptionPane.WARNING_MESSAGE);	//消息对话框
                break;
            }
            time++;
        }
        return time;
    }
    //生成[min,max]内的随机整数
    private static int randomInt(int min,int max){
        return new Random().nextInt(max)%(max-min+1)+min;
    }
}

