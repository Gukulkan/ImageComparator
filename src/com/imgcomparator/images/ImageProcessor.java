package com.imgcomparator.images;

import com.imgcomparator.entity.Point;
import com.sun.prism.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

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

    public BufferedImage getComparedImage(BufferedImage image1, BufferedImage image2) {

//        List<Point> diffPoints = new ArrayList<>();

        boolean points[][] = new boolean[image1.getWidth()][image1.getHeight()];
        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(Math.abs((image1.getRGB(x,y) / image2.getRGB(x,y)) * 100 ) < 10)
                    points[x][y] = true;
            }
        }



        return drawRedRec(image2, points);
    }

    private BufferedImage drawRedRec(BufferedImage image, boolean points[][]){

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < image.getWidth() - 1; i++){
            for (int j = 0; j < image.getHeight() - 1; j++){
                if(points[i][j]){
                    startX = i;
                    startY = j;
                    Point p = findFinishForBlock(points, startX, startY);
                    Graphics2D g = image.createGraphics();
                    g.setColor(java.awt.Color.RED);
                    g.drawRect(startX - 10, startY - 10,
                            p.getX() - startX + 15, p.getY() - startY + 15);
                    i = p.getX();
                    j = p.getY();

                }


            }
        }

        return image;
    }

    private Point findFinishForBlock(boolean points[][], int startX, int startY){
        for (int x = startX; x < startX + 20; x++){
            for (int y = startY; y < startY + 20; y++) {
                if (points[x][y]) {
                    startX = x;
                    startY = y;
                }
            }
        }

        return new Point(startX,startY);

    }

}
