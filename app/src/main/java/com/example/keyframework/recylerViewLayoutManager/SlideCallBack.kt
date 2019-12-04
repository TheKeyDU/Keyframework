package com.example.keyframework.recylerViewLayoutManager
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class HelpSlideCallBack : ItemTouchHelper.Callback {


    interface ItemTouchStatus {

        fun onItemMove(fromPosition: Int): Boolean

        fun onItemRemove(position: Int): Boolean
    }

    private val DEFAULT_SCALE = 0.85f
    private var mItemTouchStatus: ItemTouchStatus? = null

    constructor(mItemTouchStatus: ItemTouchStatus?) : super() {
        this.mItemTouchStatus = mItemTouchStatus
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        /*
        * 设置拖动和滑动的标记
        * dragFlage是拖动的标记，item可以随着哪一个方向拖动
        * swipeFlage是滑动的标记，比如最近任务往右边滑动直接删除组这个item
        * */
        // var drageFlag =ItemTouchHelper.UP or ItemTouchHelper.DOWN
        var swipeFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(0, swipeFlag)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        var ItemView=viewHolder.itemView
        ItemView.translationX=dX
        ItemView.translationY=dY
    }
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
    viewHolder.itemView.rotation=0f
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        mItemTouchStatus!!.onItemMove(viewHolder.adapterPosition)
    }
}