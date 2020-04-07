package com.dqj.interstellar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * TODO: document your custom view class.
 */
public class StarTrailsView extends View {
    private String mExampleString; // TODO: use a default from R.string...


    private Paint StarTrailsPaint;
    private float Width;
    private float Height;
    private RectF rectF;
    private Point point1;
    private Point point2;
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
        StarTrailsPaint.setARGB(100, 100, 0, 0);
        StarTrailsPaint.setStyle(Paint.Style.STROKE);
        StarTrailsPaint.setStrokeWidth(3);
        point1=new Point(-500,1000);
        point2=new Point(1500,4000);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Width = getWidth();
                Height = getHeight();
            }
        });
          rectF = new RectF(point1.x, point1.y, point2.x ,point2.y  );
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
      canvas.drawArc(rectF, 0, 360 , false, StarTrailsPaint);


    }

    public void drawCustomArc(Canvas canvas,Point point1, Point point2, int startAngle, int EndAngle, Boolean UseCenter, int PaintColor) {
        rectF.set(point1.x,point1.y,point2.x,point2.y);
        StarTrailsPaint.setColor(PaintColor);
        canvas.drawArc(rectF,startAngle,EndAngle,UseCenter,StarTrailsPaint);
    }
}
