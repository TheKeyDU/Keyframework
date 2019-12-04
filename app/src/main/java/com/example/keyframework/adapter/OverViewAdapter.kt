package com.example.keyframework.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.HomeListBean
import com.example.keyframework.bean.UserBean

class OverViewAdapter(data: MutableList<HomeListBean.NewslistBean>?) : BaseQuickAdapter<HomeListBean.NewslistBean, BaseViewHolder>(R.layout.item_overview_pager, data) {
    override fun convert(helper: BaseViewHolder?, item: HomeListBean.NewslistBean?) {


    }
}