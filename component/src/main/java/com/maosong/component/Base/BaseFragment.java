package com.maosong.component.Base;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.itkey.component.R;
import com.maosong.component.event.MessageEvent;
import com.maosong.component.net.RxNetLife;
import com.maosong.component.view.BaseView;
import com.maosong.component.view.impl.BaseViewImpl;
import com.maosong.tools.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragment extends Fragment implements BaseView {
    protected boolean isFirst = false;
    private Boolean isPrepared = false;
    protected View convertView;
    private String mEmptyText = "";
    private View mEmptyView;
    private BaseViewImpl mBaseViewImpl;

    protected static final TransitionConfig SLIDE_TRANSITION_CONFIG = new TransitionConfig(
            R.anim.slide_in_right, R.anim.slide_out_left,
            R.anim.slide_in_left, R.anim.slide_out_right);

    public static final class TransitionConfig {
        public final int enter;
        public final int exit;
        public final int popenter;
        public final int popout;

        TransitionConfig(int enter, int exit, int popenter, int popout) {
            this.enter = enter;
            this.exit = exit;
            this.popenter = popenter;
            this.popout = popout;
        }
    }

    public TransitionConfig onFetchTransitionConfig() {
        return SLIDE_TRANSITION_CONFIG;
    }

    public Object onLastFragmentFinish() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setFragmentView() != 0) {
            convertView = inflater.inflate(setFragmentView(), container, false);
            mEmptyView = LayoutInflater.from(getContext()).inflate(R.layout.layout_simple_empty_view, null, false);
            TextView tv = mEmptyView.findViewById(R.id.tv_empty_text);
            if (null != tv && !TextUtils.isEmpty(mEmptyText)) {
                tv.setText(mEmptyText);
            }
            ImageView back = convertView.findViewById(R.id.base_toolbar_back);
            if (null != back) {
                back.setOnClickListener(v -> popBackStack());
            }
            return convertView;
        } else {
            return convertView = super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initDate();
        initView();
    }

    /**
     * 在这里取出数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxNetLife.getNetLife().clear(getNetKey());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }


    public abstract int setFragmentView();

    public abstract void initView();

    public abstract void initDate();

    public void startFragment(BaseFragment fragment) {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) getActivity()).startFragment(fragment);
        }

    }


    public void popBackStack() {
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) getActivity()).popBackStack();
        }
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

    protected boolean isFirstRunning() {
        return isFirstRunning(true);
    }

    /**
     * 初次实例化这个类
     *
     * @param clearAble true,可以清除，一般用于首次展示界面
     *                  false,标志不可以被清除,一般用于首次安装
     */
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

    /**
     * 设置firstrun 已经运行过了
     */
    protected void setFirstRuned() {
        SPUtils.getInstance().put("firstRun" + this.getClass().getSimpleName(), false);
        SharedPreferences sp = getContext().getSharedPreferences("first_install_guide", Context.MODE_PRIVATE);
        sp.edit().putBoolean("firstRun" + this.getClass().getSimpleName(), true).apply();
    }

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
