package frame;

import javax.swing.*;
import java.awt.*;

public class TestFrame2 {
    private JFrame frame;
    private JPanel totalPane;

    public TestFrame2() {
        frame = new JFrame("Test Frame 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        totalPane = new JPanel(new BorderLayout());
        totalPane.setLayout(null); // 使用绝对布局以控制组件位置
        totalPane.setOpaque(false); // 设置JPanel透明

        // 创建JLabel并加载图片
        JLabel label = new JLabel(new ImageIcon("D:\\_S_A_M\\Desktop\\1.png"));
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 设置JLabel的位置和大小
        label.setOpaque(true); // 使JLabel不透明，以便图片可见
        label.setIconTextGap(0); // 确保文本和图标之间没有间隙

        // 创建JScrollPane和JTextArea
        JScrollPane scrollPane = new JScrollPane();
        JTextArea textArea = new JTextArea("Sample text");
        scrollPane.setViewportView(textArea);
        scrollPane.getViewport().setOpaque(false); // 设置JScrollPane的视口透明
        scrollPane.setOpaque(false); // 设置JScrollPane透明
        textArea.setOpaque(false); // 设置JTextArea透明
        scrollPane.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 设置JScrollPane的位置和大小

        // 添加组件到totalPane
        totalPane.add(scrollPane);
        totalPane.add(label); // 将JLabel添加在其他组件之下

        frame.add(totalPane);
        frame.setVisible(true);

        // 确保JLabel在最底层
        totalPane.revalidate();
        totalPane.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestFrame2::new);
    }
}