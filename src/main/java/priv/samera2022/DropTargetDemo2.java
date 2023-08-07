package priv.samera2022;

import javax.swing.*;
import java.awt.*;

public class DropTargetDemo2{
    private static JTextField textField;

    public static void DropTargetDemo2() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("TextField with Image Example");
        frame.setSize(400,500);

        // 创建一个 JLabel 来显示图片
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("path/to/your/image.jpg"); // 替换为你的图片路径
        imageLabel.setIcon(imageIcon);

        // 创建 JTextField
        textField = new JTextField(20);
        textField.setEditable(false);

        // 创建一个 JPanel，并设置其布局为 BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(400,500);

        // 将 imageLabel 设置为 JPanel 的背景
        panel.add(imageLabel, BorderLayout.WEST);

        // 将 textField 放置在 JPanel 的中间
        panel.add(textField, BorderLayout.CENTER);

        // 将 JPanel 添加到 JFrame 的内容面板中
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        DropTargetDemo2();
//        SwingUtilities.invokeLater(DropTargetDemo2::new);
    }
}