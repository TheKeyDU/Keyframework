package com.dqj.interstellar.StarTrailsView;

import android.graphics.Color;
import android.graphics.Point;

import java.util.Random;

/**
 * Created by Administrator on 2020/4/23 15:55
 */
public class TrailsLinesBean {
    public int getRadius() {
        return Radius;
    }

    public void setRadius(int radius) {
        Radius = radius;
    }

    public int getStartAngle() {
        return StartAngle;
    }

    public void setStartAngle(int startAngle) {
        StartAngle = startAngle;
    }

    public int getEndAngle() {
        return EndAngle;
    }

    public void setEndAngle(int endAngle) {
        EndAngle = endAngle;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }


    public TrailsLinesBean(int radius, int startAngle, int endAngle, int time) {
        Radius = radius;
        StartAngle = startAngle;
        EndAngle = endAngle;
        Time = time;

        if (CenterPoint != null && SrceenWithAndHeigh != null) {
            Random random = new Random();
            RectPonintTopLeft = new Point((SrceenWithAndHeigh.x / 2 - radius), (SrceenWithAndHeigh.y / 2 - radius));
            RectPonintBottomRight = new Point((SrceenWithAndHeigh.x / 2 + radius), (SrceenWithAndHeigh.y / 2 + radius));
            RandomColor = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));

        } else {
            System.out.println("屏幕中心点未赋值！");
        }
    }

    public int getRandomColor() {
        return RandomColor;
    }

    public void setRandomColor(int randomColor) {
        RandomColor = randomColor;
    }

    private int RandomColor;
    private int Radius = 0;
    private int StartAngle = 0;
    private int EndAngle = 0;
    private int Time;

    public Point getRectPonintTopLeft() {
        return RectPonintTopLeft;
    }

    public void setRectPonintTopLeft(Point rectPonintTopLeft) {
        RectPonintTopLeft = rectPonintTopLeft;
    }

    public Point getRectPonintBottomRight() {
        return RectPonintBottomRight;
    }

    public void setRectPonintBottomRight(Point rectPonintBottomRight) {
        RectPonintBottomRight = rectPonintBottomRight;
    }

    private Point RectPonintTopLeft;
    private Point RectPonintBottomRight;
    public static Point CenterPoint = null;
    public static Point SrceenWithAndHeigh = null;
}
