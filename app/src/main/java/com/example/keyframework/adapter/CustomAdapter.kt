package com.example.keyframework.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.UserBean

class CustomAdapter(data: MutableList<UserBean>?) : BaseQuickAdapter<UserBean, BaseViewHolder>(R.layout.item_customize, data) {
    override fun convert(helper: BaseViewHolder?, item: UserBean?) {
        helper!!.setText(R.id.tv_num, item?.name)
    }
}