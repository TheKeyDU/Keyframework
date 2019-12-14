package com.example.keyframework.fragments

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.keyframework.R
import com.example.keyframework.adapter.OverViewAdapter
import com.example.keyframework.module.NetModules
import com.example.keyframework.recylerViewLayoutManager.MyItemTouchHelperCallback
import com.example.keyframework.recylerViewLayoutManager.OverViewLayoutManager
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
                            var mMyItemTouchHelperCallback = MyItemTouchHelperCallback(mHomeListAdapter, 10)
                            var itemTouchHelper = ItemTouchHelper(mMyItemTouchHelperCallback)
                            var mLinearLayoutManager = OverViewLayoutManager(rec_home_lsit,
                                    10, itemTouchHelper)
                            rec_home_lsit.layoutManager = mLinearLayoutManager
                            rec_home_lsit.adapter = mHomeListAdapter
                            itemTouchHelper.attachToRecyclerView(rec_home_lsit)


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