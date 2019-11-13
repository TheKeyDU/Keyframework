package com.example.keyframework.activitys;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.constants.ARouterPage;
import com.maosong.component.Base.BaseActivity;

@Route(path = ARouterPage.SPLASH_ACTIVITY)
public class SplashActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        jumpAndKillSelf();

    }

    private void jumpAndKillSelf() {
        ARouter.getInstance().build(ARouterPage.MAIN_ACTIVITY).navigation(this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
              //  onDestroy();

            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public int getContentViewRes() {
        return 0;
    }
}
