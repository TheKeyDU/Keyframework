package com.example.keyframework.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.keyframework.R
import com.example.keyframework.bean.UserBean
import java.util.*

class CustomAdapter(data: MutableList<UserBean>?) : BaseQuickAdapter<UserBean, BaseViewHolder>(R.layout.item_customize, data) {
    @SuppressLint("NewApi")
    override fun convert(helper: BaseViewHolder?, item: UserBean?) {
        helper!!.setText(R.id.tv_num, item?.name)
        var mConstraintLayout=helper.itemView.findViewById<ConstraintLayout>(R.id.cly_root)
        var random =   Random()
            var R_= random.nextInt(256).toFloat()
            var G_ = random.nextInt(256).toFloat()
            var B_ = random.nextInt(256).toFloat()
          var  Color= Color.argb(1f,R_,G_,B_)
        mConstraintLayout.setBackgroundColor(Color)
    }
}