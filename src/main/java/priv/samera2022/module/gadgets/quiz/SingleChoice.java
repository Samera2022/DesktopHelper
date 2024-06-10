package priv.samera2022.module.gadgets.quiz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleChoice {
    private String question;
    private String[] answers;
    private int[] indexes;//the indexes of correct answers
    private String title;
    public SingleChoice(String question, String[] answers, int[] indexes) {
        this.question = question;
        this.answers = answers;
        this.indexes = indexes;
    }
    public SingleChoice(String question, String[] answers, int[] indexes, String title) {
        this.question = question;
        this.answers = answers;
        this.indexes = indexes;
        this.title = title;
    }
    public boolean exam() {
        AtomicBoolean answer = new AtomicBoolean(true);
        int length = 0; //length是指全体选项所有字符加在一起的长度，便于后续判断窗体大小
        JFrame exam = new JFrame(title==null?"":title);
        exam.setBounds(500, 300, 460, 250);
        exam.setLayout(new GridLayout(3, 1));
        exam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel JP = new JPanel();
        // 第一题
        JTextField Q1 = new JTextField(question);
        Q1.setEditable(false);
        Q1.setBackground(Color.WHITE);
        ArrayList<JRadioButton> arr = new ArrayList<>();
        ButtonGroup BG1 = new ButtonGroup();
        for (int i = 0; i<answers.length; i++){
            String text = letter(i)+". "+answers[i];
            JRadioButton jrb = new JRadioButton(text);
            length += text.length();
            BG1.add(jrb);
            JP.add(jrb);
            int finalI = i;
            if (Arrays.stream(indexes).anyMatch(num -> num == finalI)) arr.add(jrb);
        }
        JButton T2 = new JButton("下一步");
        T2.addActionListener(e -> {
            for (JRadioButton jrb : arr) {
                if (!jrb.isSelected()) {
                    answer.set(false);
                    break;
                }
            }
            exam.dispose();
        });
        JPanel cards = new JPanel(new CardLayout());
        JPanel PANEL1 = new JPanel();
        PANEL1.add(T2);
        cards.add(PANEL1, "card1");
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "card1");
        exam.add(Q1);
        exam.add(JP);
        exam.add(cards);
        exam.setVisible(true);
        return answer.get();
    }

    private static char letter(int num){
        return (char) ('A' + num);
    }
}
