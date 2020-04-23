package com.dqj.interstellar.StarTrailsView;

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

import com.dqj.interstellar.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: document your custom view class.
 */
public class StarTrailsView extends View {
    private String mExampleString; // TODO: use a default from R.string...

    public ArrayList<TrailsLinesBean> Arcs = null;
    public Point CenterPoint = null;
    private Paint StarTrailsPaint;
    private int Width;
    private int Height;
    private RectF rectF;
    private Point point1;
    private Point point2;
    private int StartAngle;
    private int EndAngle;
    private int FPS = 60;
    private int MaxRadius;
    private int MaxArcNumber = 50;
    private long INTERVALS = 1000 / FPS;
    private Random random = new Random();
    public int TargetArcPostion = 0;
    public int TargetArcRadius = 0;

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
        StarTrailsPaint.setStrokeWidth(5);
        StarTrailsPaint.setAntiAlias(true);
        CenterPoint = new Point(0, 0);
        Arcs = new ArrayList<>();
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Width = getWidth();
                Height = getHeight();
                StartAngle = 0;
                EndAngle = 0;
                point1 = new Point(0, 0);
                point2 = new Point(Width, Height);
                rectF = new RectF(point1.x, point1.y, point2.x, point2.y);
                if (Width != 0 && Height != 0) {
                    initCenterPoint(Width / 2, Height / 2);
                    MaxRadius = Math.max(Width, Height) / 2;
                    TrailsLinesBean.CenterPoint = CenterPoint;
                    TrailsLinesBean.SrceenWithAndHeigh = new Point(Width, Height);
                }
            }
        });

    }

    public void initCenterPoint(int x, int y) {
        CenterPoint.set(x, y);
    }

    public void initRandomPoints() {
        Arcs.add(new TrailsLinesBean(TargetArcRadius + random.nextInt(20),
                0,
                0,
                1, 1

        ));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF, StartAngle, EndAngle, false, StarTrailsPaint);
        EndAngle++;
        postInvalidateDelayed(INTERVALS);

    }

    public void drawCustomArc(Canvas canvas, Point point1, Point point2, int startAngle, int EndAngle, Boolean UseCenter, int PaintColor) {
        rectF.set(point1.x, point1.y, point2.x, point2.y);
        StarTrailsPaint.setColor(PaintColor);
        canvas.drawArc(rectF, startAngle, EndAngle, UseCenter, StarTrailsPaint);
    }
}
