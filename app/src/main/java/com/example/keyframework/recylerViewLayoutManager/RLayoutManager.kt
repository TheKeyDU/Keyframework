package com.example.keyframework.recylerViewLayoutManager

import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.maosong.tools.ToastUtils
import kotlin.math.pow
import kotlin.math.roundToInt


class RLayoutManager : RecyclerView.LayoutManager() {
    private var verticalScrollOffset = 0
    private val mScale: Int = 0
    private var mHasChild: Boolean = false
    private var mItemViewHeight: Int = 0
    private var mItemViewWidth: Int = 0
    private val mItemHeightWidthRatio: Float = 0f
    private var mItemCount: Int = 0
    private var mScrollYoffset: Int = 0

    private var mScaleX = 0.93f
    private var mScaleY = 0.99f
    private var mScaleViewInterval = 0.97f
    var offsetY = 0
    var offsetX = 0
    var normalViewWidth = 0
    var normalViewHeigh = 0


    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {

          var scrollLength = dy
          if (verticalScrollOffset + dy < 0) {
              scrollLength = -verticalScrollOffset
          } else if (verticalScrollOffset + dy > offsetY - getVerticalLength()) {
              scrollLength=offsetY-getVerticalLength()-verticalScrollOffset
          }
          verticalScrollOffset+=scrollLength
          offsetChildrenVertical(-scrollLength)

          return  scrollLength
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
        recycler?.let { removeAndRecycleAllViews(it) }
        /*
        * 初始化偏移量
        * */
        offsetY = 0
        offsetX = 0
        setViewsLayout(recycler, state, 0)


    }

    private fun setViewsLayout(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, offset: Int) {
        for (i in 0..itemCount - 1) {
            val View = recycler?.getViewForPosition(i)
            addView(View)
            View?.let { measureChildWithMargins(it, 0, 0) }
            val width = View?.let { getDecoratedMeasuredWidth(it) }
            val heigh = View?.let { getDecoratedMeasuredHeight(it) }
            normalViewHeigh = heigh!!
            normalViewWidth = width!!
            var mScaleXY = mScaleX.pow(itemCount - i)
            View!!.scaleX = mScaleXY
            View!!.scaleY = mScaleXY
            //   Log.e("  #1  ", "width: ${width} heigh: ${heigh} mScaleXY: ${mScaleXY}")
            var left = 0
            var top = offsetY
            var right = width!!
            var bottom = (heigh!! + offsetY)
            //   Log.e("  #2  ", " left:${left} top: ${top} right: ${right} bottom: ${bottom}")
            layoutDecorated(View!!, 0, top, right, bottom)
            //  offsetX += width!! * mScaleViewInterval.pow(itemCount - i  )
            offsetY += (heigh * mScaleXY * 0.7).toInt()
        }
    }


}