package com.example.keyframework;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.Constants.MyConstant;
import com.example.keyframework.activity.MainActivity;
import com.example.keyframework.activity.SplashActivity;
import com.maosong.tools.AppLifeCircleUtil;
import com.maosong.tools.ToastUtils;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.xiaomi.MiPushRegistar;

/**
 * @author Administrator
 */
public class MyApplication extends Application {
    private boolean isDebug = true;
    private Context context = MyApplication.this;

    @Override
    public void onCreate() {
        super.onCreate();
        initCrashWithReboot();
        initUmengPush();
        initMIUIPush();
        initARout();
    }

    private void initCrashWithReboot() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                if (!handleException(e) &&
                        Thread.getDefaultUncaughtExceptionHandler() != null) {
                    Thread.getDefaultUncaughtExceptionHandler().uncaughtException(t, e);
                    AppLifeCircleUtil.getInstance().appExit(MyApplication.this);

                } else {
                    try {
                        Thread.sleep(1000);
                       /* AppLifeCircleUtil.getInstance().finishAllActivity();
                        AppLifeCircleUtil.getInstance().appExit(MyApplication.this);*/
                        // android.os.Process.killProcess(android.os.Process.myPid());
                        // System.exit(0);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        @SuppressLint("WrongConstant") PendingIntent restartIntent = PendingIntent.getActivity(
                                getApplicationContext(), 0, intent,
                                Intent.FLAG_ACTIVITY_NEW_TASK);
                        //退出程序
                        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                                restartIntent);
                      //  AppLifeCircleUtil.getInstance().finishAllActivity();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }

            }
        });

    }

    private boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        }
        new Thread() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                super.run();
                if (Looper.myLooper() == null) {
                    Looper.prepare();
                }
                Toast.makeText(MyApplication.this, e.toString(), 2000).show();
                Looper.loop();
            }
        }.start();
        return true;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    private void initARout() {
        if (isDebug) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(MyApplication.this);
    }

    private void initUmengPush() {
        UMConfigure.init(this,
                MyConstant.UMENGAPPKEY,
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                MyConstant.UMENGMS);
        PushAgent pushAgent = PushAgent.getInstance(this);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                if (isDebug) {
                    Log.e("-----ok", "UMENG---DEVICE--TOKEN--SUCCESS:" + deviceToken);
                }
            }

            @Override
            public void onFailure(String s, String s1) {
                if (isDebug) {
                }
                Log.e("-----failure", "UMENG---DEVICE--TOKEN--FAILURE:" + s + " " + s1);

            }
        });
    }

    private void initMIUIPush() {
        MiPushRegistar.register(this, MyConstant.xiaomiID, MyConstant.xiaomiAppKey);
    }


}