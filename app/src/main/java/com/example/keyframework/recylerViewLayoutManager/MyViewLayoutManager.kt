package com.example.keyframework.recylerViewLayoutManager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keyframework.bean.ItemViewInfo
import java.util.ArrayList

class MyLayoutManager : RecyclerView.LayoutManager() {
    private val mScale: Int = 0
    private var mHasChild: Boolean = false
    private var mItemViewHeight: Int = 0
    private var mItemViewWidth: Int = 0
    private val mItemHeightWidthRatio: Float = 0f
    private var mItemCount: Int = 0
    private var mScrollOffset: Int = Int.MAX_VALUE

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

        return super.scrollHorizontallyBy(dx, recycler, state)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }
    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams? {
        return object : RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT) {}
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        /*
        * 如果没有Item，或者正在展示布局之前直接返回
        * */
        if (state!!.itemCount == 0 || state.isPreLayout()) {
            return
        }
        /*
        * 在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        * */
        recycler?.let { removeAndRecycleAllViews(it) }
        /*
        * 初始化偏移量
        * */
        var offsetY = 0
        var offsetX = 0
        for (i in 0..itemCount - 1) {
            var View = recycler?.getViewForPosition(i)
            addView(View)
            View?.let { measureChildWithMargins(it, 0, 0) }
            var width = View?.let { getDecoratedMeasuredWidth(it) }
            var heigh = View?.let { getDecoratedMeasuredHeight(it) }
            var left = (getWidth() - width!!) / 2
            if (heigh != null) {
                layoutDecorated(View!!, offsetX, 0, offsetX+width, heigh)
            }
            offsetY += heigh!!
                offsetX+=width
        }

    }


}