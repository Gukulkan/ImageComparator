package com.imgcomparator;

import com.imgcomparator.file.FileService;
import com.imgcomparator.images.ImageProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {

        BufferedImage image1 = FileService.loadImage("C:\\Projects\\ImageComparator\\image1.png");;
        BufferedImage image2 = FileService.loadImage("C:\\Projects\\ImageComparator\\image2.png");;

        BufferedImage imageFinal = null;

        if(image1 != null && image2 != null){

            ImageProcessor imageProcessor = new ImageProcessor();

            if(!imageProcessor.isImagesEquals(image1, image2)){
                imageFinal = imageProcessor.getComparedImage(image1, image2);
            }

        }



        if(imageFinal != null){
            FileService.saveImage(imageFinal);
            buildFrame(imageFinal);

        }
    }

    private static void buildFrame( BufferedImage image) {
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.setSize(image.getWidth(), image.getHeight());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
