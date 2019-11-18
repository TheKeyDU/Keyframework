package com.example.keyframework.fragments

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.keyframework.R
import com.maosong.component.Base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home_list.*

class HomeFragment : BaseFragment() {
    override fun initView() {
        srl_rec_home.setOnRefreshListener{

        }
    }

    override fun initDate() {
    }

    override fun setFragmentView(): Int {

        return R.layout.fragment_home_list
    }

}