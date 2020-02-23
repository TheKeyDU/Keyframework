package com.dqj.interstellar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.Window.FEATURE_NO_TITLE
import androidx.core.content.ContextCompat.getSystemService


class MainActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_main)
   /*     window.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);*/
    }

    fun jump(view: View) {
        startActivity(Intent(this, pointActivity::class.java))
    }
}
