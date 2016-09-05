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

        List<Point> diffPoints = new ArrayList<>();

        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(Math.abs((image1.getRGB(x,y) / image2.getRGB(x,y)) * 100 ) < 10)
                    diffPoints.add(new Point(x,y));
            }
        }



        return drawRedRec(image2, diffPoints);
    }

    private BufferedImage drawRedRec(BufferedImage image, List<Point> points){

        Point[] arrayPoint = new Point[points.size()];
        arrayPoint = points.toArray(arrayPoint);

        Point start = arrayPoint[0];
        int startId = 0;
        for (int i = 0; i < arrayPoint.length - 1; i++){
            if(arrayPoint[i+1].getX() - arrayPoint[i].getX() > 20 ||
                    arrayPoint[i+1].getY() - arrayPoint[i].getY() > 20){
                Graphics2D g = image.createGraphics();
                g.setColor(java.awt.Color.RED);
                Point minimum = getMinimum(arrayPoint, startId, i);
                Point maximum = getMaximum(arrayPoint, startId, i);
                g.fillRect(minimum.getX(), minimum.getY(),
                        maximum.getX() - minimum.getX(), maximum.getY() - minimum.getY());
                start = arrayPoint[i+1];
                startId = i+1;
            }
        }

        return image;
    }

    private Point getMinimum(Point[] arrayPoint, int from, int to){
        Point minimum = new Point(arrayPoint[from].getX(),arrayPoint[from].getY());
        for (int i = from; i < to; i++){
            if (arrayPoint[i].getX() < minimum.getX()){
                minimum.setX(arrayPoint[i].getX());
            }
            if (arrayPoint[i].getY() < minimum.getY()){
                minimum.setY(arrayPoint[i].getY());
            }
        }
        return minimum;
    }

    private Point getMaximum(Point[] arrayPoint, int from, int to){
        Point maximum = new Point(arrayPoint[from].getX(),arrayPoint[from].getY());
        for (int i = from; i < to; i++){
            if (arrayPoint[i].getX() > maximum.getX()){
                maximum.setX(arrayPoint[i].getX());
            }
            if (arrayPoint[i].getY() > maximum.getY()){
                maximum.setY(arrayPoint[i].getY());
            }
        }
        return maximum;
    }

}
