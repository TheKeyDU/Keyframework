package com.example.keyframework.recylerViewLayoutManager

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.pow


class RLayoutManager(var mContext: Context, var orientation: Boolean) : RecyclerView.LayoutManager() {
    private var verticalScrollOffset = 0
    private val mScale: Int = 0
    private var mHasChild: Boolean = false
    private var mItemViewHeight: Int = 0
    private var mItemViewWidth: Int = 0
    private val mItemHeightWidthRatio: Float = 0f
    private var mItemCount: Int = 0
    private var mScrollYoffset: Int = 0
    private var moreTopBottomSpace = 400
    private var mScaleX = 0.95f
    private var mScaleY = 0.99f
    private var mScaleViewInterval = 0.97f
    private var itemRotationX = -10f
    var offsetY = 0
    var AllOffsetY = 0
    var normalViewWidth = 0
    var normalViewHeigh = 0
    var mScrollLength = 0
    var ScrollPercent = 0f
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        var scrollLength = dy
        if (verticalScrollOffset + dy + moreTopBottomSpace < 0) {
            scrollLength = -verticalScrollOffset - moreTopBottomSpace
        } else if (verticalScrollOffset + dy - moreTopBottomSpace > AllOffsetY - getVerticalLength()) {
            scrollLength = AllOffsetY - getVerticalLength() - verticalScrollOffset + moreTopBottomSpace
        }
        mScrollLength = -scrollLength
        verticalScrollOffset += scrollLength
        Log.e("位移", "scrollLength: ${scrollLength}  verticalScrollOffset:${(verticalScrollOffset)}   AllOffsetY:${(AllOffsetY)} ")

        setViewsLayout(recycler, state, false)

        return scrollLength
    }

    private fun getHorizontallLength(): Int {
        return width - paddingRight - paddingLeft
    }

    private fun getVerticalLength(): Int {
        return height - paddingTop - paddingBottom
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun canScrollVertically(): Boolean {
        return true
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
        /*
        * 初始化偏移量
        * */
        offsetY = 0
        offsetY = 0
        setViewsLayout(recycler, state, true)

    }

    private fun setViewsLayout(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, isfrist: Boolean) {
        var temp = true
        detachAndScrapAttachedViews(recycler!!)
          if (AllOffsetY ==0 ) AllOffsetY =1
            ScrollPercent = ((verticalScrollOffset.toFloat() - 2 * (moreTopBottomSpace).toFloat()) / AllOffsetY.toFloat())
            Log.e("位移百分比", "AllOffsetY: ${AllOffsetY}  verticalScrollOffset:${(verticalScrollOffset)}     %:${(ScrollPercent)}   ")
        for (i in 0..itemCount - 1) {
            val View = recycler?.getViewForPosition(i)
            addView(View)

            View?.let { measureChildWithMargins(it, 0, 0) }
            val width = View?.let { getDecoratedMeasuredWidth(it) }
            val heigh = View?.let { getDecoratedMeasuredHeight(it) }
            normalViewHeigh = heigh!!
            normalViewWidth = width!!
          //  var mScaleXY = mScaleX.pow(itemCount - i)
            var mScaleXY: Float = (0.6+(i+1)*0.1).toFloat()
            mScaleXY=if (mScaleXY>3) 3f else mScaleXY
            mScaleXY=if (mScaleXY<0) 0.1f else mScaleXY
            if (isfrist) {
                var left = 0
                var top = offsetY
                var right = width!!
                var bottom = (heigh!! + offsetY)
                layoutDecoratedWithMargins(View!!, left, top, right, bottom)
                offsetY += (heigh * mScaleXY * 0.7).toInt()
                AllOffsetY = offsetY
                Log.e("y值${i}   ", " offsety: ${offsetY}  AllOffsetY:${(AllOffsetY)}  ")
            } else {
                View?.offsetTopAndBottom(mScrollLength)
                Log.e("y值${i}   ", " offsety: ${offsetY}  AllOffsetY:${(AllOffsetY)} ")
            }
            mItemCount = if (mItemCount < 0) 0 else mItemCount
            mItemCount = if (mItemCount >100) 100 else mItemCount

            View!!.scaleX = mScaleXY
            View!!.scaleY = mScaleXY
            //  View.z = mScaleXY
            View.rotationX = itemRotationX
            //Log.e(" 宽高缩放${i} ", "width: ${width} heigh: ${heigh} mScaleXY: ${mScaleXY}")
            //  Log.e(" mScrollLength${i} ", "mScrollLength: ${mScrollLength}  ")
            //
        }

    }


}