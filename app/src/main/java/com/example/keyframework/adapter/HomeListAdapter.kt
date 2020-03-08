package com.example.keyframework.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.HomeListBean
import java.util.*

class HomeListAdapter(date: MutableList<HomeListBean.NewslistBean>) : BaseQuickAdapter<HomeListBean.NewslistBean, BaseViewHolder>
(R.layout.item_content_small, date) {
    var imageView: ImageView? = null
    override fun convert(helper: BaseViewHolder?, item: HomeListBean.NewslistBean?) {
        imageView = helper?.getView(R.id.iv_head)
        Glide.with(mContext).load(item?.image)
                . into(this!!.imageView!!)

        helper?.setText(R.id.tv_title, item?.title)
        helper?.setText(R.id.tv_time, item?.postdate)
        /*    var layout=helper?.itemView?.findViewById<RelativeLayout>(R.id.rl_root)
            var random =   Random()
            var R_= random.nextInt(256).toFloat()
            var G_ = random.nextInt(256).toFloat()
            var B_ = random.nextInt(256).toFloat()
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               var  Color=   Color.argb(1f,R_,G_,B_)
                layout?.setBackgroundColor(Color)

            } else {
            }*/
    }


}