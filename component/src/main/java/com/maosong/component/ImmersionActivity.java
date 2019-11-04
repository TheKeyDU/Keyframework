package com.maosong.component;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.maosong.tools.StatusBarUtils;


/**
 * 沉浸式上层Activity, 可独立出来
 * 默认状态栏 theme底 黑字 留出状态栏高度.
 *
 * @author zhouhao
 * @since 2018/7/10
 */
public class ImmersionActivity extends AppCompatActivity {

    protected View rootView;
    protected int mPhoneType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (needStatusPlaceHolder()) {
            // 添加RootView
            LinearLayout rootView = new LinearLayout(this);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rootView.setOrientation(LinearLayout.VERTICAL);

            // 添加statusBar的placeHolder.
            View view = new View(this);
            view.setBackgroundColor(StatusBarUtils.STATUS_COLOR);
            rootView.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, StatusBarUtils.getStatusBarHeight(this)));
            this.rootView = rootView;
        }

        // 设置状态栏
        mPhoneType = StatusBarUtils.setStatusTheme(this, isStatusDark(), isFullScreen());
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        if (needStatusPlaceHolder()) {
            ((LinearLayout) rootView).addView(view);
        } else {
            rootView = view;
        }
        if (!isStatusDark() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(StatusBarUtils.STATUS_COLOR);
        }
        super.setContentView(rootView);
    }


    /**
     * 获取RootView
     *
     * @return 返回contentView
     */
    public View getRootView() {
        return rootView;
    }

    /**
     * 是否状态栏文字白色
     *
     * @return 默认否
     */
    protected boolean isStatusDark() {
        return false;
    }

    /**
     * 是否全屏
     *
     * @return 默认非全屏
     */
    protected boolean isFullScreen() {
        return false;
    }

    /**
     * 是否添加statusBar的placeHolder
     *
     * @return 默认否, 全屏的时候可以考虑添加 或者 直接通过fitSystemWindow=true留出statusBar的高度.
     */
    protected boolean needStatusPlaceHolder() {
        return false;
    }
}