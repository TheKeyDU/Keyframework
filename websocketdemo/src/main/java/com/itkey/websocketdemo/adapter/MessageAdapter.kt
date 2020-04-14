package com.itkey.websocketdemo.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.itkey.websocketdemo.R
import com.itkey.websocketdemo.bean.MessageBean
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2020/4/14 10:17
 */
  class MessageAdapter  (data: ArrayList<MessageBean>?) : BaseMultiItemQuickAdapter<MessageBean, BaseViewHolder>(data) {

    init {
       addItemType(MessageBean.TYPE_ME, R.layout.item_message_sent)
        addItemType(MessageBean.TYPE_ME, R.layout.item_message_receive)
        addItemType(MessageBean.TYPE_SYSYTEM, R.layout.item_message_receive)
        //
    }

    override fun convert(helper: BaseViewHolder?, item: MessageBean?) {
        when (helper!!.itemViewType) {
            MessageBean.TYPE_ME -> {
                 helper.setText(R.id.tv_sent, item?.message)

            }
            MessageBean.TYPE_OTHER -> {
               helper.setText(R.id.tv_rec, item?.message)
            }
            MessageBean.TYPE_SYSYTEM -> {

            }
        }
    }
}