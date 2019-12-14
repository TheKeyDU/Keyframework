package com.example.keyframework.recylerViewLayoutManager

import androidx.recyclerview.widget.RecyclerView

class FlowLayoutManager : RecyclerView.LayoutManager {
    constructor() : super() {
        isAutoMeasureEnabled = true
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler!!)
        var offsetX = 0
        for (i in 0..itemCount-1) {
            var mView = recycler.getViewForPosition(i)
            addView(mView)
            measureChildWithMargins(mView, 0, 0)
            var width = getDecoratedMeasuredWidth(mView)
            var height = getDecoratedMeasuredHeight(mView)
            layoutDecorated(mView, 0, offsetX,   width, height+offsetX)
            offsetX = (height * 0.52 + offsetX).toInt()
        }
    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        for (i in 0..itemCount-1) {
            var mView = recycler?.getViewForPosition(i)
            addView(mView)
            mView?.let { measureChildWithMargins(it, 0, 0) }
            var width = mView?.let { getDecoratedMeasuredWidth(it) }
            var height = mView?.let { getDecoratedMeasuredHeight(it) }
            mView?.let { width?.let { it1 -> height?.plus(dy)?.let { it2 -> layoutDecorated(it, 0, dy, it1, it2) } } }
        }
        return (height * 0.52 + dy).toInt()
    }
}