package com.example.keyframework.activitys

import android.content.pm.ActivityInfo
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.adapter.CustomAdapter
import com.example.keyframework.bean.UserBean
import com.example.keyframework.constants.ARouterPage
import com.example.keyframework.recylerViewLayoutManager.LinearDecoration
import com.example.keyframework.recylerViewLayoutManager.MyLayoutManager
import com.maosong.component.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_recylerview.*
import java.util.*

@Route(path = ARouterPage.RECYLERVIEWACTIVITY)
class RecylerActivity : BaseActivity() {
    var customAdapter: CustomAdapter? = null
    var list: ArrayList<UserBean>? = null
    var pos = 0;
    override fun initView() {

    }

    override fun initDate() {
        list = ArrayList<UserBean>()
        for (i in 1..7) {
            list!!.add(object : UserBean(i.toString(), i.toString()) {})
        }
        customAdapter = CustomAdapter(list)
        var layoutManager = MyLayoutManager( )
      //  layoutManager.orientation = RecyclerView.HORIZONTAL
        rec_customize.layoutManager = layoutManager
        rec_customize.adapter = customAdapter
        /*  var snapHelper=PagerSnapHelper();
        snapHelper.attachToRecyclerView(rec_customize)*/
        rec_customize.addItemDecoration(object : LinearDecoration(150, 20, 25, 0) {})
        var LinearSnapHelper = LinearSnapHelper()
        //  LinearSnapHelper.attachToRecyclerView(rec_customize)

    }

    override fun onResume() {
        super.onResume()
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    fun smooth(V: View) {
        //rec_customize.smoothScrollToPosition(pos)
        rec_customize.addOnScrollListener(object : MySrcollListenr() {})


    }

    override fun getContentViewRes(): Int {
        return R.layout.activity_recylerview
    }

    open class MySrcollListenr : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Log.e("changed", dx.toString() + "______" + dy.toString())

        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            Log.e("change", newState.toString())

        }
    }
}