package com.example.keyframework.recylerViewLayoutManager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class MyLayoutManager : RecyclerView.LayoutManager() {
    private var verticalScrollOffset = 0
    private val mScale: Int = 0
    private var mHasChild: Boolean = false
    private var mItemViewHeight: Int = 0
    private var mItemViewWidth: Int = 0
    private val mItemHeightWidthRatio: Float = 0f
    private var mItemCount: Int = 0
    private var mScrollYoffset: Int = 0
    var offsetY = 0
    var offsetX = 0

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        var scrollLength = dx
        offsetX
        offsetY

        if (verticalScrollOffset + dx < 0) {
            scrollLength = -verticalScrollOffset
        } else if (verticalScrollOffset + dx > offsetX - getHorizontallLength()) {
            scrollLength=offsetX-getHorizontallLength()-verticalScrollOffset
        }
        verticalScrollOffset+=scrollLength
        offsetChildrenHorizontal(-scrollLength)

        return  scrollLength
    }

    private fun getHorizontallLength(): Int {
        return width - paddingRight - paddingLeft
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
        offsetY = 0
        offsetX = 0
        for (i in 0..itemCount - 1) {
            val View = recycler?.getViewForPosition(i)
            addView(View)
            View?.let { measureChildWithMargins(it, 0, 0) }
            val width = View?.let { getDecoratedMeasuredWidth(it) }
            val heigh = View?.let { getDecoratedMeasuredHeight(it) }
          //  var left = (getWidth() - width!!) / 2
            if (heigh != null) {
                View.rotationY=70-i*23.3f
                layoutDecorated(View, (offsetX/1.3).toInt(), 0, (offsetX/1.3).toInt() + width!!, heigh)
            }
           // offsetY += heigh!!
            offsetX += width!!
        }

    }



}