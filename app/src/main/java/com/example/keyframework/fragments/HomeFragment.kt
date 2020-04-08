package com.example.keyframework.fragments

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import anet.channel.util.Utils.context
import com.alibaba.android.arouter.launcher.ARouter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.keyframework.R
import com.example.keyframework.activitys.MainActivity
import com.example.keyframework.adapter.HomeListAdapter
import com.example.keyframework.bean.ImageViewDrawble
import com.example.keyframework.bean.UserBean
import com.example.keyframework.constants.ARouterPage
import com.example.keyframework.fragments.setOnRecylerViewScrollChangeListener
import com.example.keyframework.module.NetModules
import com.example.keyframework.recylerViewLayoutManager.FlowLayoutManager
import com.example.keyframework.recylerViewLayoutManager.RLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.maosong.component.Base.BaseFragment
import com.maosong.tools.QMUIDisplayHelper
import com.maosong.tools.ToastUtils
import kotlinx.android.synthetic.main.fragment_home_list.*

class HomeFragment(var mListener: setOnRecylerViewScrollChangeListener) : BaseFragment() {


    var mHomeListAdapter: HomeListAdapter? = null
    var netModules: NetModules? = null

    var mScrollX = 0
    var myOnRecylerViewScrollChangeListener: setOnRecylerViewScrollChangeListener? = null
    var myScrollListener: MyScrollListener? = null

    class MyScrollListener(var myListener: setOnRecylerViewScrollChangeListener) : RecyclerView.OnScrollListener() {
        var offset = 0
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (myListener != null) {
                offset += dy
                myListener.OnRecylerViewScrollChangeListener(offset)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        srl_rec_home.setOnRefreshListener {
            // initNetRequset()

        }
        initNetRequset()
        setOnRecylerViewScrollChangeListener(mListener)
    }

    private fun addScrollListener() {
        view!!.findViewById<RecyclerView>(R.id.rec_home_lsit).addOnScrollListener(myScrollListener!!)
    }

    fun setOnRecylerViewScrollChangeListener(Listener: setOnRecylerViewScrollChangeListener) {
        this.myOnRecylerViewScrollChangeListener = Listener
        myScrollListener = MyScrollListener(Listener)
        addScrollListener()

    }

    override fun initDate() {
    }

    private fun initNetRequset() {
        netModules = NetModules()
        val disposable = netModules!!.getHomeList()
                .subscribe(
                        { homeListBean ->
                            mHomeListAdapter = HomeListAdapter(homeListBean.newslist)
                            var mRLayoutManager = context?.let { RLayoutManager(it) }
                            //  var mRLayoutManager = context?.let {LinearLayoutManager(it) }
                            rec_home_lsit.adapter = mHomeListAdapter
                            rec_home_lsit.layoutManager = mRLayoutManager
                            setItemOnClick();
                            if (srl_rec_home.isRefreshing) {
                                srl_rec_home.isRefreshing = false
                                Snackbar.make(activity?.findViewById(R.id.cl_root)!!, "refresh success!", 1000).setAction("ok") { }.show()
                            }


                        }, { throwable ->
                    ToastUtils.showLongToast(throwable.message)

                }, {

                }, { disposable1 ->

                }
                )
    }

    private fun setItemOnClick() {

        mHomeListAdapter?.setOnItemClickListener { adapter, view, position ->
            var imageView = view!!.findViewById<ImageView>(R.id.iv_head)
            var layout = view!!.findViewById<ConstraintLayout>(R.id.rl_root)
            imageView.transitionName = "sharedView_iv"
            layout.transitionName = "sharedView_layout"

            var pair1 = androidx.core.util.Pair<View, String>(imageView, "sharedView_iv")
            var pair2 = androidx.core.util.Pair<View, String>(layout, "sharedView_layout")
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this!!.activity!!, pair1, pair2)
            var ivd = ImageViewDrawble(imageView.drawable)
            ARouter.getInstance()
                    .build(ARouterPage.MYPAGE_ACTIVITY)
                    .withOptionsCompat(optionsCompat)
                    .withParcelable("src",ivd)
                    .navigation(activity)


        }

    }

    override fun setFragmentView(): Int {

        return R.layout.fragment_home_list
    }


}


