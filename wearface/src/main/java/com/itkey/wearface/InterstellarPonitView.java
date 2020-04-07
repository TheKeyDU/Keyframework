package com.itkey.wearface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;

import com.itkey.wearface.StartsPonitBean;

public class InterstellarPonitView extends View {
    Paint paint = null;
    Random random = null;
    int width = 0;
    int height = 0;
    int centerX;
    int centerY;
    int lineMaxNumber = 100;
    int MaxCircleradius = 150;
    int fpsNum = 59;
    int MoveX;
    int MoveY;
    boolean forward=true;
    boolean puase = false;
    int arratSize[] = {10, 33, 30, 20, 15, 20, 30, 33, 20, 10,15,11};
    Paint paint2 = new Paint();
    sendMessage sendMessage=null;
    StartsPonitBean DrawmLineBean=null;
    ArrayList<StartsPonitBean> StartsLinesBeanlist = null;
    Paint CirclePaint = new Paint();

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {


                case 1: {
                    if (!puase) {
                        for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                            StartsPonitBean Bean = StartsLinesBeanlist.get(i);
                            //StartsLinesBeanlist.get(i).offsetAddOrSub(r,r/1.5f,true);
                            if (Bean.outOfscreen) {
                                Bean = null;
                                StartsLinesBeanlist.remove(i);
                                StartsLinesBeanlist.add(initALineObject(false));
                                Log.e("-------删除了", StartsLinesBeanlist.size() + "");
                            } else {

                                Bean.startAndEndTransform(Bean.speedFloat, forward);

                            }

                            invalidate();

                        }
                    }
                    break;
                }
                case 2: {
                    //puase = true;
                    for (int i = 0; i < StartsLinesBeanlist.size(); i++) {
                        StartsPonitBean Bean = StartsLinesBeanlist.get(i);
                        Bean.calculateNow();

                    }
                    invalidate();
                    break;
                }
                case 3: {
                    puase = false;
                    invalidate();
                    break;
                }

            }

         /*   for (int i = 0; i <= 2; i++) {
            }*/

            //    Log.e("------- ", StartsLinesBeanlist.size() + "");


        }


    };

    public InterstellarPonitView(Context context) {
        super(context);
        init();

    }

    public void setCenter(int x, int y) {

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
                sendMessage=new sendMessage();

            }
        });


    }

    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(5);
        Paint paint2 = new Paint();
        paint2.setARGB(80, 255, 255, 255);
        paint2.setTextSize(10);
    }

    private void initLines() {

        for (int i = 0; i < lineMaxNumber; i++) {
            StartsLinesBeanlist.add(initALineObject(false));

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

    synchronized private StartsPonitBean initALineObject(Boolean from0) {
        int randomSize = random.nextInt(arratSize.length);
        int RandomMaxCircleradius = arratSize[randomSize];

        // int RandomMaxCircleradius = MaxCircleradius;
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
        float sl;
        if (from0)
            sl=0;
        else
            sl=random.nextInt(100);
        //  Log.e("---" + ramodNum + "   ", "x:" + startx + " y:" + starty);
        StartsPonitBean startsLinesBean = new StartsPonitBean(startx, starty, centerX, centerY, sl, RandomMaxCircleradius, randomSize);

        return startsLinesBean;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // canvas.drawText("("+centerX+", "+centerY+")", centerX-40, centerY-40, paint2);
        canvas.drawText("duqijian", centerX - 40, centerY - 100, paint2);
        drawStartsPonit(canvas, StartsLinesBeanlist);
        sendMessage.run();
    }
    class sendMessage extends Thread{
        @Override
        public void run() {
            super.run();
            super.run();
            try {
                Thread.sleep(1000 / fpsNum);
                Message message = new Message();
                message.what = 1;
                mHandler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                MoveX = (int) event.getX();
                MoveY = (int) event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int offsetX = (int) (event.getX() - MoveX);
                int offsety = (int) (event.getY() - MoveY);
                centerX += offsetX;
                centerY += offsety;
                MoveX = (int) event.getX();
                MoveY = (int) event.getY();
                StartsPonitBean.centerX = centerX;
                StartsPonitBean.centerY = centerY;
                Message message = new Message();
                message.what = 2;
              //  puase = true;
                mHandler.sendMessage(message);
                break;

            }
            case MotionEvent.ACTION_UP: {
/*
                Message message = new Message();
                message.what = 3;
                mHandler.sendMessage(message);
                break;*/

            }
        }

        return super.onTouchEvent(event);
    }

    private void drawStartsPonit(Canvas canvas, ArrayList<StartsPonitBean> StartsLinesBean) {
        for (int i = 0; i < StartsLinesBean.size(); i++) {
            DrawmLineBean = StartsLinesBean.get(i);
            paint.setAntiAlias(true);
           /* paint.setARGB(100,
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255));*/
            //   canvas.drawCircle( mLineBean.lineStartX, mLineBean.linestartY,mLineBean.Startlength*MaxCircleradius,paint);
            //   canvas.drawCircle(mLineBean.lineStartX, mLineBean.linestartY, (100 - mLineBean.Startlength) * MaxCircleradius / 100, paint);
            CirclePaint.setARGB(DrawmLineBean.color[0], DrawmLineBean.color[1], DrawmLineBean.color[2], DrawmLineBean.color[3]);
            canvas.drawCircle(DrawmLineBean.lineStartX,
                    DrawmLineBean.linestartY,
                    (100 - DrawmLineBean.Startlength) * DrawmLineBean.raduis / 100,
                    CirclePaint);
            /*-----------------------------------------------------------------*/            /*-----------------------------------------------------------------*/
            /*-----------------------------------------------------------------*/
            /*-----------------------------------------------------------------*/

          /*  Paint paint2=new Paint();
            paint2.setTextSize(mLineBean.raduis/2);
            paint2.setARGB(100,255,255,255);
            boolean b=random.nextBoolean();
            String str="";
            if (b)
            {
                str="丁";
            }
            else {
                str="玲";
            }
            canvas.drawText(str,
                    mLineBean.lineStartX,
                    mLineBean.linestartY,
                    paint2);*/
            /*-----------------------------------------------------------------*/
            /*-----------------------------------------------------------------*/
            /*-----------------------------------------------------------------*/
            /*-----------------------------------------------------------------*/

           /*canvas.drawCircle(mLineBean.lineStartX,
                    mLineBean.linestartY,
                   cirlerSizeChangeWIthLength(mLineBean.raduis,mLineBean.Startlength) ,
                    CirclePaint);*/
            //    Log.e("ondraw i: " + i, "x: " + mLineBean.lineStartX + " y: " + mLineBean.linestartY + " rd: " + (100 - mLineBean.Startlength) * MaxCircleradius / 100);
            //  Log.e("  半径"+(100 - mLineBean.Startlength)," Startlength"+mLineBean.Startlength+" ");
        }
    }

    private float cirlerSizeChangeWIthLength(float daxiao, float juli) {
        return (float) Math.sin(juli) * 100;
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
