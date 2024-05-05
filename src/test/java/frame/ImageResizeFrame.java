package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class ImageResizeFrame extends JFrame {

    private JLabel imageLabel;
    private ImageIcon currentImageIcon;
    private JPanel imagePanel;
    private Point dragPoint;

    public ImageResizeFrame() {
        super("Image Resize Frame");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // 创建一个面板用于放置图片标签
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (currentImageIcon != null) {
                    Image image = currentImageIcon.getImage();
                    g.drawImage(image, 0, 0, null);
                }
            }
        };
        imagePanel.setLayout(null);
        imagePanel.setOpaque(false);
        this.add(imagePanel, BorderLayout.CENTER);

        imageLabel = new JLabel();
        imageLabel.setSize(100, 100); // 初始大小
        imageLabel.setLocation(0, 0);
        imagePanel.add(imageLabel);

        // 添加鼠标事件监听器以支持拖拽
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragPoint = null;
            }
        });

        imageLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (dragPoint != null) {
                    int deltaX = e.getPoint().x - dragPoint.x;
                    int deltaY = e.getPoint().y - dragPoint.y;

                    // 更新标签的位置
                    Point currentLocation = imageLabel.getLocation();
                    imageLabel.setLocation(currentLocation.x + deltaX, currentLocation.y + deltaY);

                    // 更新拖拽点
                    dragPoint = e.getPoint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Do nothing
            }
        });

        // 添加组件监听器以更新图片大小
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (currentImageIcon != null) {
                    Image image = currentImageIcon.getImage();
                    // 根据窗体大小调整图片大小
                    Dimension newDimension = new Dimension(imagePanel.getWidth(), imagePanel.getHeight());
                    imageLabel.setSize(newDimension);
                    imagePanel.repaint();
                }
            }
        });

        // 添加文本框和按钮以加载图片
        JPanel controlPanel = new JPanel();
        JTextField urlField = new JTextField(30);
        JButton loadButton = new JButton("Load Image");

        controlPanel.add(urlField);
        controlPanel.add(loadButton);

        loadButton.addActionListener(e -> loadNewImage(urlField.getText()));

        this.add(controlPanel, BorderLayout.NORTH);
    }

    private void loadNewImage(String filePath) {
        try {
            File file = new File(filePath);
            URI uri = file.toURI();
            URL url = uri.toURL(); // 将文件路径转换为URL
            currentImageIcon = new ImageIcon(url);
            imageLabel.setIcon(currentImageIcon);
            imageLabel.setSize(currentImageIcon.getIconWidth(), currentImageIcon.getIconHeight());
            imagePanel.setPreferredSize(new Dimension(currentImageIcon.getIconWidth(), currentImageIcon.getIconHeight()));
            imagePanel.revalidate();
            imagePanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageResizeFrame frame = new ImageResizeFrame();
            frame.setVisible(true);
        });
    }
}
