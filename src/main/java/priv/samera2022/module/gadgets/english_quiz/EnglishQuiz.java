package priv.samera2022.module.gadgets.english_quiz;

import javax.swing.*;
import java.util.Random;

public class EnglishQuiz {
    public static void main(String[] args) {
        quiz();
    }
    public static int quiz(){
        int time = 1;
        for (;;) {
            int random = randomInt(1-1,Source.sources.size()-1);
            Source source = Source.sources.get(random);
            String answer = JOptionPane.showInputDialog(null, source.getTips(),"第"+time+"次",JOptionPane.QUESTION_MESSAGE);		//输入对话框
            System.out.println(answer);
            if (!answer.equals(source.getAnswer())){
                JOptionPane.showMessageDialog(null,"回答错了！答案应该是"+ source.getAnswer()+"。","回答错误",JOptionPane.WARNING_MESSAGE);	//消息对话框
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

