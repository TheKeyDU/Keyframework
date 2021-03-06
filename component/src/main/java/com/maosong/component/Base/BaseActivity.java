package com.maosong.component.Base;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.alibaba.android.arouter.launcher.ARouter;
import com.itkey.component.R;
import com.maosong.component.event.ForceFinishEvent;
import com.maosong.component.event.MessageEvent;
import com.maosong.component.net.RxNetLife;
import com.maosong.component.view.BaseView;
import com.maosong.component.view.impl.BaseViewImpl;
import com.maosong.tools.AppLifeCircleUtil;
import com.maosong.tools.LogUtil;
import com.maosong.tools.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity extends ImmersionActivity implements BaseView {
    protected BaseFragment mFragment;
    protected Activity activity;
    protected boolean isAlive;
    private String mEmptyText = "";
    private View mEmptyView;
    private BaseView mBaseViewImpl;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        EventBus.getDefault().register(this);
        AppLifeCircleUtil.getInstance().pushActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ARouter.getInstance().inject(this);
        if (getContentViewRes() != 0) {
            setContentView(getContentViewRes());
        }
        initSupportShareElement();
        initView();
        initDate();
    }

    private void initSupportShareElement() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        }
    }

    public abstract void initView();

    public abstract void initDate();

    private void initInner() {
        mEmptyView = LayoutInflater.from(this).inflate(R.layout.layout_simple_empty_view, null, false);
        TextView tv = mEmptyView.findViewById(R.id.tv_empty_text);
        mBaseViewImpl = new BaseViewImpl(this);

    }

    public boolean jumpActivity(String path) {
        ARouter.getInstance().build(path).navigation();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isAlive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAlive = false;
    }

    public abstract int getContentViewRes();


    @Override
    public void onBackPressed() {
        BaseFragment fragment = getCurrentFragment();
        if (fragment != null) {
            fragment.popBackStack();
        } else {
            popBackStack();
//            if (!isFinishing())
//                super.onBackPressed();
        }
    }

    public Context getContext() {
        return this;
    }

    public BaseFragment getCurrentFragment() {
        int contextViewId = getContentViewRes();
        if (contextViewId != 0) {
            return (BaseFragment) getSupportFragmentManager().findFragmentById(getContentViewRes());
        } else {

            return null;
        }
    }

    public void startFragment(BaseFragment fragment) {
    }


    protected boolean isFirstRunning() {
        return isFirstRunning(true);
    }


    protected boolean isFirstRunning(boolean clearAble) {
        boolean firstRun = true;
        if (clearAble) {
            firstRun = SPUtils.getInstance().getBoolean("firstRun" + this.getClass().getSimpleName(), true);
        } else {
            SharedPreferences sp = getContext().getSharedPreferences("first_install_guide", Context.MODE_PRIVATE);
            firstRun = sp.getBoolean("firstRun" + this.getClass().getSimpleName(), true);
        }
        return firstRun;
    }

    protected void setFirstRuned() {
        SPUtils.getInstance().put("firstRun" + this.getClass().getSimpleName(), false);
        SharedPreferences sp = getContext().getSharedPreferences("first_install_guide", Context.MODE_PRIVATE);
        sp.edit().putBoolean("firstRun" + this.getClass().getSimpleName(), true).apply();
    }

    public void popBackStack() {
        LogUtil.i("popBackStack: getSupportFragmentManager().getBackStackEntryCount() = " + getSupportFragmentManager().getBackStackEntryCount());
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            BaseFragment fragment = getCurrentFragment();
            if (fragment == null) {
                finish();
                return;
            }
            BaseFragment.TransitionConfig transitionConfig = fragment.onFetchTransitionConfig();
            Object toExec = fragment.onLastFragmentFinish();
            if (toExec != null) {
                if (toExec instanceof BaseFragment) {
                    BaseFragment mFragment = (BaseFragment) toExec;
                    startFragment(mFragment);
                } else if (toExec instanceof Intent) {
                    Intent intent = (Intent) toExec;
                    startActivity(intent);
                    overridePendingTransition(transitionConfig.popenter, transitionConfig.popout);
                    finish();
                } else {
                    throw new Error("can not handle the result in onLastFragmentFinish");
                }
            } else {
                finish();
            }
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onDestroy() {
        AppLifeCircleUtil.getInstance().removeActivity(this);

        activity = null;
        EventBus.getDefault().unregister(this);
        RxNetLife.getNetLife().clear(getNetKey());
        //setFirstRuned();
        super.onDestroy();
    }

    @Override
    public String getNetKey() {
        return getClass().getSimpleName();
    }

    public void setEmptyViewText(String text) {
        mEmptyText = text;
        if (null != mEmptyView) {
            TextView tv = mEmptyView.findViewById(R.id.tv_empty_text);
            if (null != tv && !TextUtils.isEmpty(mEmptyText)) {
                tv.setText(mEmptyText);
            }
        }
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        if (messageEvent instanceof ForceFinishEvent) {
            if (activity != null && activity.getClass().getSimpleName().equals(((ForceFinishEvent) messageEvent).getClassName())) {
                activity.finish();
            }
        }
    }

    /**
     * Baseview 相关
     */
    @Override
    public void showLoading() {
        mBaseViewImpl.showLoading();
    }

    @Override
    public void showLoading(String message) {
        mBaseViewImpl.showLoading(message);
    }

    @Override
    public void dismissLoading() {
        mBaseViewImpl.dismissLoading();
    }

    @Override
    public void showTipMessage(String msg) {
        mBaseViewImpl.showTipMessage(msg);
    }


}

