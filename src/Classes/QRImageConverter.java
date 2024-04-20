/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import com.google.zxing.common.BitMatrix;
/**
 *
 * @author kathe
 */
public class QRImageConverter {
    public static Image convertBitMatrixToImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        WritableImage image = new WritableImage(width, height);
        PixelWriter writer = image.getPixelWriter();

        // Iterar sobre cada píxel del BitMatrix y establecer el color correspondiente en la imagen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                writer.setArgb(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        return image;
    }
}
