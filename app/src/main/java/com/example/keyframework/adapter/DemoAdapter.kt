package com.example.keyframework.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.UserBean

class DemoAdapter(data: MutableList<UserBean>?) : BaseQuickAdapter<UserBean, BaseViewHolder>(R.layout.activity_main, data) {
    override fun convert(helper: BaseViewHolder?, item: UserBean?) {

    }
}