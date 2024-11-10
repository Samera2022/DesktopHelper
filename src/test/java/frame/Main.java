package frame;

import javax.swing.*;
import java.awt.*;

// 创建名为 Main 的主类
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("透明图形示例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        CustomPanel panel = new CustomPanel(); // 创建 CustomPanel 实例
        frame.add(panel); // 将面板添加到框架中
        frame.setVisible(true);
    }
}
class CustomPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 先调用父类的 paintComponent 方法
        g.setColor(new Color(255, 0, 0, 128)); // 创建红色，设置透明度
        g.fillRect(50, 50, 200, 200); // 绘制半透明矩形
    }
}