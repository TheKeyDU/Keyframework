package com.example.keyframework.widget

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverViewLayoutManager:RecyclerView.LayoutManager() {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if(state!!.itemCount==0||state.isPreLayout)
            return
        removeAndRecycleAllViews(recycler!!)

        super.onLayoutChildren(recycler, state)
    }

    override fun addView(child: View?) {
        super.addView(child)
    }
}