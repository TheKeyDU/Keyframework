package com.dqj.interstellar;

import android.util.Log;

import java.security.spec.ECField;
import java.util.EmptyStackException;

public class StartsLinesBean {
    int startX;
    int startY;
    int lineStartX;
    int linestartY;
    int lineEndx;
    int lineEndy;
    int endX;
    int endy;
    float xielv;
    float Startlength;
    float Endlength;
    int maxX = 1080;
    int maxY = 1920;
    int type;
    int centerX;
    int centerY;


    public StartsLinesBean(int startX, int startY, int xielv, float Startlength,float Endlength) {
        this.startX = startX;
        this.startY = startY;
        this.xielv = xielv;
        this.Startlength = Startlength;
        this.Endlength = Endlength;
    }


    public StartsLinesBean(int startX, int startY, int centerX, int centerY, float Startlength,float Endlength) {
        this.startX = startX;
        this.startY = startY;
        this.Startlength = Startlength;
        this.Endlength = Endlength;
        this.centerX = centerX;
        this.centerY = centerY;
        type = intType(startX, startY, centerX, centerY);
        Log.e("-----",""+type);
        switch (type) {
            case 1: {
                lineStartX= (int) ((startX-centerX)*(100-Startlength)/100+centerX);
               linestartY= centerY- (int) ((centerY-startY)*(100-Startlength)/100);
               lineEndx=(int) ((startX-centerX)*(100-Endlength)/100+centerX);
                lineEndy= centerY- (int) ((centerY-startY)*(100-Endlength)/100);
                Log.e(".......","xielv"+xielv+"   endx"+lineEndx+"   endy"+lineEndy+"   linstartx"+lineStartX+"   linestarty"+linestartY);
            }
            case 2: {/*  xielv=(float) (centerX-startX)/(centerY-startY);
                endX=(int) (((centerX-startX)*length/100*xielv)+startX);
                endy=(int) (((centerY-startY)*length/100*xielv)+startY);*/


            }
            case 3: {
            }
            case 4: {
            }
        }
    }

    private int intType(int startX, int startY, int centerX, int centerY) {
        if (startX > centerX) {
            if (startY > centerY) {
                return 4;
            } else {
                return 1;
            }
        } else {
            if (startY > centerY) {
                return 3;
            } else {
                return 2;
            }
        }

    }

    public void toEnd(int offset, boolean offsetStart) {
//
    }


    public void toStart(int offset, boolean offsetStart) {

    }


}
