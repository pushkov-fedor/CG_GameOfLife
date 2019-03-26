package graphics;

import Utilities.Pair;
import model.Cell;
import model.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.*;

public class GraphUtills {

    public static final int COLOR_WHITE = getColorRGB(255, 255, 255);
    public static final int COLOR_RED = getColorRGB(255, 0, 0);
    public static final int COLOR_GREEN = getColorRGB(0, 255, 0);
    public static final int COLOR_BLACK = getColorRGB(0, 0, 0);

    public static int HEXAGON_RADIUS = 20;

    public static int FONT_SIZE = HEXAGON_RADIUS;

    public static int HEXAGON_RADIUS_SMALL = (int) (HEXAGON_RADIUS * sqrt(3) / 2);

//    public static final int IMAGE_WIDTH = 400;
//
//    public static final int IMAGE_HEIGHT = 400;

//    public static final int JFRAME_WIDTH = 1000;
//
//    public static final int JFRAME_HEIGHT = 800;

//  Some utility calculations
    public static int ASIDE_X = 2 * HEXAGON_RADIUS_SMALL;
    public static int ASIDE_Y = (int) (HEXAGON_RADIUS * 1.5);
//    public static int HEXAGONS_IN_ROW = (IMAGE_WIDTH - 10 - HEXAGON_RADIUS_SMALL) / ASIDE_X;
//    public static int HEXAGONS_IN_COL = (IMAGE_HEIGHT - 10 - HEXAGON_RADIUS) / ASIDE_Y;


    public static int HEXAGONS_IN_ROW = 10;
    public static int HEXAGONS_IN_COL = 10;

    public static int IMAGE_WIDTH = HEXAGONS_IN_ROW * ASIDE_X + 10 + HEXAGON_RADIUS_SMALL;
    public static int IMAGE_HEIGHT = HEXAGONS_IN_COL * ASIDE_Y + 10 + HEXAGON_RADIUS;

    public static int JFRAME_WIDTH = 1280;

    public static int JFRAME_HEIGHT = 1024;

    public static BufferedImage BUFFERED_IMAGE = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static int getColorRGB(int r, int g, int b){
        return (r << 16) | (g << 8) | b;
    }

    public static Pair<Integer, Integer> getSelectedHexagon(int x, int y){
        x = x - 5;
        y = y - 5;
        int gridHeight = (int) (HEXAGON_RADIUS * 1.5);
        int gridWidth = HEXAGON_RADIUS_SMALL * 2;
        double c = HEXAGON_RADIUS / 2;
        double m = c / HEXAGON_RADIUS_SMALL;

        int row =  y / gridHeight;
        int column;

        boolean rowIsOdd = row % 2 == 1;
        if (rowIsOdd) {
            column = (int) ( (x - HEXAGON_RADIUS_SMALL) / gridWidth);
        } else {
            column = (int) (x / gridWidth);
        }

        double relY = y - (row * gridHeight);
        double relX;

        if (rowIsOdd){
            relX = (x - (column * gridWidth)) - HEXAGON_RADIUS_SMALL;
        } else {
            relX = x - (column * gridWidth);
        }

        if (relY < (-m * relX) + c) {
            row--;
            if (!rowIsOdd)
                column--;
        }else if (relY < (m * relX) - c){
            row--;
            if(rowIsOdd)
                column++;
        }

        return new Pair<>(column, row);
    }

