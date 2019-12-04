package com.example.keyframework.widget

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class OverViewLayoutManager : RecyclerView.LayoutManager {
    var mRecyclerView: RecyclerView? =null
    var mItemTouchHelper:ItemTouchHelper?=null
    val mMaxVisibleCount: Int = 0
    private val DEFAULT_SCALE = 0.5f

    constructor(mRecyclerView: RecyclerView?, mItemTouchHelper: ItemTouchHelper?) : super() {
        this.mRecyclerView = mRecyclerView
        this.mItemTouchHelper = mItemTouchHelper
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        var layoutCount = Math.min(itemCount, mMaxVisibleCount)
        detachAndScrapAttachedViews(recycler!!)
        for (i in layoutCount..0) {
            var view = recycler.getViewForPosition(i)
            addView(view)
            measureChildWithMargins(view, 0, 0)
            /*得到view的真实左右长度*/
            var widthSpace = width - getDecoratedMeasuredHeight(view)
            var heightSpace = height - getDecoratedMeasuredHeight(view)
            layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2, widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view))
            view.scaleX = ((Math.pow(DEFAULT_SCALE.toDouble(), i.toDouble()).toFloat()))
            view.scaleY = ((Math.pow(DEFAULT_SCALE.toDouble(), i.toDouble()).toFloat()))
            if (i == 0) {
                view.setOnTouchListener(object :View.OnTouchListener{
                    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                        var childViewHolder=mRecyclerView!!.getChildViewHolder(v!!)
                        if (event!!.actionMasked==MotionEvent.ACTION_DOWN)
                        {
                            mItemTouchHelper!!.startSwipe(childViewHolder)
                        }
                    return false
                    }
                })

            }
            else{
                view.setOnTouchListener(null)
            }
        }

    }

}