package com.example.keyframework.fragments

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyframework.R
import com.example.keyframework.activitys.MyPage
import com.example.keyframework.adapter.HomeListAdapter
import com.example.keyframework.adapter.OverViewAdapter
import com.example.keyframework.bean.UserBean
import com.example.keyframework.module.NetModules
import com.example.keyframework.recylerViewLayoutManager.HelpSlideCallBack
import com.example.keyframework.widget.OverViewLayoutManager
import com.maosong.component.Base.BaseFragment
import com.maosong.tools.ToastUtils
import kotlinx.android.synthetic.main.fragment_home_list.*

class OverViewListFragment : BaseFragment() {
    var mHomeListAdapter: OverViewAdapter? = null
    var netModules: NetModules? = null
    override fun initView() {
        srl_rec_home.setOnRefreshListener {
            initNetRequset()

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
                            mHomeListAdapter = OverViewAdapter(homeListBean.newslist)
                             var helpCallback=HelpSlideCallBack(object : HelpSlideCallBack.ItemTouchStatus {
                                 override fun onItemMove(fromPosition: Int): Boolean {
                                     return true
                                 }

                                 override fun onItemRemove(position: Int): Boolean {
                                     return true

                                 }
                             })
                            var itemTouchHelper=ItemTouchHelper(helpCallback)
                            rec_home_lsit.adapter = mHomeListAdapter
                            var mLinearLayoutManager = OverViewLayoutManager(rec_home_lsit, itemTouchHelper)
                            rec_home_lsit.layoutManager = mLinearLayoutManager


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