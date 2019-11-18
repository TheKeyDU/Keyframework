package com.example.keyframework.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.UserBean

class HomeListAdapter(data: MutableList<UserBean>?) : BaseQuickAdapter<UserBean, BaseViewHolder>
(R.layout.item_content_small, data)
{
    override fun convert(helper: BaseViewHolder?, item: UserBean?) {
    helper?.setText(R.id.tv_title,item?.name)
    helper?.setText(R.id.tv_from,item?.id)
        val ImageView=helper?.getView<ImageView>(R.id.iv_head)
        Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").into(ImageView!!)
    }
}