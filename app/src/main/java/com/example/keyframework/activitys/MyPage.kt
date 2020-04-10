package com.example.keyframework.activitys

import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.keyframework.R
import com.example.keyframework.constants.ARouterPage
import com.maosong.component.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_my_page.*
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.example.keyframework.bean.HomeListBean


@Route(path = ARouterPage.MYPAGE_ACTIVITY)
class MyPage : BaseActivity() {
    var topH = 0
    var topW = 0
    var bottomW = 0
    var bottomH = 0
    var lastX = 0
    var lastY = 0

    @JvmField
    @Autowired(name = "src")
     var mBundle: Bundle? =null




    override fun initView() {
        cl_root.postDelayed({
            SynchronizaitonLayout()
        }, 1)
       // iv_clbg.setImageBitmap(mBundle!!.getParcelable<Bitmap>("src"))
        iv_clbg.setImageDrawable(resources.getDrawable(R.drawable.bg_round_count))
        fl_bottom.visibility=View.VISIBLE
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
        topH = iv_clbg.height
        topW = iv_clbg.width
        bottomH = fl_bottom.height
        bottomW = fl_bottom.width
        var bean= mBundle!!.getParcelable<HomeListBean.NewslistBean>("bean")
        tv_details_mypage.setText(bean?.description)
        tv_title_mypage.setText(bean?.title)

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
                    if (fl_bottom.y + offY >= 0 && fl_bottom.y + offY <= iv_clbg.height) {
                        fl_bottom.offsetTopAndBottom(offY)
                        iv_clbg.alpha = (fl_bottom.y + offY) / iv_clbg.height + 0.3f

                    }
                    lastX = x
                    lastY = y
                    iv_clbg.scaleX = (fl_bottom.y + offY) / iv_clbg.height
                    iv_clbg.scaleY = (fl_bottom.y + offY) / iv_clbg.height
                }
                MotionEvent.ACTION_UP -> {
                    if (fl_bottom.y < iv_clbg.height / 1.5) {
                        ElasticSlide(fl_bottom.y, 0, 300, fl_bottom, object : slideCallBack {
                            override fun callback(percent: Float) {
                                iv_clbg.scaleX = percent
                                iv_clbg.scaleY = percent
                            }
                        })
                    } else {
                        ElasticSlide(fl_bottom.y, iv_clbg.height, 300, fl_bottom, object : slideCallBack {
                            override fun callback(percent: Float) {
                                iv_clbg.scaleX = percent
                                iv_clbg.scaleY = percent
                            }
                        })

                    }

                }

            }
        }


        return true

    }

    fun ElasticSlide(fromY: Float, ToY: Int, DurTime: Long, mView: View, callBack: slideCallBack) {
        var mValueAnimator = ValueAnimator.ofFloat(fromY.toFloat(), ToY.toFloat())
        mValueAnimator.setDuration(DurTime)
        mValueAnimator.addUpdateListener {
            val curValue = it.getAnimatedValue() as Float
            fl_bottom.layout(mView.left, curValue.toInt(), mView.right, (curValue + mView.height).toInt())
            //   LogUtil.e(",toY:${ToY},fromY:${fromY},inv${curValue},xxxx=${curValue / (ToY - fromY)}")
            var percent: Float = curValue / topH
            Log.e("**********", percent.toString())
            callBack.callback(percent = percent)
        }
        mValueAnimator.start()

    }

    interface slideCallBack {
        fun callback(percent: Float)
    }


    /*override fun onStop() {
        finishAfterTransition()
        super.onStop()
    }*/
}