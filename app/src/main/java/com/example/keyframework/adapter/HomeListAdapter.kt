package com.example.keyframework.adapter

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.HomeListBean

class HomeListAdapter(date:MutableList<HomeListBean.NewslistBean>) : BaseQuickAdapter<HomeListBean.NewslistBean, BaseViewHolder>
(R.layout.item_content_small ,date)
{
    var imageView: ImageView? =null
    override fun convert(helper: BaseViewHolder?, item: HomeListBean.NewslistBean?) {
        imageView=helper?.getView(R.id.iv_head)
        Glide.with(mContext).load(item?.image).into(this!!.imageView!!)
        Log.e("*****",item?.description)
        Log.e("*****",item?.postdate)
        Log.e("*****",item?.image)
        helper?.setText(R.id.tv_title,item?.description)
        helper?.setText(R.id.tv_time,item?.postdate)
    }

}