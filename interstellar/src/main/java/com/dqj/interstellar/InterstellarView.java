package com.dqj.interstellar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class InterstellarView extends View {
    Paint paint = null;
    Random random = null;
    int width = 0;
    int height = 0;
    int centerX;
    int centerY;
    ArrayList<StartsLinesBean> StartsLinesBeanlist = null;

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            Random random2 = new Random();
            for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                float r=random2.nextInt(100)/30;
              StartsLinesBeanlist.get(i).offsetAddOrSub(r,r/1.5f,true);
            }

            invalidate();
        }


    };
    private int lineMaxNumber = 100;

    public InterstellarView(Context context) {
        super(context);
        init();

    }

    private void init() {
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                width = getWidth();
                height = getHeight();
                centerX = getWidth() / 2;
                centerY = getHeight() / 2;
                StartsLinesBeanlist = new ArrayList<>();
                random = new Random();
                initPaint();
                initLines();

            }
        });


    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setARGB(100, 255, 255, 255);
    }

    private void initLines() {

        for (int i = 0; i < lineMaxNumber; i++) {
            int startx = 1;
            int starty = 1;
            int ramodNum = random.nextInt(4);
            switch (ramodNum) {
                case 0: {
                    startx = 1;
                    starty = random.nextInt(getHeight());
                    break;
                }
                case 1: {
                    startx = random.nextInt(getWidth());
                    starty = 1;
                    break;

                }
                case 2: {
                    startx = getWidth();
                    starty = random.nextInt(getHeight());
                    break;

                }
                case 3: {
                    startx = random.nextInt(getWidth());
                    starty = getHeight();
                    break;

                }
            }
              int end=random.nextInt(50)+50;
            StartsLinesBean startsLinesBean = new StartsLinesBean(startx, starty, centerX, centerY,end-50 , end);
            StartsLinesBeanlist.add(startsLinesBean);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2 = new Paint();
        paint2.setARGB(100, 255, 0, 0);
        paint2.setStrokeWidth(3);
        canvas.drawPoint(centerX, centerY, paint2);

        drawStartsLine(canvas, paint, StartsLinesBeanlist);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(20);
                      mHandler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void drawStartsLine(Canvas canvas, Paint paint, ArrayList<StartsLinesBean> StartsLinesBean) {
        for (int i = 0; i < StartsLinesBean.size(); i++) {
            StartsLinesBean mLineBean = StartsLinesBean.get(i);
            paint.setAntiAlias(true);
           /* paint.setARGB(100,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));*/
            canvas.drawLine(mLineBean.lineEndx, mLineBean.lineEndy, mLineBean.lineStartX, mLineBean.linestartY, paint);

        }
    }

    public InterstellarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public InterstellarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public InterstellarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
}
