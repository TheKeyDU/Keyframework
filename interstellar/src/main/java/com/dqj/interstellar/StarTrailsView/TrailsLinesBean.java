package com.dqj.interstellar.StarTrailsView;

import android.graphics.Point;

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

    public int getStartPoint() {
        return startX;
    }


    public TrailsLinesBean(int radius, int startAngle, int endAngle, int time, int startX ) {
        Radius = radius;
        StartAngle = startAngle;
        EndAngle = endAngle;
        Time = time;
        this.startX = startX;
        if (CenterPoint != null&&SrceenWithAndHeigh!=null) {
            RectPonintTopLeft=new Point((SrceenWithAndHeigh.x/2-radius),(SrceenWithAndHeigh.y/2-radius));
            RectPonintBottomRight=new Point((SrceenWithAndHeigh.x/2+radius),(SrceenWithAndHeigh.y/2+radius));
        } else {
            System.out.println("屏幕中心点未赋值！");
        }
    }

    private int Radius = 0;
    private int StartAngle = 0;
    private int EndAngle = 0;
    private int Time;
    private int startX;
    private Point RectPonintTopLeft;
    private Point RectPonintBottomRight;
    public static Point CenterPoint = null;
    public static Point SrceenWithAndHeigh = null;
}
