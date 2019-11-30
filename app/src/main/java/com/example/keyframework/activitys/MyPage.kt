package com.example.keyframework.activitys

import android.app.usage.UsageEvents
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.constants.ARouterPage
import com.maosong.component.Base.BaseActivity
import com.maosong.tools.QMUIDeviceHelper
import com.maosong.tools.QMUIDisplayHelper
import kotlinx.android.synthetic.main.activity_my_page.*

@Route(path = ARouterPage.MYPAGE_ACTIVITY)
class MyPage : BaseActivity() {
    var topH = 0
    var topW = 0
    var bottomW = 0
    var bottomH = 0
    var lastX = 0
    var lastY = 0
    override fun initView() {
        cl_root.postDelayed({
            SynchronizaitonLayout()
        }, 1)
    }

    override fun initDate() {
    }

    override fun getContentViewRes(): Int {
        return R.layout.activity_my_page
    }

    fun setLayoutsWidthHeigh(topH: Int, topW: Int, bottonW: Int, bottmH: Int) {
        this.topH = topH
        this.topW = topW
        this.bottomW = bottomW
        this.bottomH = bottomH
    }

    fun SynchronizaitonLayout() {
        topH = cl_top.height
        topW = cl_top.width
        bottomH = fl_bottom.height
        bottomW = fl_bottom.width
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            var x = event.x.toInt()
            var y = event.y.toInt()
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = x
                    lastY = y
                }
                MotionEvent.ACTION_MOVE -> {
                    var offX = lastX -x
                    var offY=lastY-y
                    if(offY>0)
                    {
                    fl_bottom.offsetTopAndBottom(offY)
                    }
                }
                MotionEvent.ACTION_UP -> {
                }

            }
        }

        return super.onTouchEvent(event)

    }
}