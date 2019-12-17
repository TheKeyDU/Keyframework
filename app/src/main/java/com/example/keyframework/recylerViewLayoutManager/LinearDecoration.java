package com.example.keyframework.recylerViewLayoutManager;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 自定义recylerview分割线
 * 很多列表的分割线都是继承这个类来进行绘制，我这里写的这一个类不是用来绘制而是设置item之间的间距
 * 通过在item之间绘制矩形，从而达到画间隔的效果
 * @Author Jungle68
 * @Contact master.jungle68@gmail.com
 * @since 2017/1/5
 */

public class LinearDecoration extends RecyclerView.ItemDecoration {
    private int top;
    private int bottom;
    private int left;
    private int right;
    private boolean mIsNeedLastDecoration = true;
    private int hidePosition = -1;
    private Paint mPaint;

    public LinearDecoration setNeedLastDecoration(boolean needLastDecoration) {
        mIsNeedLastDecoration = needLastDecoration;
        return this;
    }

    public void hideDecoration(int position) {
        hidePosition = position;
    }

    public LinearDecoration(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public LinearDecoration(int top, int bottom, int left, int right, int color) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = top;
        if (hidePosition >= 0 && parent.getChildAdapterPosition(view) == hidePosition) {
            outRect.bottom = 0;
        } else if (!mIsNeedLastDecoration && parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            // 最后一行
            outRect.bottom = 0;
        } else {
            outRect.bottom = bottom;
        }
        outRect.left = left;
        outRect.right = right;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mPaint == null) {
            super.onDraw(c, parent, state);
        } else {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View view = parent.getChildAt(i);
                c.drawRect(parent.getPaddingLeft(), view.getBottom(),
                        parent.getWidth() - parent.getPaddingRight(), view.getBottom() + (bottom - top), mPaint);
            }
        }
    }
}