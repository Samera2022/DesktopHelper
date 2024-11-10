package frame;

import javax.swing.*;
import java.awt.*;

public class TransparentScrollPaneExample extends JFrame {
    public TransparentScrollPaneExample() {
        setTitle("Transparent ScrollPane with Components");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建一个JPanel用于放置滚动面板
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(124, 124, 170, 255)); // 设置半透明背景颜色
        new Color(174, 176, 178, 255);//一种好看的颜色
        Color c = new Color(124, 124, 170, 255);
        // 创建JTextArea
        JTextArea textArea = new JTextArea("这是一个示例文本区域，可以滚动查看更多内容。\n这里可以添加更多的文本...");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);

        // 创建JScrollPane并将JTextArea添加到其中
        JScrollPane scrollPane = new JScrollPane(textArea) {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                // 允许背景显示为透明
//            }
        };

        // 设置JScrollPane的组件为透明
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
//        scrollPane.getViewport().setBackground(new Color(0, 0, 255, 128));
//        scrollPane.getHorizontalScrollBar().setOpaque(false);
//        scrollPane.getVerticalScrollBar().setOpaque(false);

        // 将JScrollPane添加到JPanel
        panel.add(scrollPane, BorderLayout.CENTER);

        // 将JPanel添加到JFrame
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TransparentScrollPaneExample().setVisible(true);
        });
    }
}