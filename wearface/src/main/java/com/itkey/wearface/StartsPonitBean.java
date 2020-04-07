package com.itkey.wearface;

import android.media.audiofx.AudioEffect;

import java.util.Random;

public class StartsPonitBean {
    int startX;
    int startY;
    int lineStartX;
    int linestartY;
    float Startlength;
    int maxX = 0;
    int maxY = 0;
    int type;
    static int centerX;
    int raduis;
    static int centerY;
    boolean outOfscreen = false;
    public float Speed[] = {0.07f, 5f, 5f, 3f, 3f, 0.7f,4f,2f, 3f, 3f, 1f,5f };
    float speedFloat = 0.0f;
    int color[] = null;
    public int DefinefpsNumber = 60;
    public static int SetFpsNumber = 0;

    public void calculateNow() {
        calculate(startX, startY, centerX, centerY, Startlength);

    }

    public StartsPonitBean(int startX, int startY, int centerX, int centerY, float Startlength, int raduis, int radomSize) {
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
        this.centerX = centerX;
        this.centerY = centerY;
        this.raduis = raduis;

        Random random = new Random();
        speedFloat = Speed[radomSize];
        color = new int[]{255, random.nextInt(255), random.nextInt(200), random.nextInt(200)};
        // color = new int[]{255, 255,255, 255};
        calculate(startX, startY, centerX, centerY, Startlength);

    }

    private void calculate(int startX, int startY, int centerX, int centerY, float mStartlength) {
        type = intType(startX, startY, centerX, centerY);


        float dertaY = Math.abs(centerY - startY);
        float dertaX = Math.abs(startX - centerX);
        float mStartX = dertaX * (100 - mStartlength) / 100;
        float mStartY = dertaY * (100 - mStartlength) / 100;

        switch (type) {
            case 1: {
                lineStartX = (int) mStartX + centerX;
                linestartY = centerY - (int) mStartY;

                break;
            }
            case 2: {
                lineStartX = (int) (centerX - mStartX);
                linestartY = (int) (centerY - mStartY);

                break;


            }
            case 3: {
                lineStartX = (int) (centerX - mStartX);
                linestartY = centerY + (int) mStartY;

                break;


            }
            case 4: {
                lineStartX = (int) mStartX + centerX;
                linestartY = centerY + (int) mStartY;


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
        } else {
            Startlength -= startOffset;
        }

        calculate(startX, startY, centerX, centerY, Startlength);
    }

    public void startAndEndTransform(float endOffset, boolean forward) {
        if (type == 1 || type == 4) {
            if (forward) {
                Startlength += endOffset;
            } else {
                Startlength -= endOffset;

            }

        } else if (type == 3 || type == 2) {
            if (forward) {
                Startlength += endOffset;

            } else {
                Startlength -= endOffset;
            }
        }


        Startlength = (float) Math.max(0.01, Startlength);
        Startlength = (float) Math.min(99.9, Startlength);
        if ((Startlength >= 99.8) || (Startlength <= 0.02)) {
            outOfscreen = true;
        } else {

        }
        calculate(startX, startY, centerX, centerY, Startlength);
    }


}
