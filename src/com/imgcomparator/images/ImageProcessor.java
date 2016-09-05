package com.imgcomparator.images;

import com.imgcomparator.entity.Point;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class ImageProcessor {

    private static final int AROUND_DIFFERENT_PIXEL = 20;
    private static final int ADDITIONAL_TO_RECT_SIZE = 15;
    private static final int ADDITIONAL_TO_RECT_CORDINATES = 10;
    private static final int DIFF_PERCENT = 10;

    public boolean isImagesEquals(BufferedImage image1, BufferedImage image2){
        if(image1 == null || image2 == null)
            return false;

        if(image1.getHeight() != image2.getHeight()
                || image1.getWidth() != image2.getWidth()){
            return false;
        }

        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(isDiffInPercent(image1.getRGB(x,y), image2.getRGB(x,y)))
                    return false;
            }
        }

        return true;
    }

    private boolean isDiffInPercent(int rgb1, int rgb2) {
        return Math.abs((rgb1 /  rgb2) * 100 ) < DIFF_PERCENT;
    }

    public BufferedImage getComparedImage(BufferedImage image1, BufferedImage image2) {
        boolean points[][] = new boolean[image1.getWidth()][image1.getHeight()];

        for (int x = 0; x < image1.getWidth(); x++){
            for (int y = 0; y < image1.getHeight(); y++){
                if(isDiffInPercent(image1.getRGB(x,y), image2.getRGB(x,y)))
                    points[x][y] = true;
            }
        }



        return drawRedRec(image2, points);
    }

    private BufferedImage drawRedRec(BufferedImage image, boolean points[][]){
        List<Pair<Point, Point>> rects = new ArrayList<>();
        for (int i = 0; i < image.getWidth() - 1; i++){
            for (int j = 0; j < image.getHeight() - 1; j++){
                if(points[i][j]){
                    Point start = new Point(i, j);
                    Point end = findFinishForBlock(points, i, j);

                    rects.add(new Pair<>(start,end));
                    i = end.getX();
                    j = end.getY();

        }

    }
}

    Graphics2D g = image.createGraphics();
g.setColor(java.awt.Color.RED);
        for (Pair<Point, Point> rect : rects){
        g.drawRect(
                    rect.getKey().getX() - ADDITIONAL_TO_RECT_CORDINATES,
                    rect.getKey().getY() - ADDITIONAL_TO_RECT_CORDINATES,
                    rect.getValue().getX() - rect.getKey().getX() + ADDITIONAL_TO_RECT_SIZE,
                    rect.getValue().getY() - rect.getKey().getY() + ADDITIONAL_TO_RECT_SIZE
            );
        }

        return image;
    }

    private Point findFinishForBlock(boolean points[][], int startX, int startY){
        for (int x = startX - AROUND_DIFFERENT_PIXEL; x < startX + AROUND_DIFFERENT_PIXEL; x++){
            for (int y = startY - AROUND_DIFFERENT_PIXEL; y < startY + AROUND_DIFFERENT_PIXEL; y++) {
                if (points[x][y]) {
                    startX = x;
                    startY = y;
                }
            }
        }

        return new Point(startX,startY);

    }

}
