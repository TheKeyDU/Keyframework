package com.example.keyframework.activitys

import android.animation.ValueAnimator
import android.view.MotionEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.constants.ARouterPage
import com.maosong.component.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_page.*
import android.view.View


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
                    var offX = x - lastX
                    var offY = y - lastY
                    if (fl_bottom.y + offY >= 0 && fl_bottom.y + offY <= cl_top.height) {
                        fl_bottom.offsetTopAndBottom(offY)
                        cl_top.alpha = (fl_bottom.y + offY) / cl_top.height
                    }
                    lastX = x
                    lastY = y
                }
                MotionEvent.ACTION_UP -> {
                    if (fl_bottom.y < cl_top.height / 2) {
                        ElasticSlide(fl_bottom.y,0,300,fl_bottom)
                    } else {
                        ElasticSlide(fl_bottom.y,cl_top.height,300,fl_bottom)

                    }

                }

            }
        }


        return true

    }

    fun ElasticSlide(fromY: Float, ToY: Int, DurTime: Long, mView:View) {
        var mValueAnimator = ValueAnimator.ofFloat(fromY.toFloat(), ToY.toFloat())
        mValueAnimator.setDuration(DurTime)
        mValueAnimator.addUpdateListener {
            val curValueFloat = it.getAnimatedValue() as Float
            val curValue = curValueFloat.toInt()
            fl_bottom.layout(mView.left, curValue, mView.right, curValue + mView.height)
        }
        mValueAnimator.start()

    }
}