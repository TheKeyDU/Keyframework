package com.example.keyframework.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyframework.R
import com.example.keyframework.adapter.HomeListAdapter
import com.example.keyframework.bean.UserBean
import com.example.keyframework.module.NetModules
import com.example.keyframework.recylerViewLayoutManager.FlowLayoutManager
import com.example.keyframework.recylerViewLayoutManager.RLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.maosong.component.Base.BaseFragment
import com.maosong.tools.ToastUtils
import kotlinx.android.synthetic.main.fragment_home_list.*

class HomeFragment : BaseFragment() {
    var mHomeListAdapter: HomeListAdapter? = null
    var netModules: NetModules? = null
    override fun initView() {
        srl_rec_home.setOnRefreshListener {
            // initNetRequset()

        }
        initNetRequset()

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
                            rec_home_lsit.adapter = mHomeListAdapter
                            rec_home_lsit.layoutManager = mRLayoutManager
                            if (srl_rec_home.isRefreshing) {
                                srl_rec_home.isRefreshing = false
                                Snackbar.make(ll, "refresh success!", 1000).setAction("ok") { }.show()
                            }


                        }, { throwable ->
                    ToastUtils.showLongToast(throwable.message)

                }, {

                }, { disposable1 ->

                }
                )
    }

    override fun setFragmentView(): Int {

        return R.layout.fragment_home_list
    }

}