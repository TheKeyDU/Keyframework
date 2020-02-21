package com.example.keyframework.recylerViewLayoutManager

import android.content.Context
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keyframework.bean.NumberOfItemNeedFold
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
    private var mScaleXFrist = 0.8f
    private var mScaleY = 0.99f
    private var mScaleViewInterval = 0.97f
    private var itemRotationX = -14f
    var offsetY = 0
    var AllOffsetY = 0
    var normalViewWidth = 0
    var normalViewHeigh = 0
    var ScrollPercent = 0f
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        var ScrollLength = dy
        /*   if (verticalScrollOffset + dy + moreTopBottomSpace < 0) {
               ScrollLength = -verticalScrollOffset - moreTopBottomSpace
           } else if (verticalScrollOffset + dy - moreTopBottomSpace > AllOffsetY - getVerticalLength()) {
               ScrollLength = AllOffsetY - getVerticalLength() - verticalScrollOffset + moreTopBottomSpace
           }*/
        Log.e(" 发送值 1", "ScrollLength: ${ScrollLength}    ")
        verticalScrollOffset += ScrollLength
        //  Log.e("位移", "mScrollLength: ${mScrollLength}  verticalScrollOffset:${(verticalScrollOffset)}   AllOffsetY:${(AllOffsetY)} ")

        setViewsLayout(recycler, state, -verticalScrollOffset)
        //  Log.e(" 发送值 2", "ScrollLength: ${ScrollLength}    ")

        return ScrollLength
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
        setViewsLayout(recycler, state, mScrollYoffset)

    }

    private fun setViewsLayout(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, mScrollLength: Int) {
        var temp = true
        detachAndScrapAttachedViews(recycler!!)
        if (AllOffsetY == 0) AllOffsetY = 1
        var mNumberOfItemNeedFold =
                calculateNumberOfItemNeedFold(mScrollLength, mScaleX, 800)
        var needFoldNumer = mNumberOfItemNeedFold.number
        var lastLenth = mNumberOfItemNeedFold.lastLenth
        //   ScrollPercent = ((verticalScrollOffset.toFloat() - 2 * (moreTopBottomSpace).toFloat()) / AllOffsetY.toFloat())
        //    Log.e("位移百分比", "AllOffsetY: ${AllOffsetY}  verticalScrollOffset:${(verticalScrollOffset)}     %:${(ScrollPercent)}   ")
        var tag = true
        for (i in 0..itemCount - 1) {
            //  Log.e(" 接收到值 ", "mScrollLength: ${mScrollLength}    ")
            val View = recycler?.getViewForPosition(i)
            addView(View)
            var tempY = if (View.y > 2000f) 2000f else View.y
            var scale: Float = (tempY / 2000f) * 0.8f + 0.4f
            scale = if (scale > 1.2f) 1.2f else scale
            scale = if (scale < 0.3f) 0.3f else scale

            View.scaleX = 0.9f
            View.scaleY = 0.9f
            View?.let { measureChildWithMargins(it, 0, 0) }
            val width = View?.let { getDecoratedMeasuredWidth(it) }
            val heigh = View?.let { getDecoratedMeasuredHeight(it) }
            normalViewHeigh = heigh!!
            normalViewWidth = width!!
            var mScaleXY = mScaleX.pow(itemCount - i)

            var left = 0
            var right = width!!
            if (mScrollLength != 0 && tag) {
                offsetY = 0
                tag = false
            }
            //var top: Int = offsetY + mScrollLength
            var top = moreTopBottomSpace + offsetY + (1 / (i + 1) * mScrollLength)
            top = if (top < moreTopBottomSpace / 3) moreTopBottomSpace / 3 else top
            var bottom = moreTopBottomSpace + offsetY + (100 + i * mScrollLength / 3) + heigh
            layoutDecoratedWithMargins(View!!, left, top, right, bottom)
            // offsetY += (heigh * mScaleXY * 0.7).toInt()
            Log.e(" 子视图${i} ", "h: ${heigh}     ")
            offsetY += (heigh * 0.9f * 0.6).toInt()
            AllOffsetY = if (offsetY == 0) AllOffsetY else offsetY
            // Log.e("y值${i}   ", " off55ety: ${offsetY}  AllOffsetY:${(AllOffsetY)}  ")
            mItemCount = if (mItemCount < 0) 0 else mItemCount
            mItemCount = if (mItemCount > 100) 100 else mItemCount
            //  View.z = mScaleXY
            // View.rotationX = itemRotationX
            //Log.e(" 宽高缩放${i} ", "width: ${width} heigh: ${heigh} mScaleXY: ${mScaleXY}")
            //  Log.e(" mScrollLength${i} ", "mScrollLength: ${mScrollLength}  ")
            //
            //  Log.e(" 子视图${i} ", "X: ${View.x}  Y: ${View.y}    ")


            /*   if (i + 1 <= needFoldNumer) {
                   val View = recycler?.getViewForPosition(i)
                   addView(View)
                   var scale = mScaleXFrist
                   View.scaleX = scale
                   View.scaleY = scale
                   View?.let { measureChildWithMargins(it, 0, 0) }
                   val width = View?.let { getDecoratedMeasuredWidth(it) }
                   val heigh = View?.let { getDecoratedMeasuredHeight(it) }
                   //    normalViewHeigh = heigh!!
                   //   normalViewWidth = width!!

                   var left = 0
                   var right = width!!
                   if (mScrollLength != 0 && tag) {
                       offsetY = 0
                       tag = false
                   }
                   var top = 0
                   var bottom: Int = heigh
                   layoutDecoratedWithMargins(View!!, left, top, right, bottom)

                   // offsetY += (heigh * mScaleXY * 0.7).toInt()
                   Log.e(" 子视图${i} ", "h: ${scale * heigh}    w: ${scale * width}     ")
                   Log.e(" 子视图${i} ", "left: ${left}    top: ${top}   right: ${right}   bottom: ${bottom}   ")
                   //  offsetY += (scale * heigh * 0.5f).toInt()
                   offsetY += 0
                   AllOffsetY = 0
                   // Log.e("y值${i}   ", " off55ety: ${offsetY}  AllOffsetY:${(AllOffsetY)}  ")
                   //  mItemCount = if (mItemCount < 0) 0 else mItemCount
                   //  mItemCount = if (mItemCount > 100) 100 else mItemCount
                   //  View.z = mScaleXY
                   // View.rotationX = itemRotationX
                   //Log.e(" 宽高缩放${i} ", "width: ${width} heigh: ${heigh} mScaleXY: ${mScaleXY}")
                   //  Log.e(" mScrollLength${i} ", "mScrollLength: ${mScrollLength}  ")
                   //
                   //  Log.e(" 子视图${i} ", "X: ${View.x}  Y: ${View.y}    ")
               } else {
                   //  Log.e(" 接收到值 ", "mScrollLength: ${mScrollLength}    ")
                   val View = recycler?.getViewForPosition(i)
                   addView(View)
                   var tempY = if (View.y > 2000f) 2000f else View.y
                   var scale: Float = (tempY / 2000f) * 0.8f + 0.4f
                   scale = if (scale > 1.2f) 1.2f else scale
                   scale = if (scale < 0.3f) 0.3f else scale
                   View.scaleX = scale
                   View.scaleY = scale
                   View?.let { measureChildWithMargins(it, 0, 0) }
                   val width = View?.let { getDecoratedMeasuredWidth(it) }
                   val heigh = View?.let { getDecoratedMeasuredHeight(it) }
                   normalViewHeigh = heigh!!
                   normalViewWidth = width!!
                   var mScaleXY = mScaleX.pow(itemCount - i - 1)

                   var left = 0
                   var right = width!!
                   if (mScrollLength != 0 && tag) {
                       offsetY = 0
                       tag = false
                   }
                   //    MotionEvent.
                   var top: Int = (offsetY * mScaleXY).toInt() + mScrollLength
                   var bottom: Int = (heigh) + (offsetY * mScaleXY).toInt() + mScrollLength
                   layoutDecoratedWithMargins(View!!, left, top, right, bottom)

                   // offsetY += (heigh * mScaleXY * 0.7).toInt()
                   Log.e(" 子视图${i} ", "h: ${scale * heigh}    w: ${scale * width}     ")
                   offsetY += (scale * heigh * 0.5f).toInt()
                   AllOffsetY = if (offsetY == 0) AllOffsetY else offsetY
                   // Log.e("y值${i}   ", " off55ety: ${offsetY}  AllOffsetY:${(AllOffsetY)}  ")
                   mItemCount = if (mItemCount < 0) 0 else mItemCount
                   mItemCount = if (mItemCount > 100) 100 else mItemCount
                   //  View.z = mScaleXY
                   View.rotationX = itemRotationX
                   //Log.e(" 宽高缩放${i} ", "width: ${width} heigh: ${heigh} mScaleXY: ${mScaleXY}")
                   //  Log.e(" mScrollLength${i} ", "mScrollLength: ${mScrollLength}  ")
                   //
                   //  Log.e(" 子视图${i} ", "X: ${View.x}  Y: ${View.y}    ")
               }*/
        }

    }

    /**
     *根据滑动的累积距离计算需要折叠多少个item
     * @param SrollLenth 移动的距离
     * @param scale      item依次缩放的比例
     * @param defaultItemHeigh item的默认高度
     * @return int
     */
    private fun calculateNumberOfItemNeedFold(SrollLenth: Int, scale: Float, defaultItemHeigh: Int): NumberOfItemNeedFold {
        var num = 0.0
        var sum = 0
        var show = 0
        while ((defaultItemHeigh / Math.pow(scale.toDouble(), num) + sum).toInt() < SrollLenth) {
            val temp = (200f / Math.pow(scale.toDouble(), num)).toFloat()
            Log.e("temp临时值:$temp", "num : $num")
            sum += (200f * Math.pow(scale.toDouble(), num.toDouble())).toInt()
            num++
            Log.e("次数:$num", "sum: $sum")
            show = SrollLenth - sum
        }
        Log.e("result", num.toString() + "")
        Log.e("show", show.toString() + "")
        return NumberOfItemNeedFold(num.toInt(), show)
    }

}


