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
import java.util.logging.LogRecord;

import androidx.annotation.Nullable;

public class InterstellarView extends View {
    Paint paint = null;
    Random random = null;
    ArrayList<LineBean> list = null;
    Canvas mCanvas = null;
    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            for (int i = 0; i < list.size(); i++) {
         /*       int flag = random.nextInt(10) % 2;
                if (flag == 0)
                   // list.get(i).add(10);
                else
                    list.get(i).sub(random.nextInt(10));
                   // list.get(i).sub(10);*/
                list.get(i).add(random.nextInt(3));

            }
            invalidate();
        }

        ;
    };

    public InterstellarView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        paint = new Paint();
        paint.setStrokeWidth(2);
        random = new Random();
        initLines();
    }

    private void initLines() {
        for (int i = 0; i < 600; i++) {
            LineBean lineBean = new LineBean(random.nextInt(1080),
                    random.nextInt(1980),
                    random.nextInt(1080),
                    random.nextInt(1920));
            list.add(lineBean);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawStartsLine(canvas, paint, list);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(17);
                    mHandler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void drawStartsLine(Canvas canvas, Paint paint, ArrayList<LineBean> lineBean) {
        for (int i = 0; i < lineBean.size(); i++) {
            LineBean mLineBean = lineBean.get(i);
           /* paint.setARGB(100,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));*/
            paint.setARGB(100,255,255,255);
            canvas.drawLine(mLineBean.startX, mLineBean.startY, mLineBean.endX, mLineBean.endy, paint);

        }
    }


    public InterstellarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();


    }

    public InterstellarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    public InterstellarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();

    }
}
