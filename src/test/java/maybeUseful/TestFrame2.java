package maybeUseful;

import javax.swing.*;
import java.awt.*;

public class TestFrame2 {

    public static void TestFrame2() {
        // 创建JFrame窗体
        JFrame frame = new JFrame();
        frame.setTitle("Transparent Components Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // 居中显示

        // 创建JPanel
        JPanel totalPane = new JPanel();
        totalPane.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        totalPane.setOpaque(false); // 设置JPanel透明

        // 创建JLabel并设置图片
        JLabel label = new JLabel(new ImageIcon("D:\\_S_A_M\\Desktop\\1.png"));
        label.setOpaque(true); // 使JLabel不透明，以便显示图片
        label.setLayout(null); // 使用绝对布局

        // 创建JScrollPane和JTextArea
        JTextArea textArea = new JTextArea("Here you can type something...", 10, 30);
        textArea.setOpaque(false); // 设置JTextArea透明
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setOpaque(false); // 设置JScrollPane的视图区域透明
        scrollPane.setOpaque(false); // 设置JScrollPane透明

        // 添加组件到JPanel
        totalPane.add(label, BorderLayout.CENTER);
        totalPane.add(scrollPane, BorderLayout.SOUTH);

        // 添加JPanel到JFrame
        frame.add(totalPane);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TestFrame2();
    }
}