package com.example.keyframework.recylerViewLayoutManager

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.dynamic.IFragmentWrapper

class OverViewLayoutManager : RecyclerView.LayoutManager  {
    var mRecyclerView: RecyclerView? = null
    var mMaxVisibleCount: Int = 0
    var mItemTouchHelper: ItemTouchHelper? = null

    constructor(mRecyclerView: RecyclerView?, mMaxVisibleCount: Int, mItemTouchHelper: ItemTouchHelper?) : super() {
        this.mRecyclerView = mRecyclerView
        this.mMaxVisibleCount = mMaxVisibleCount
        this.mItemTouchHelper = mItemTouchHelper
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT)

    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        recycler?.let { detachAndScrapAttachedViews(it) }
        var layoutCount = Math.min(itemCount, mMaxVisibleCount)
        while (layoutCount-1>0) {
            var mView = recycler?.getViewForPosition(layoutCount)
            addView(mView)
            measureChildWithMargins(mView!!, 0, 0)
            val widthSpace = width - getDecoratedMeasuredWidth(mView!!)
            val heightSpace = height - getDecoratedMeasuredHeight(mView!!)
            layoutDecoratedWithMargins(mView!!, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(mView!!),
                    heightSpace / 2 + getDecoratedMeasuredHeight(mView!!))
            if (layoutCount == 0) {
                mView?.setOnTouchListener(object : View.OnTouchListener {
                    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                        var childHolder = v?.let { mRecyclerView?.getChildViewHolder(it) }
                        if (event?.actionMasked == MotionEvent.ACTION_DOWN) {
                            childHolder?.let { mItemTouchHelper?.startSwipe(it) }
                        }
                        return false

                    }
                })
            } else {
                mView?.setOnTouchListener(null)
            }
        --layoutCount
        }
    }

}