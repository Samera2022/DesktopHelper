package priv.samera2022.module.gadgets.aa_quiz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class ImageFrame extends JFrame {

    private JLabel imageLabel;
    private BufferedImage image;

    public ImageFrame() {
//        setTitle("High Quality Image Scaling");
        setTitle("Amino Acid Structure Quiz");
        setSize(800, 600); // 设置初始大小
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // 添加组件监听器，以便在窗体大小改变时自动缩放图片
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaleImage();
            }
        });
    }

    public void loadImage(String picPath) {
        try {
            image = ImageIO.read(new File(picPath));
            if (image != null) {
                imageLabel.setIcon(new ImageIcon(image));
                pack();
                scaleImage(); // 加载图片后立即缩放以适应窗体大小
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePic(String picPath) {
        loadImage(picPath); // 更新图片
    }

    public void scaleImage() {
        if (image != null) {
            int newWidth = getWidth() > 0 ? getWidth() : image.getWidth();
            int newHeight = getHeight() > 0 ? getHeight() : image.getHeight();

            // 创建一个新的BufferedImage对象来存储缩放后的图像
            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();

            // 设置渲染质量为最高
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(image, 0, 0, newWidth, newHeight, this);
            g2d.dispose();

            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageFrame frame = new ImageFrame();
            frame.loadImage("D:\\_S_A_M\\Desktop\\Temp\\1.png"); // Replace with your image path
            frame.setVisible(true);
        });
    }
}