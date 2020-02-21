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


    public StartsLinesBean(int startX, int startY, int xielv, float Startlength, float Endlength) {
        this.startX = startX;
        this.startY = startY;
        this.xielv = xielv;
        this.Startlength = Startlength;
        this.Endlength = Endlength;
    }


    public StartsLinesBean(int startX, int startY, int centerX, int centerY, float Startlength, float Endlength) {
        this.startX = startX;
        this.startY = startY;
        this.Startlength = Startlength;
        this.Endlength = Endlength;
        this.centerX = centerX;
        this.centerY = centerY;
        type = intType(startX, startY, centerX, centerY);
        Log.e("-----", "" + type);
        switch (type) {
            case 1: {
                lineStartX = (int) ((startX - centerX) * (100 - Startlength) / 100 + centerX);
                linestartY = centerY - (int) ((centerY - startY) * (100 - Startlength) / 100);
                lineEndx = (int) ((startX - centerX) * (100 - Endlength) / 100 + centerX);
                lineEndy = centerY - (int) ((centerY - startY) * (100 - Endlength) / 100);
                Log.e("%%%%%%%%%%%1", "xielv" + xielv + "   endx" + lineEndx + "   endy" + lineEndy + "   linstartx" + lineStartX + "   linestarty" + linestartY);

                break;
            }
            case 2: {
                lineStartX = (int) ((centerX - startX) * (100 - Startlength) / 100 + startX);
                linestartY = startY + (int) ((centerY - startY) * (100 - Startlength) / 100);
                lineEndx = (int) ((centerX - startX) * (100 - Endlength) / 100 + startX);
                lineEndy = startY + (int) ((centerY - startY) * (100 - Endlength) / 100);
                Log.e("%%%%%%%%%%%2", "xielv" + xielv + "   endx" + lineEndx + "   endy" + lineEndy + "   linstartx" + lineStartX + "   linestarty" + linestartY);

                break;


            }
            case 3: {
                lineStartX = (int) ((centerX - startX) * (100 - Startlength) / 100 + startX);
                linestartY = startY -  (int) ((startY - centerY) * (100 - Startlength) / 100);
                lineEndx = (int) ((centerX - startX) * (100 - Endlength) / 100 + startX);
                lineEndy = startY -  (int) ((startY - centerY) * (100 - Endlength) / 100);
                Log.e("%%%%%%%%%%%3", "xielv" + xielv + "   endx" + lineEndx + "   endy" + lineEndy + "   linstartx" + lineStartX + "   linestarty" + linestartY);

                break;


            }
            case 4: {
                lineStartX = (int) ((startX - centerX) * (100 - Startlength) / 100 + centerX);
                linestartY = centerY + (int) ((startY - centerY) * (100 - Startlength) / 100);
                lineEndx = (int) ((startX - centerX) * (100 - Endlength) / 100 + centerX);
                lineEndy = centerY + (int) ((startY - centerY) * (100 - Endlength) / 100);
                Log.e("%%%%%%%%%%%4", "xielv" + xielv + "   endx" + lineEndx + "   endy" + lineEndy + "   linstartx" + lineStartX + "   linestarty" + linestartY);


                break;

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
