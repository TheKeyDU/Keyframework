package com.dqj.interstellar

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.point.*

class pointActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
// 隐藏状态栏
// 定义全屏参数
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
// 获得当前窗体对象
        val window = this@pointActivity.getWindow()
        setContentView(R.layout.point)

// 设置当前窗体为全屏显示
        window.setFlags(flag, flag)
        /*      window.setFlags(
                      WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                      WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);*/
        setContentView(R.layout.point)
        initStart();
        Toast.makeText(this, "可以点击或者拖动屏幕，点击下面按钮进入第二个界面",Toast.LENGTH_LONG).show()
    }

    private fun initStart() {
        cl.postDelayed({
            btn.visibility = View.GONE
        }, 2000)
    }

    fun jump(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}