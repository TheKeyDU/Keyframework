package com.dqj.interstellar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

public class InterstellarView extends View {
    Paint paint = null;
    Random random = null;

    ArrayList<StartsLinesBean> StartsLinesBeanlist = null;
    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
         /*   for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
           //   StartsLinesBeanlist.get(i).toEnd(1,true);
            }*/
            //initLines();

            invalidate();
        }

        ;
    };

    public InterstellarView(Context context) {
        super(context);
        init();

    }

    private void init() {
        random = new Random();
        paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setARGB(100, 255, 255, 255);
        StartsLinesBeanlist = new ArrayList<>();
         initLines();
    }

    private void initLines() {
        StartsLinesBeanlist.clear();
            StartsLinesBean lineBean = new StartsLinesBean(800,100,500,1000,10,80);
            StartsLinesBean lineBean2 = new StartsLinesBean(200,200,500,1000,10,80);
            StartsLinesBean lineBean3 = new StartsLinesBean(200,1700,500,1000,10,80);
            StartsLinesBean lineBean4 = new StartsLinesBean(800,1700,500,1000,10,80);
            StartsLinesBeanlist.add(lineBean);
            StartsLinesBeanlist.add(lineBean2);
            StartsLinesBeanlist.add(lineBean3);
            StartsLinesBeanlist.add(lineBean4);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2=new Paint();
        paint2.setARGB(100,255,0,0);
        paint2.setStrokeWidth(10);
        canvas.drawPoint(500,1000,paint2);
        canvas.drawPoint(800,100,paint2);
        canvas.drawPoint(200,200,paint2);
        canvas.drawPoint(200,1700,paint2);
        canvas.drawPoint(800,1700,paint2);
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
