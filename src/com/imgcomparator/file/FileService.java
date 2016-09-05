package com.imgcomparator.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileService {

    public static BufferedImage loadImage(String url) {
        File file = new File(url);

        BufferedImage image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    public static void saveImage(BufferedImage image){
        File outfile = new File("C:\\Projects\\ImageComparator\\saved.png");
        try {
            ImageIO.write(image, "png", outfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
