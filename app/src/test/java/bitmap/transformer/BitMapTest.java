
package bitmap.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class BitMapTest {

    @Test
    public void testAddBorder() throws IOException {

        String inputImagePath = "src/test/resources/image5.png";
        BitMap bitMap = new BitMap(inputImagePath);


        int borderWidth = 10;
        Color borderColor = Color.RED;
        bitMap.addBorder(borderWidth, borderColor);


        String outputImagePath = "src/test/resources/image6.bmp";
        bitMap.write(outputImagePath);

        File outputFile = new File(outputImagePath);
        assertTrue(outputFile.exists(), "Output image was not created");

        int redRGB = Color.RED.getRGB();
        for (int i = 0; i < bitMap.bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bitMap.bufferedImage.getHeight(); j++) {
                if (i < borderWidth || i >= bitMap.bufferedImage.getWidth() - borderWidth
                        || j < borderWidth || j >= bitMap.bufferedImage.getHeight() - borderWidth) {
                    int actualRGB = bitMap.bufferedImage.getRGB(i, j);
                    assertEquals(redRGB, actualRGB, "Border color is not red");
                }
            }
        }
    }

    @Test
    public void testInvertColors() throws IOException {
        String inputImagePath = "src/test/resources/image5.png";
        BitMap bitMap = new BitMap(inputImagePath);

        BufferedImage originalImage = new BufferedImage(
                bitMap.bufferedImage.getWidth(),
                bitMap.bufferedImage.getHeight(),
                bitMap.bufferedImage.getType()
        );
        originalImage.setData(bitMap.bufferedImage.getData());

        bitMap.invertColors();

        String outputImagePath = "src/test/resources/image6_colorinverted.bmp";
        bitMap.write(outputImagePath);
        File outputFile = new File(outputImagePath);
        assertTrue(outputFile.exists(), "Output image was not created");


        for (int i = 0; i < bitMap.bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bitMap.bufferedImage.getHeight(); j++) {
                Color originalColor = new Color(originalImage.getRGB(i, j));
                Color invertedColor = new Color(bitMap.bufferedImage.getRGB(i, j));


                assertEquals(originalColor.getAlpha(), invertedColor.getAlpha());
            }
        }
    }

    @Test
    public void testRotateImage() throws IOException {

        String inputImagePath = "src/test/resources/image4.bmp";
        BitMap bitMap = new BitMap(inputImagePath);

        BufferedImage originalImage = new BufferedImage(
                bitMap.bufferedImage.getWidth(),
                bitMap.bufferedImage.getHeight(),
                bitMap.bufferedImage.getType()
        );
        originalImage.setData(bitMap.bufferedImage.getData());

        double angle = 45.0;
        bitMap.rotateImage(angle);


        String outputImagePath = "src/test/resources/image6_rotated.bmp";
        bitMap.write(outputImagePath);
        File outputFile = new File(outputImagePath);
        assertTrue(outputFile.exists(), "Output image was not created");


        Color originalCenterColor = new Color(originalImage.getRGB(originalImage.getWidth() / 2, originalImage.getHeight() / 2));
        Color rotatedCenterColor = new Color(bitMap.bufferedImage.getRGB(bitMap.bufferedImage.getWidth() / 2, bitMap.bufferedImage.getHeight() / 2));
        assertEquals(originalCenterColor, rotatedCenterColor);
    }
}