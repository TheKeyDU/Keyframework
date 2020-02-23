package com.dqj.interstellar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager

class pointActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
// 隐藏状态栏
// 定义全屏参数
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
// 获得当前窗体对象
        val window = this@pointActivity.getWindow()
// 设置当前窗体为全屏显示
        window.setFlags(flag, flag)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        setContentView(R.layout.point)
    }
}