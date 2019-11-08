package com.example.keyframework;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.Constants.MyConstant;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.xiaomi.MiPushRegistar;

public class MyApplication extends Application {
    private boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        initUmengPush();
        initMIUIPush();
        initARout();
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
