import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

public class GaussianBlurring {
    public static void main(String[] args) {
        try {
            // 加载原始图片
            File originalImageFile = new File("D:\\_S_A_M\\Desktop\\1.jpg");
            BufferedImage originalImage = ImageIO.read(originalImageFile);

            // 创建一个和原始图片一样大小的图片，‌用于存放模糊后的背景
            BufferedImage blurredImage = new BufferedImage(originalImage.getWidth(),
                    originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // 应用高斯模糊
            float[] matrix = {
                    0.0625f, 0.125f, 0.0625f,
                    0.125f,  0.25f,  0.125f,
                    0.0625f, 0.125f, 0.0625f
            };
            BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrix), ConvolveOp.EDGE_NO_OP, null);
            op.filter(originalImage, blurredImage);

            // 创建一个新的图片，‌将模糊背景与原始图片合并
            BufferedImage combinedImage = new BufferedImage(originalImage.getWidth(),
                    originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = combinedImage.createGraphics();
            g2d.drawImage(blurredImage, 0, 0, null); // 先绘制模糊背景
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2d.drawImage(originalImage, 0, 0, null); // 再绘制原始图片
            g2d.dispose();

            // 保存结果图片
            File outputImageFile = new File("D:\\_S_A_M\\Desktop\\2.jpg");
            ImageIO.write(combinedImage, "jpg", outputImageFile);

            System.out.println("Image processed and saved successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