    public static void drawHexagon(int centerX, int centerY, int hexagonRadius, BufferedImage bufferedImage){
        int currentX = centerX,  currentY = centerY;
        int heightDiffSmall = hexagonRadius / 2;
        int heightDiffBig = hexagonRadius;
        int widthDiff = (int) (hexagonRadius * sqrt(3) / 2);

        ArrayList<Point> hexagonPoints = new ArrayList<>();

        //1st pixel

        currentX = currentX;
        currentY -= heightDiffBig;
        hexagonPoints.add(new Point(currentX, currentY));

        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);
        //2nd pixel
        currentX += widthDiff;
        currentY +=heightDiffSmall;
        hexagonPoints.add(new Point(currentX, currentY));
        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);
        //3rd pixel
        currentX = currentX;
        currentY += heightDiffBig;
        hexagonPoints.add(new Point(currentX, currentY));
        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);
        //4th pixel
        currentX -= widthDiff;
        currentY += heightDiffSmall;
        hexagonPoints.add(new Point(currentX, currentY));
        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);
        //5th pixel
        currentX -= widthDiff;
        currentY -= heightDiffSmall;
        hexagonPoints.add(new Point(currentX, currentY));
        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);
        //6th pixel
        currentX = currentX;
        currentY -= heightDiffBig;
        hexagonPoints.add(new Point(currentX, currentY));
        bufferedImage.setRGB(currentX, currentY, COLOR_WHITE);

        Iterator<Point> iterator = hexagonPoints.iterator();
        Point first = iterator.next();
        Point start = first;
        Point last = first;
        while(iterator.hasNext()){
            Point end = iterator.next();
            drawLine(start.x, start.y, end.x, end.y, bufferedImage);
            start = end;
            if(!iterator.hasNext()) last = end;
        }
        drawLine(first.x, first.y, last.x, last.y, bufferedImage);
    }

    public static void drawLine(int x0, int y0, int x1, int y1, BufferedImage bufferedImage){

        int p = getColorRGB(255, 255, 255);
        if(x0 == x1 && y0 == y1){
            bufferedImage.setRGB(x0, y0, p);
            return;
        }

        int A, B, sign;
        A = y1 - y0;
        B = x0 - x1;
        if (abs(A) > abs(B)) sign = 1;
        else sign = -1;
        int signa, signb;
        if (A < 0) signa = -1;
        else signa = 1;
        if (B < 0) signb = -1;
        else signb = 1;
        int f = 0;
        bufferedImage.setRGB(x0, y0, p);
        int x = x0, y = y0;
        if (sign == -1)
        {
            do {
                f += A*signa;
                if (f > 0)
                {
                    f -= B*signb;
                    y += signa;
                }
                x -= signb;
                bufferedImage.setRGB(x, y, p);
            } while (x != x1 || y != y1);
        }
        else
        {
            do {
                f += B*signb;
                if (f > 0) {
                    f -= A*signa;
                    x -= signb;
                }
                y += signa;
                bufferedImage.setRGB(x, y, p);
            } while (x != x1 || y != y1);
        }
    }

    public static void spanFlood(int x, int y, int newColor, BufferedImage bufferedImage){
        int realColor = bufferedImage.getRGB(x,y)  + 16777216;
        if(realColor == COLOR_BLACK) {
            bufferedImage.setRGB(x, y, newColor);
            int curX = x - 1;
            int curY = y;
            realColor = bufferedImage.getRGB(curX, curY) + 16777216;
            while (realColor == COLOR_BLACK) {
                bufferedImage.setRGB(curX, curY, newColor);
                spanFlood(curX, curY - 1, newColor, bufferedImage);
                spanFlood(curX, curY + 1, newColor, bufferedImage);
                curX--;
                realColor = bufferedImage.getRGB(curX, curY) + 16777216;
            }
            curX = x + 1;
            curY = y;
            realColor = bufferedImage.getRGB(curX, curY) + 16777216;
            while (realColor == COLOR_BLACK) {
                bufferedImage.setRGB(curX, curY, newColor);
                spanFlood(curX, curY - 1, newColor, bufferedImage);
                spanFlood(curX, curY + 1, newColor, bufferedImage);
                curX++;
                realColor = bufferedImage.getRGB(curX, curY) + 16777216;
            }
        }
    }

    public static boolean isPointInHexagonField(int x, int y, BufferedImage bufferedImage){
        if(x > IMAGE_WIDTH || y > IMAGE_HEIGHT) return false;
        int realColor = bufferedImage.getRGB(x, y) + 16777216;
        int expectedColor1 = COLOR_RED;
        int expectedColor2 = COLOR_GREEN;
        if(realColor == expectedColor1 || realColor == expectedColor2){
            return true;
        } else {
            return false;
        }
    }

    public static Image drawHexagonField(BufferedImage bufferedImage){
        int hexagonRadius = HEXAGON_RADIUS;

        int startX;
        int startY = 5 + hexagonRadius;

        int smallRadius = HEXAGON_RADIUS_SMALL;
        int asideX = ASIDE_X;
        int asideY = ASIDE_Y;
        int hexagonsInRow = HEXAGONS_IN_ROW;
        int hexagonsInColumn = HEXAGONS_IN_COL;

        for(int j = 0; j < hexagonsInColumn; j++) {
            if(j % 2 == 0) {
                startX = 5 + smallRadius;
            } else {
                startX = (int) (5 + smallRadius + smallRadius);
            }
            for (int i = 0; i < hexagonsInRow; i++) {
                GraphUtills.drawHexagon(startX, startY, hexagonRadius, bufferedImage);
                startX += asideX;
            }
            startY += asideY;
        }

        return bufferedImage;
    }

    public static void drawWorld(World world, Graphics graphics, ImageObserver observer){
        BUFFERED_IMAGE = new BufferedImage(GraphUtills.IMAGE_WIDTH, GraphUtills.IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        drawHexagonField(BUFFERED_IMAGE);
        colorWorld(world, BUFFERED_IMAGE);
        graphics.drawImage(BUFFERED_IMAGE, 0, 0, observer);
        drawImpact(world, graphics);
    }

    public static void drawImpact(World world, Graphics graphics){
        int hexagonRadius = HEXAGON_RADIUS;

        int startX;
        int startY = 5 + hexagonRadius;

        int smallRadius = HEXAGON_RADIUS_SMALL;
        int asideX = ASIDE_X;
        int asideY = ASIDE_Y;
        int hexagonsInRow = HEXAGONS_IN_ROW;
        int hexagonsInColumn = HEXAGONS_IN_COL;

        for(int j = 0; j < hexagonsInColumn; j++) {
            if(j % 2 == 0) {
                startX = 5 + smallRadius;
            } else {
                startX = (int) (5 + smallRadius + smallRadius);
            }
            for (int i = 0; i < hexagonsInRow; i++) {
                Pair<Integer, Integer> pair = getSelectedHexagon(startX, startY);
                Cell cell = world.getCell(pair.getFirst(), pair.getSecond());
                String impact = new DecimalFormat("#.#").format(cell.getImpact());
                Graphics2D gr = (Graphics2D) graphics;
                gr.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE));
                if(impact.length()==1) {
                    gr.drawString(impact, startX - HEXAGON_RADIUS_SMALL / 4, startY + HEXAGON_RADIUS / 3);
                } else {
                    gr.drawString(impact, (int) (startX - HEXAGON_RADIUS_SMALL/1.5), startY + HEXAGON_RADIUS / 3);
                }
                startX += asideX;
            }
            startY += asideY;
        }
    }



    private static void colorWorld(World world, BufferedImage bufferedImage){
        ArrayList<Pair<Integer, Integer>> coloredCells = new ArrayList<>();
        int startX;
        int startY = 5 + HEXAGON_RADIUS;
        int asideX = ASIDE_X;
        int asideY = ASIDE_Y;
        for(int j = 0; j < world.getHexInCol(); j++) {
            if(j % 2 == 0) {
                startX = 5 + HEXAGON_RADIUS_SMALL;
            } else {
                startX = (5 + HEXAGON_RADIUS_SMALL * 2);
            }
            for (int i = 0; i < world.getHexInRow(); i++) {
                Pair<Integer, Integer> coordColRow = getSelectedHexagon(startX, startY);
                Cell cell = world.getCell(coordColRow.getFirst(), coordColRow.getSecond());
                if(cell.isAlive()) {
                    GraphUtills.spanFlood(startX, startY, COLOR_GREEN, bufferedImage);
                } else {
                    GraphUtills.spanFlood(startX, startY, COLOR_RED, bufferedImage);
                }
                startX += asideX;
            }
            startY += asideY;
        }
    }
}
