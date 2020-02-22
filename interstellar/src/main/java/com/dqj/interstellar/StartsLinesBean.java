package com.dqj.interstellar;

import android.util.Log;

import java.security.spec.ECField;
import java.util.EmptyStackException;
import java.util.Random;

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
    boolean outOfscreen=false;
    float speed[] = {0.02f, 0.04f,0.08f,0.1f, 0.15f, 0.3f, 0.6f, 1f,2f,4f,8f,16f};
  float speedFloat=0.0f;

    public StartsLinesBean(int startX, int startY, int centerX, int centerY, float Startlength, float Endlength) {
        this.startX = startX;
        this.startY = startY;
     /*   if (Endlength > Startlength) {
            this.Startlength = Startlength;
            this.Endlength = Endlength;
        } else {
            this.Startlength = Endlength;
            this.Endlength = Startlength;
        }*/
        this.Startlength = Startlength;
        this.Endlength = Endlength;
        this.centerX = centerX;
        this.centerY = centerY;
        speedFloat=speed[new Random().nextInt(speed.length)];
        calculate(startX, startY, centerX, centerY, Startlength, Endlength);

    }

    private void calculate(int startX, int startY, int centerX, int centerY, float startlength, float endlength) {
        type = intType(startX, startY, centerX, centerY);

        float mStartX = (startX - centerX) * (100 - Startlength) / 100 + centerX;
        float mStartY = (centerY - startY) * (100 - Startlength) / 100;
        float mEndx = (startX - centerX) * (100 - Endlength) / 100 + centerX;
        float mEndy = (centerY - startY) * (100 - Endlength) / 100;
        float mStartX2 = (centerX - startX) * (100 - Startlength) / 100 + startX;
        float mstarty2 = (centerX - startX) * (100 - Endlength) / 100 + startX;
        float mEndX2 = (startY - centerY) * (100 - Startlength) / 100;
        float mEndy2 = (startY - centerY) * (100 - Endlength) / 100;
        switch (type) {
            case 1: {
                lineStartX = (int) mStartX;
                linestartY = centerY - (int) mStartY;
                lineEndx = (int) mEndx;
                lineEndy = centerY - (int) mEndy;

                break;
            }
            case 2: {
                lineStartX = (int) mStartX2;
                linestartY = startY + (int) mStartY;
                lineEndx = (int) mstarty2;
                lineEndy = startY + (int) mEndy;

                break;


            }
            case 3: {
                lineStartX = (int) mStartX2;
                linestartY = startY - (int) mEndX2;
                lineEndx = (int) mstarty2;
                lineEndy = startY - (int) mEndy2;

                break;


            }
            case 4: {
                lineStartX = (int) mStartX;
                linestartY = centerY + (int) mEndX2;
                lineEndx = (int) mEndx;
                lineEndy = centerY + (int) mEndy2;


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

    public void offsetAddOrSub(float endOffset, float startOffset, boolean add) {
        if (add) {
            Startlength += startOffset;
            Endlength -= endOffset;
        } else {
            Startlength -= startOffset;
            Endlength -= endOffset;
        }

        calculate(startX, startY, centerX, centerY, Startlength, Endlength);
    }

    public void startAndEndTransform(float endOffset, boolean forward) {
        if (type == 1 || type == 4) {
            if (forward) {
                Startlength -= endOffset;
                Endlength -= endOffset;
            } else {
                Startlength += endOffset;
                Endlength += endOffset;

            }

        } else if (type == 3 || type == 2) {
            if (forward) {
                Startlength += endOffset;
                Endlength += endOffset;

            } else {
                Startlength -= endOffset;
                Endlength -= endOffset;
            }
        }


        Startlength = Math.max(1, Startlength);
        Startlength = Math.min(99, Startlength);
        Endlength = Math.max(1, Endlength);
        Endlength = Math.min(99, Endlength);
if ((Startlength==1&&Endlength==1)||(Startlength==99&&Endlength==99))
{
    outOfscreen=true;
}
        calculate(startX, startY, centerX, centerY, Startlength, Endlength);
    }


}
