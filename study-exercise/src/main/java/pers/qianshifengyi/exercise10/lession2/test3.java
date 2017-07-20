package pers.qianshifengyi.exercise10.lession2;


import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test3 {
    public static void image2RGB565Bmp(String filePath, String saveFileName) {
        try {
            String base64Str = "eyJhZGRyZXNzIjoi5LiK5rW35biC5b6Q5rGH5Yy65Yev5ruo6LevMjA25Y+3IiwiY3VycmVudExhbnRpdHVkZSI6IjMxLjE4MjYyNCIsImN1cnJlbnRMb25ndGl0dWRlIjoiMTIxLjQ1NjI1MyIsIm1kNSI6IjE5ZTVlOGUwYWYyMTY3MDZiZjkwODAwOTVmNjhkMDViIiwic2VsZWN0TGFudGl0dWRlIjoiMzEuMTgzMTMxIiwic2VsZWN0TG9uZ3RpdHVkZSI6IjEyMS40NTYxMTYiLCJ0aW1lIjoiMjAxNzA3MTRfMTYxNDIyIn0=";

            BufferedImage sourceImg = ImageIO.read(new File(filePath));
            int h = sourceImg.getHeight(), w = sourceImg.getWidth();
            int[] pixel = new int[w * h];
            PixelGrabber pixelGrabber = new PixelGrabber(sourceImg, 0, 0, w, h, pixel, 0, w);
            pixelGrabber.grabPixels();

            MemoryImageSource m = new MemoryImageSource(w, h, pixel, 0, w);
            Image image = Toolkit.getDefaultToolkit().createImage(m);
            BufferedImage buff = new BufferedImage(w, h, BufferedImage.TYPE_USHORT_565_RGB);
            buff.createGraphics().drawImage(image, 0, 0 ,null);
            ImageIO.write(buff, "bmp", new File(saveFileName));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
