//package priv.samera2022;
//
//import java.awt.Color;
//import java.awt.EventQueue;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.TexturePaint;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//
////定义文本框子类，使得能绘制背景图像
//class TextFieldWithImage extends JTextField {
//    BufferedImage img;
//    TexturePaint texture;
//
//    public TextFieldWithImage(File file) throws IOException {
//        super();
//        img = ImageIO.read(file);
//        Rectangle rect = new Rectangle(0, 0, img.getWidth(null), img.getHeight(null));
//        texture = new TexturePaint(img, rect);
//        setOpaque(false);
//    }
//
//    @Override
//    // 重写父类方法，参考JDK可知，当Swing组件的paint方法被调用时，paintComponent、paintBorder、
//    // paintChildren这三个方法也会被按顺序调用，
//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setPaint(texture);
//        g.fillRect(0, 0, getWidth(), getHeight());
//        super.paintComponent(g);
//    }
//}
//
////使用自动以按钮类
//public class BackGroundImage extends JFrame {
//
//    private JPanel contentPane;
//    ImageIcon imageIcon = new ImageIcon("image\\001.png");
//    JLabel background = new JLabel(imageIcon);
//    JTextField foregroundtxt = new JTextField();
//    TextFieldWithImage aaa;
//
//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    BackGroundImage frame = new BackGroundImage();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    /**
//     * Create the frame.
//     */
//    public BackGroundImage() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 450, 300);
//        contentPane = new JPanel();
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.setLayout(null);
//        setContentPane(contentPane);
//        getLayeredPane().setLayout(null);
//        try {
//            aaa = new TextFieldWithImage(new File("image\\001.png"));
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//
//        aaa.setBounds(20, 20, 300, 100);
//        contentPane.add(aaa); // 将文本框添加到前景
//        aaa.setFont(new Font("宋体", Font.BOLD, 20));
//        aaa.setForeground(Color.red);
//    }
//}