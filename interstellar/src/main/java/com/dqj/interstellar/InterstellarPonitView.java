package com.dqj.interstellar;

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
import java.util.Random;

import androidx.annotation.Nullable;

public class InterstellarPonitView extends View {
    Paint paint = null;
    Random random = null;
    int width = 0;
    int height = 0;
    int centerX;
    int centerY;
    int lineMaxNumber = 100;
    int MaxCircleradius = 500;
    int fpsNum=59;

    ArrayList<StartsPonitBean> StartsLinesBeanlist = null;

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {

            for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                StartsPonitBean Bean = StartsLinesBeanlist.get(i);
                //StartsLinesBeanlist.get(i).offsetAddOrSub(r,r/1.5f,true);
                if (Bean.outOfscreen) {
                    Bean = null;
                    StartsLinesBeanlist.remove(i);
                    StartsLinesBeanlist.add(initALineObject());


                    Log.e("-------删除了", StartsLinesBeanlist.size() + "");
                } else {

                    Bean.startAndEndTransform(Bean.speedFloat, false);

                }


            }
         /*   for (int i = 0; i <= 2; i++) {
            }*/

            Log.e("------- ", StartsLinesBeanlist.size() + "");

            invalidate();
        }


    };

    public InterstellarPonitView(Context context) {
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
        paint.setStrokeWidth(5);
    }

    private void initLines() {

        for (int i = 0; i < lineMaxNumber; i++) {
            StartsLinesBeanlist.add(initALineObject());

        }
    /*         StartsPonitBean startsLinesBean = new StartsPonitBean(0, 0, centerX, centerY, 70);
        StartsPonitBean startsLinesBean2 = new StartsPonitBean(1050, 0, centerX, centerY, 70);
        StartsPonitBean startsLinesBean3 = new StartsPonitBean(0, 1900, centerX, centerY, 20);
        StartsPonitBean startsLinesBean4 = new StartsPonitBean(1050, 1900, centerX, centerY, 40);

        StartsLinesBeanlist.add(startsLinesBean);
      StartsLinesBeanlist.add(startsLinesBean2);
        StartsLinesBeanlist.add(startsLinesBean3);
        StartsLinesBeanlist.add(startsLinesBean4);
     StartsLinesBeanlist.add(startsLinesBean5);
        StartsLinesBeanlist.add(startsLinesBean6);
        StartsLinesBeanlist.add(startsLinesBean7);
        StartsLinesBeanlist.add(startsLinesBean8);*/

    }

    synchronized private StartsPonitBean initALineObject() {
        int RandomMaxCircleradius = random.nextInt(MaxCircleradius);
        int CirclerOutOFScreenX = 0 - 2 * RandomMaxCircleradius;
        int CirclerOutOFScreenY = 0 - 2 * RandomMaxCircleradius;
        int CircleradiusD = 2 * RandomMaxCircleradius;

        int startx = 0;
        int starty = 0;
        int ramodNum = random.nextInt(4);
        switch (ramodNum) {
            case 0: {

                startx = random.nextInt(getWidth() + 2 * CircleradiusD) - CircleradiusD;
                starty = CirclerOutOFScreenY;
                break;
            }
            case 1: {
                startx = getWidth() + CircleradiusD;
                starty = random.nextInt(getHeight());
                break;

            }
            case 2: {
                startx = random.nextInt(getWidth() + 2 * CircleradiusD) - CircleradiusD;
                starty = getHeight() + CircleradiusD;
                break;

            }
            case 3: {
                startx = CirclerOutOFScreenX;
                starty = random.nextInt(getHeight());
                break;

            }
        }

        //  Log.e("---" + ramodNum + "   ", "x:" + startx + " y:" + starty);
        StartsPonitBean startsLinesBean = new StartsPonitBean(startx, starty, centerX, centerY, 100, RandomMaxCircleradius);

        return startsLinesBean;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint2 = new Paint();
        paint2.setARGB(100, 255, 0, 0);
        paint2.setStrokeWidth(20);
        canvas.drawPoint(centerX, centerY, paint2);
        drawStartsPonit(canvas,   StartsLinesBeanlist);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000/fpsNum);
                    mHandler.sendMessage(new Message());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void drawStartsPonit(Canvas canvas,  ArrayList<StartsPonitBean> StartsLinesBean) {
        for (int i = 0; i < StartsLinesBean.size(); i++) {
            StartsPonitBean mLineBean = StartsLinesBean.get(i);
            paint.setAntiAlias(true);
           /* paint.setARGB(100,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));*/
            //   canvas.drawCircle( mLineBean.lineStartX, mLineBean.linestartY,mLineBean.Startlength*MaxCircleradius,paint);
            //   canvas.drawCircle(mLineBean.lineStartX, mLineBean.linestartY, (100 - mLineBean.Startlength) * MaxCircleradius / 100, paint);
            Paint CirclePaint=new Paint();
            CirclePaint.setARGB(mLineBean.color[0],mLineBean.color[1],mLineBean.color[2],mLineBean.color[3]);
            canvas.drawCircle(mLineBean.lineStartX,
                    mLineBean.linestartY,
                    (100 - mLineBean.Startlength) * mLineBean.raduis / 100,
                    CirclePaint);
            //    Log.e("ondraw i: " + i, "x: " + mLineBean.lineStartX + " y: " + mLineBean.linestartY + " rd: " + (100 - mLineBean.Startlength) * MaxCircleradius / 100);
            //  Log.e("  半径"+(100 - mLineBean.Startlength)," Startlength"+mLineBean.Startlength+" ");
        }
    }

    public InterstellarPonitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public InterstellarPonitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public InterstellarPonitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
}
