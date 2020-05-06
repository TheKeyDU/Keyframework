package com.dqj.interstellar.StarTrailsView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.dqj.interstellar.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class StarTrailsView extends View {
    private String mExampleString; // TODO: use a default from R.string...

    public ArrayList<TrailsLinesBean> lines = null;
    public Point CenterPoint = null;
    private Paint StarTrailsPaint;
    private int Width;
    private int Height;
    private int FPS = 60;
    private long INTERVALS = 1000 / FPS;
    private Random random = new Random();
    public int TargetArcPostion = 200;
    public int TargetArcRadius = 10;
    private int MoveX;
    private int MoveY;
    private boolean ChangeOnce = true;

    public StarTrailsView(Context context) {
        super(context);
        init(null, 0);
    }

    public StarTrailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public StarTrailsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.StarTrailsView, defStyle, 0);

        mExampleString = a.getString(
                R.styleable.StarTrailsView_exampleString);
        a.recycle();
        StarTrailsPaint = new TextPaint();
        // StarTrailsPaint.setARGB(100, 100, 0, 0);
        StarTrailsPaint.setStyle(Paint.Style.STROKE);
        StarTrailsPaint.setStrokeWidth(5);
        StarTrailsPaint.setTextSize(30);
        StarTrailsPaint.setAntiAlias(true);
        CenterPoint = new Point(0, 0);
        lines = new ArrayList<>();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (ChangeOnce) {
                    Width = getWidth();
                    Height = getHeight();
                    if (Width != 0 && Height != 0) {
                        initCenterPoint(Width, Height);
                        TrailsLinesBean.CenterPoint = CenterPoint;
                        initRandomPoints();
                        ChangeOnce = false;
                    }
                }
            }
        });

    }

    public void initCenterPoint(int x, int y) {
        CenterPoint.set(x, y);
    }

    public void initRandomPoints() {

        for (int i = 0; i < TargetArcPostion; i++) {
            float start = random.nextInt(320);
            float lenth = random.nextInt(40);
             float time = (random.nextInt(10) + 1) / 10f;
           // float time = 1;
            lines.add(new TrailsLinesBean(TargetArcRadius,
                    start,
                    start + lenth,
                    time)

            );
            TargetArcRadius += random.nextInt(20);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawAllArcLines(canvas, lines, StarTrailsPaint);
        linesLenthThen(lines);
        postInvalidateDelayed(INTERVALS);

    }

    private void linesLenthThen(ArrayList<TrailsLinesBean> lines) {
        for (int i = 0; i < lines.size(); i++) {
            TrailsLinesBean trailsLinesBean = lines.get(i);
            float start = trailsLinesBean.getStartAngle();
            float end = trailsLinesBean.getEndAngle();
            float time = trailsLinesBean.getTime();
            start += time;
            end += time;
            start = start > 360 ? start % 360 : start;
            end = end > 360 ? end % 360 : end;
            lines.get(i).setStartAngle(start);
            lines.get(i).setEndAngle(end);
            Log.e("起点终点" + i, lines.get(i).getStartAngle() + "     " + lines.get(i).getEndAngle());
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
            /*    CenterPoint.x+= offsetX;
                CenterPoint.y += offsety;*/
                TrailsLinesBean.CenterPoint.x += offsetX;
                TrailsLinesBean.CenterPoint.y += offsety;
                MoveX = (int) event.getX();
                MoveY = (int) event.getY();
                break;

            }
            case MotionEvent.ACTION_UP: {


            }
        }

        return true;
    }


    private void drawAllArcLines(Canvas canvas, ArrayList<TrailsLinesBean> lines, Paint paint) {
        for (int i = 0; i < lines.size(); i++) {
            float left = lines.get(i).getRectPonintTopLeft().x;
            float top = lines.get(i).getRectPonintTopLeft().y;
            float right = lines.get(i).getRectPonintBottomRight().x;
            float bottom = lines.get(i).getRectPonintBottomRight().y;
            float startAngle = lines.get(i).getStartAngle();
            float sweepAngle = lines.get(i).getEndAngle();
            int color = lines.get(i).getRandomColor();
            paint.setColor(color);
            canvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, false, paint);
            lines.get(i).caculateLTBR();

            //     canvas.drawText("start:"+startAngle+" end"+sweepAngle,(int)left-100,(int)top-100,paint);
            //canvas.drawPoint(lines.get(i).getRectPonintBottomRight().x,lines.get(i).getRectPonintBottomRight().y,paint);
        /*    canvas.drawText(i+"",right,lines.get(i).getRectPonintBottomRight().y,paint);
            canvas.drawText("s"+startAngle,right,top,paint);
            canvas.drawText("e"+sweepAngle,left,bottom,paint);
           // canvas.drawPoint(lines.get(i).getRectPonintTopLeft().x,lines.get(i).getRectPonintTopLeft().y,paint);
            canvas.drawText(i+"",lines.get(i).getRectPonintTopLeft().x,lines.get(i).getRectPonintTopLeft().y,paint);*/
            // canvas.drawPoint(TrailsLinesBean.CenterPoint.x, TrailsLinesBean.CenterPoint.y, paint);


        }
    }


}
