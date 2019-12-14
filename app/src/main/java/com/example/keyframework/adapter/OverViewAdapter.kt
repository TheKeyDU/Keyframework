package com.example.keyframework.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.HomeListBean
import com.example.keyframework.recylerViewLayoutManager.MyItemTouchHelperCallback

class OverViewAdapter(data: MutableList<HomeListBean.NewslistBean>?) : BaseQuickAdapter<HomeListBean.NewslistBean, BaseViewHolder>(R.layout.item_overview_pager, data),MyItemTouchHelperCallback.MyItemTouchStatus {
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return true
    }

    override fun onItemRemove(position: Int): Boolean {
        data.removeAt(position)
        notifyDataSetChanged()
        return true
    }


    override fun convert(helper: BaseViewHolder?, item: HomeListBean.NewslistBean?) {


    }
}