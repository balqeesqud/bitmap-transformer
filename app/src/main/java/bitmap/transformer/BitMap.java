package bitmap.transformer;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitMap {
    private static final Logger logger = Logger.getLogger(BitMap.class.getName());
    BufferedImage bufferedImage;

    public BitMap(String filePath) throws IOException {
        File file = new File(filePath);
        bufferedImage = ImageIO.read(file);

    }
    public  void write(String fileName)throws IOException{

        File file=new File(fileName);
        ImageIO.write(this.bufferedImage,"bmp",file);

    }
    public void addBorder(int borderWidth, Color borderColor) {
        logger.info("Adding border to the image...");



        int width = this.bufferedImage.getWidth();
        int height = this.bufferedImage.getHeight();

        BufferedImage newImage = new BufferedImage(
                width + 2 * borderWidth,
                height + 2 * borderWidth,
                this.bufferedImage.getType()

        );

        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                if (i < borderWidth || i >= width + borderWidth || j < borderWidth || j >= height + borderWidth) {
                    newImage.setRGB(i, j, borderColor.getRGB());
                } else {
                    Color color = new Color(this.bufferedImage.getRGB(i - borderWidth, j - borderWidth));
                    newImage.setRGB(i, j, color.getRGB());
                }
            }
        }

        this.bufferedImage = newImage;
        logger.info("Border added successfully.");
    }

    public void invertColors() {
        logger.info("Inverting Image colors...");
        for (int i = 0; i < this.bufferedImage.getWidth(); i++) {
            for (int j = 0; j < this.bufferedImage.getHeight(); j++) {
                Color color = new Color(this.bufferedImage.getRGB(i, j));
                int r = 255 - color.getRed();
                int g = 255 - color.getGreen();
                int b = 255 - color.getBlue();
                int a = color.getAlpha();
                Color invertedColor = new Color(r, g, b, a);
                this.bufferedImage.setRGB(i, j, invertedColor.getRGB());
            }
        }
        logger.info("Image color Inverted successfully.");
    }

    public void rotateImage(double angle) {
        logger.info("Rotating Image ...");
        int width = this.bufferedImage.getWidth();
        int height = this.bufferedImage.getHeight();

        BufferedImage rotatedImage = new BufferedImage(width, height, this.bufferedImage.getType());

        Graphics2D g2d = rotatedImage.createGraphics();

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);

        g2d.setTransform(transform);
        g2d.drawImage(this.bufferedImage, 0, 0, null);

        g2d.dispose();

        this.bufferedImage = rotatedImage;
        logger.info("Image rotated successfully.");
    }

}







