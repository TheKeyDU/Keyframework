package com.example.keyframework.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.keyframework.Constants.ARouterPage;
import com.example.keyframework.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maosong.component.Base.BaseActivity;
import com.maosong.tools.AppLifeCircleUtil;
import com.maosong.tools.ToastUtils;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import java.util.logging.Handler;

import io.reactivex.internal.schedulers.NewThreadWorker;

@Route(path = ARouterPage.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {
    private TextView mTextMessage;
    Animator animator = null;
    ConstraintLayout constraintLayout = null;
    BottomNavigationView navView = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            BottomNavigationMenuView itemViews = (BottomNavigationMenuView) navView.getChildAt(0);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    itemViewStartAnimation(itemViews.getChildAt(0));
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    itemViewStartAnimation(itemViews.getChildAt(1));
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    itemViewStartAnimation(itemViews.getChildAt(2));
                    return true;
            }
            return false;
        }
    };


    @Override
    public void initView() {
        navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        constraintLayout = findViewById(R.id.container);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        AppLifeCircleUtil.getInstance().finishActivity(AppLifeCircleUtil.activityStack.get(0));
    }

    @Override
    public void initDate() {
        constraintLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startCircularReveal(constraintLayout, constraintLayout.getWidth() / 2, 0, constraintLayout.getHeight(), 700);
            }
        }, 100);
    }

    private void itemViewStartAnimation(View view) {
        startCircularReveal(constraintLayout, (int) view.getX()+view.getWidth()/2, constraintLayout.getHeight(), constraintLayout.getHeight(), 200);
    }

    private void startCircularReveal(View view,
                                     int x,
                                     int y,
                                     int redius,
                                     int time
    ) {
        animator = ViewAnimationUtils.createCircularReveal(
                view,
                x,
                y,
                0, redius);
        animator.setDuration(time);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                constraintLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();


    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_mainbot;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
