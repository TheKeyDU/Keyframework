package com.example.keyframework.recylerViewLayoutManager

import android.graphics.Canvas
import android.media.browse.MediaBrowser
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MyItemTouchHelperCallback : ItemTouchHelper.Callback {
    var myItemTouchStatus: MyItemTouchStatus? = null
    var mMaxVisibleCount = 0

    constructor(myItemTouchStatus: MyItemTouchStatus?, mMaxVisibleCount: Int) : super() {
        this.myItemTouchStatus = myItemTouchStatus
        this.mMaxVisibleCount = mMaxVisibleCount
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(0, swipeFlag)

    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false

    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            var ratio = dX / getThreshold(recyclerView, viewHolder)
            if (ratio > 1) {
                ratio = 1f
            } else if (ratio < -1) {
                ratio = -1f
            }
            // 跟着角度旋转
            itemView.rotation = ratio * 15
            for (i in 0..2) {
                // 下面的ItemView跟着手指缩放
                val child = recyclerView.getChildAt(i)
                val currentScale = Math.pow(0.85, (2 - i).toDouble()).toFloat()
                val nextScale = currentScale / 0.85f
                val scale = nextScale - currentScale
                child.scaleX = Math.min(1f, currentScale + scale * Math.abs(ratio))
                child.scaleY = Math.min(1f, currentScale + scale * Math.abs(ratio))
            }
        }

    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        myItemTouchStatus?.onItemRemove(viewHolder.adapterPosition)

    }
    private fun getThreshold(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Float {
        return recyclerView.width * getSwipeThreshold(viewHolder)
    }
    interface MyItemTouchStatus {

        fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

        fun onItemRemove(position: Int): Boolean
    }


}