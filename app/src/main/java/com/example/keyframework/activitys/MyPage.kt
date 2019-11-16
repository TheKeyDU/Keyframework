package com.example.keyframework.activitys

import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.constants.ARouterPage
import com.maosong.component.Base.BaseActivity

@Route(path = ARouterPage.MYPAGE_ACTIVITY)
class MyPage: BaseActivity() {
    override fun initView() {
    }

    override fun initDate() {
    }

    override fun getContentViewRes(): Int {
        return R.layout.activity_my_page
    }
}