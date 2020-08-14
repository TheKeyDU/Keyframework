package com.dqj.interstellar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.Window.FEATURE_NO_TITLE
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getSystemService
import com.dqj.interstellar.StarTrailsView.StarTrailsView
import kotlinx.android.synthetic.main.activity_select.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var key = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
// 隐藏状态栏
// 定义全屏参数
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
// 获得当前窗体对象
        val window = this@MainActivity.getWindow()
// 设置当前窗体为全屏显示
        window.setFlags(flag, flag)
        setContentView(R.layout.activity_select)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_show_hide.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_hide -> {
                if (btn_1.visibility == View.VISIBLE) {
                    ll_root.setVerticalGravity(View.GONE)
                } else {
                    ll_root.setVerticalGravity(View.VISIBLE)
                }
            }
            R.id.btn_1 -> {
                key = 1
            }
            R.id.btn_2 -> {
                key = 2
            }
            R.id.btn_3 -> {
                key = 3
            }
            R.id.btn_4 -> {
                key = 4
            }

        }
swithView(key)
    }

    fun swithView(key: Int) {
        when (key) {
            1 -> {
                fl_root.removeView(duqijian)
                var view = StarTrailsView(this)
                view.id = R.id.duqijian
                var lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                view.layoutParams = lp
                view.isClickable = true
                fl_root.addView(view)
            }
            2 -> {
                fl_root.removeView(duqijian)
                var view = InterstellarPonitView(this)
                view.id = R.id.duqijian

                var lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                view.layoutParams = lp
                view.isClickable = true
                fl_root.addView(view)
            }
            3 -> {
                fl_root.removeView(duqijian)
                var view = InterstellarView(this)
                view.id = R.id.duqijian

                var lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                view.layoutParams = lp
                view.isClickable = true
                fl_root.addView(view)
            }
            4 -> {
                fl_root.removeView(duqijian)
                var view = LinesFlyView(this)
                view.id = R.id.duqijian

                var lp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                view.layoutParams = lp
                view.isClickable = true
                fl_root.addView(view)
            }
        }
    }

}
