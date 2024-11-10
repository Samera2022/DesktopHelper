package frame;

import javax.swing.*;
import java.awt.*;

public class TestFrame {
    public static void main(String[] args) {
       frame();
    }
    public static void frame(){
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.enableInputMethods(false);
        JPanel totalPane = new JPanel();

        //<---BACKGROUND--->
        JLabel background = new JLabel(new ImageIcon("D:\\_S_A_M\\Desktop\\1.png"));
        frame.setBounds(0,0,300,700);
        background.setBounds(0,0,300,700);
        totalPane.add(background);

        //<---ComponentTest--->
        JTextArea jta = new JTextArea();
        jta.setLineWrap(true);
        jta.setEditable(true);
        jta.setWrapStyleWord(true);
        jta.setOpaque(false);
        jta.setPreferredSize(new Dimension(300,100));
        JScrollPane jsp = new JScrollPane(jta);
        jsp.setOpaque(false);
        jsp.setPreferredSize(new Dimension(300,100));
        totalPane.add(jsp);



        frame.add(totalPane);
//        background.setVisible(true);
        frame.setVisible(true);
    }

    public static void setTransparent(JComponent component) {
        component.setOpaque(false); // 直接设置传入组件为透明
        Container container = (Container) component;
        JComponent[] components = (JComponent[]) container.getComponents();
        for (JComponent c : components) {
            setTransparent(c); // 递归调用设置子组件为透明
        }
    }
}
