package com.example.keyframework;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.maosong.tools.LogUtil;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;

import org.android.agoo.xiaomi.MiPushRegistar;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initUmengPush();
        initMIUIPush();
    }

    private void initUmengPush() {
        UMConfigure.init(this,
                MyConstant.UMENGAPPKEY,
                "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                MyConstant.UMENGMS);
                PushAgent pushAgent=PushAgent.getInstance(this);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                Log.e("-----ok","UMENG---DEVICE--TOKEN--SUCCESS:"+deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e("-----failure","UMENG---DEVICE--TOKEN--FAILURE:"+s+" "+s1);

            }
        });
    }
    private void initMIUIPush(){
        MiPushRegistar.register(this,MyConstant.xiaomiID,MyConstant.xiaomiAppKey);
    }
}
