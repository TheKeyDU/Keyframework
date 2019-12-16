package com.example.keyframework.activitys

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.adapter.CustomAdapter
import com.example.keyframework.bean.UserBean
import com.example.keyframework.constants.ARouterPage
import com.maosong.component.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_recylerview.*
import java.util.*

@Route(path = ARouterPage.RECYLERVIEWACTIVITY)
class RecylerActivity : BaseActivity() {
    var customAdapter: CustomAdapter? = null
    var list: ArrayList<UserBean>? = null

    override fun initView() {
    }

    override fun initDate() {
        list = ArrayList<UserBean>()
        for (i in 1..20) {
            list!!.add(object : UserBean(i.toString(), i.toString()) {})
        }
        customAdapter= CustomAdapter(list)
        var layoutManager=LinearLayoutManager(this)
        rec_customize.layoutManager=layoutManager
        rec_customize.adapter=customAdapter
    }

    override fun getContentViewRes(): Int {
        return R.layout.activity_recylerview
    }
}