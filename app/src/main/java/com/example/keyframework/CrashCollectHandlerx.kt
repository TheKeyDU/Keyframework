package com.example.keyframework


import android.content.Context
import android.content.Intent
import android.os.Looper
import android.util.Log
import com.example.keyframework.activitys.SplashActivity
import com.maosong.tools.AppLifeCircleUtil
import com.maosong.tools.ToastUtils
import com.maosong.tools.ToolsApp

public class CrashCollectHandler : Thread.UncaughtExceptionHandler {

    var mContext: Context? = null
    /**
     * 默认的异常处理
     */
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null

    companion object {

        const val TAG = "CrashCollectHandler"

        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CrashCollectHandler() }
    }

    fun init(pContext: Context) {
        this.mContext = pContext
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        if (!handleException(e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler?.uncaughtException(t, e)
        } else {
            //2s后重启app
            try {
                Thread.sleep(2000)
                val intent = Intent(ToolsApp.getAppContext(), SplashActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                ToolsApp.getAppContext().startActivity(intent)
            } catch (e: Exception) {
                Log.e(TAG, "first class error:$e")
            }

            //退出程序
           AppLifeCircleUtil.getInstance().appExit(mContext)
        }

    }

    /**
     * 处理没有捕捉到的异常,
     * @param ex 异常
     */
    private fun handleException(ex: Throwable?): Boolean {
        if (ex == null) {
            return false
        }
        Thread {
            if (Looper.myLooper() == null) {
                Looper.prepare()
            }
            ToastUtils.showShortToast("Sorry. Something went wrong. Please try again.")
            Looper.loop()
        }.start()
        return true
    }
}