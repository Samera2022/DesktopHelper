package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    Rectangle rect;
    int left;// 窗体离屏幕左边的距离
    int top;// 窗体离屏幕顶部的距离
    int width; // 窗体的宽
    int height;// 窗体的高
    Point point;// 鼠标在窗体的位置
    Timer timer = new Timer(10, this);

    public static void main(String[] args) {
        new MyFrame();
    }

    public MyFrame() {
        timer.start();
        this.setTitle("中国");
        this.setSize(200, 600);
        this.setLocation(700, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        left = getLocationOnScreen().x;
        top = getLocationOnScreen().y;
        width = getWidth();
        height = getHeight();
// 获取窗体的轮廓
        rect = new Rectangle(0, 0, width, height);
// 获取鼠标在窗体的位置
        point = getMousePosition();
        if ((top < 0) && isPtInRect(rect, point)) {
// 当鼠标在当前窗体内，并且窗体的Top属性小于0
// 设置窗体的Top属性为0,就是将窗口上边沿紧靠顶部
            setLocation(left, 0);
        } else if (top > -5 && top < 5 && !(isPtInRect(rect, point))) {
// 当窗体的上边框与屏幕的顶端的距离小于5时 ，
// 并且鼠标不再窗体上 将QQ窗体隐藏到屏幕的顶端
            setLocation(left, 5 - height);
        }
    }

    public boolean isPtInRect(Rectangle rect, Point point) {
        if (rect != null && point != null) {
            int x0 = rect.x;
            int y0 = rect.y;
            int x1 = rect.width;
            int y1 = rect.height;
            int x = point.x;
            int y = point.y;
            return x >= x0 && x < x1 && y >= y0 && y < y1;
        }
        return false;
    }

}