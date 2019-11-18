package com.example.keyframework.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keyframework.R
import com.example.keyframework.adapter.HomeListAdapter
import com.example.keyframework.bean.UserBean
import com.maosong.component.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_list.*

class HomeFragment : BaseFragment() {
    var mHomeListAdapter: HomeListAdapter? = null
    var mUserBean: UserBean? = null
    override fun initView() {
        mUserBean = UserBean("name", "123456")
        var date = mutableListOf<UserBean>()
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        date.add(mUserBean!!)
        mHomeListAdapter = HomeListAdapter(date)
        var mLinearLayoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        rec_home_lsit.layoutManager=mLinearLayoutManager
        rec_home_lsit.adapter=mHomeListAdapter
        srl_rec_home.setOnRefreshListener {
        }
    }

    override fun initDate() {
    }

    override fun setFragmentView(): Int {

        return R.layout.fragment_home_list
    }

}