package com.example.keyframework.activitys;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.constants.ARouterPage;
import com.example.keyframework.R;
import com.example.keyframework.adapter.HomePagerFragmentAdapter;
import com.example.keyframework.fragments.HomeFragment;
import com.example.keyframework.fragments.OverViewListFragment;
import com.example.keyframework.module.NetModules;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.maosong.component.Base.BaseActivity;
import com.maosong.component.widget.Rotate3dAnimation;
import com.maosong.tools.AppLifeCircleUtil;
import com.maosong.tools.StatusBarUtil;
import com.maosong.tools.SystemBarTintManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;


import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Administrator
 */
@Route(path = ARouterPage.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        Animator.AnimatorListener {
    private int FristPageAnimation = 400;
    private int MenuViewItemBig2Small = 400;
    private int MenuViewItemSmall2Big = 200;
    private Context mContext;
    ImageView imageView = null;
    private NetModules netModules;
    Toolbar toolbar = null;
    CoordinatorLayout coordinatorLayout = null;
    HomePagerFragmentAdapter homePagerFragmentAdapter = null;
    androidx.drawerlayout.widget.DrawerLayout mDrawerLayout = null;
    List<Fragment> fragments = null;
    BottomNavigationView navView = null;
    ViewPager mViewPager = null;
    private int whichAnimation = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        BottomNavigationMenuView itemViews = (BottomNavigationMenuView) navView.getChildAt(0);
        switch (item.getItemId()) {
            case R.id.navigation_home:
                setWhichAnimationTag(0);
                startBig2samllAnimation(MenuViewItemBig2Small, mDrawerLayout.getWidth(), 0, MainActivity.this);
                return true;
            case R.id.navigation_dashboard:
                setWhichAnimationTag(1);
                startBig2samllAnimation(MenuViewItemBig2Small, mDrawerLayout.getWidth() / 2, 0, MainActivity.this);
                return true;
            case R.id.navigation_notifications:
                setWhichAnimationTag(2);
                startBig2samllAnimation(MenuViewItemBig2Small, 0, 0, MainActivity.this);
                return true;
        }
        return false;
    };

    private synchronized void setWhichAnimationTag(int tag) {
        whichAnimation = tag;
    }

    private synchronized int getWhichAnimationTag() {
        return whichAnimation;
    }

    @Override
    public void initView() {
        netModules = new NetModules();
        initViewPagerWithFragments();
        mContext = this.getContext();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.cl_center);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.dwl_root);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // mDrawerLayout.addDrawerListener(toggle);
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //    Log.e("sile--------------",slideOffset+"");
                int w = drawerView.getWidth();
                int h = drawerView.getHeight();
                float scale = 0.9f;
                if ((1 - slideOffset) > scale) {
                    coordinatorLayout.setX(slideOffset * w);
                } else {
                    coordinatorLayout.setX(slideOffset * w);
                }
                final float scaleNum = (1 - slideOffset) > scale ? (1 - slideOffset) : scale;
                coordinatorLayout.setScaleX(scaleNum);
                coordinatorLayout.setScaleY(scaleNum);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navView = findViewById(R.id.nav_bot);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        AppLifeCircleUtil.getInstance().finishActivity(AppLifeCircleUtil.activityStack.get(0));
        initListener();
        setStatbarColor();


    }

    private void setStatbarColor() {
        StatusBarUtil.setStatusBarColor(this, 3948353);
    }

    public static String execByRuntime(String cmd) {
        Process process = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            inputStreamReader = new InputStreamReader(process.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);

            int read;
            char[] buffer = new char[4096];
            StringBuilder output = new StringBuilder();
            while ((read = bufferedReader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            Log.e("shell------dqj",output.toString());
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (Throwable t) {
                    //
                }
            }
            if (null != process) {
                try {
                    process.destroy();
                } catch (Throwable t) {
                    //
                }
            }
        }
    }




    private void initListener() {
        try {
            Field field = toolbar.getClass().getDeclaredField("mLogoView");
            field.setAccessible(true);
            imageView = (ImageView) field.get(toolbar);
            imageView.setId(R.id.tool_bar_log);
            imageView.setTransitionName("sharedView");
            imageView.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, MyPage.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                imageView, "sharedView").toBundle());
              /*  ARouter.getInstance()
                        .build(ARouterPage.MYPAGE_ACTIVITY)
                        .with(ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                imageView,
                                "SEIV")
                                .toBundle())
                        .navigation(MainActivity.this);*/
               /* startActivity(new Intent(MainActivity.this, MyPage.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                                imageView,
                                "SEIV")
                                .toBundle());*/
            });


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {

            e.printStackTrace();
        }
    }

    private void initViewPagerWithFragments() {
        mViewPager = findViewById(R.id.vp_container);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        //  fragments.add(new OverViewListFragment());
        homePagerFragmentAdapter = new HomePagerFragmentAdapter(getSupportFragmentManager(), 0, fragments);
        mViewPager.setAdapter(homePagerFragmentAdapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void initDate() {
        mDrawerLayout.postDelayed(() -> {
            startCircularReveal(mDrawerLayout,
                    mDrawerLayout.getWidth() / 2, 0, 0,
                    mDrawerLayout.getHeight(),
                    FristPageAnimation);
            int maxHeigh = mDrawerLayout.getHeight() - findViewById(R.id.abl_tool).getHeight() - findViewById(R.id.nav_bot).getHeight();
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mViewPager.getLayoutParams();
            layoutParams.height = maxHeigh;
            mViewPager.setLayoutParams(layoutParams);
        }, 100);
    }

    private void itemViewStartAnimation(View view) {
        startCircularReveal(mDrawerLayout,
                (int) view.getX() + view.getWidth() / 2,
                mDrawerLayout.getHeight(),
                mDrawerLayout.getWidth() / 4,
                mDrawerLayout.getHeight(),
                MenuViewItemSmall2Big
        );
    }

    private void startBig2samllAnimation(int time, int x, int y, Animator.AnimatorListener listener) {
        Animator anim = ViewAnimationUtils.createCircularReveal(
                mDrawerLayout,
                x,
                y,
                mDrawerLayout.getHeight(), 0);
        anim.setDuration(time);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addListener(listener);
        anim.start();
    }

    private void startCircularReveal(View view,
                                     int x,
                                     int y,
                                     int sr,
                                     int redius,
                                     int time
    ) {
        Animator animator = ViewAnimationUtils.createCircularReveal(
                view,
                x,
                y,
                sr, redius);
        animator.setDuration(time);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_tools) {
            Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
            long[] patter = {2, 10, 2, 10};
            vibrator.vibrate(patter, 1);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            ARouter.getInstance().build(ARouterPage.RECYLERVIEWACTIVITY).navigation();


        }

        DrawerLayout drawer = findViewById(R.id.dwl_root);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.dwl_root);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onAnimationStart(Animator animation, boolean isReverse) {

    }

    @Override
    public void onAnimationEnd(Animator animation, boolean isReverse) {


    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        BottomNavigationMenuView itemViews = (BottomNavigationMenuView) navView.getChildAt(0);

        switch (getWhichAnimationTag()) {
            case 0: {
                itemViewStartAnimation(itemViews.getChildAt(0));
                break;
            }
            case 1: {
                itemViewStartAnimation(itemViews.getChildAt(1));
                break;

            }
            case 2: {
                itemViewStartAnimation(itemViews.getChildAt(2));
                break;

            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public void jump(View view) {

        final float centerX = mDrawerLayout.getWidth() / 2.0f;
        final float centerY = mDrawerLayout.getHeight() / 2.0f;
        //括号内参数分别为（上下文，开始角度，结束角度，x轴中心点，y轴中心点，深度，是否扭曲）
        final Rotate3dAnimation rotation = new Rotate3dAnimation(mContext, 0, 45, centerX, centerY, 1.0f, true);
        rotation.setDuration(1500);                               //设置动画时长
        rotation.setFillAfter(true);                              //保持旋转后效果
        rotation.setInterpolator(new AccelerateInterpolator());   //设置插值器

        rotation.setAnimationListener(new Animation.AnimationListener() {   //设置监听器

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });

    }

}
