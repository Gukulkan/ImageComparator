package com.imgcomparator;

import com.imgcomparator.file.FileService;
import com.imgcomparator.images.ImageProcessor;

import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage image1 = FileService.loadImage("c:\\1.png");;
        BufferedImage image2 = FileService.loadImage("c:\\2.png");;

        BufferedImage imageFinal = null;

        if(image1 != null && image2 != null){

            ImageProcessor imageProcessor = new ImageProcessor();

            if(!imageProcessor.isImagesEquals(image1, image2)){
                imageFinal = imageProcessor.getComparedImage(image1, image2);
            }

        }


        if(imageFinal != null){
            System.out.println("draw file");
        }

    }
}
