package com.imgcomparator.images;

import java.awt.image.BufferedImage;

public class ImageProcessor {

    public boolean isImagesEquals(BufferedImage image1, BufferedImage image2){
        if(image1 == null || image2 == null)
            return false;

        if(image1.getHeight() != image2.getHeight()
                || image1.getWidth() != image2.getWidth()){
            return false;
        }

        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(Math.abs((image1.getRGB(x,y) / image2.getRGB(x,y)) * 100 ) < 10)
                    return false;
            }
        }

        return true;
    }

    public BufferedImage getComparedImage(BufferedImage image1, BufferedImage image2){

        int[][] differe

        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(Math.abs((image1.getRGB(x,y) / image2.getRGB(x,y)) * 100 ) < 10)
                    return false;
            }
        }

        return null;
    }


}
