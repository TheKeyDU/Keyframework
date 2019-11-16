package com.example.keyframework.activitys;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.constants.ARouterPage;
import com.example.keyframework.R;
import com.example.keyframework.adapter.HomePagerFragmentAdapter;
import com.example.keyframework.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.maosong.component.Base.BaseActivity;
import com.maosong.tools.AppLifeCircleUtil;
import com.maosong.tools.ToastUtils;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.animation.Animator;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterPage.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        Animator.AnimatorListener {
    private int FristPageAnimation = 400;
    private int MenuViewItemBig2Small = 400;
    private int MenuViewItemSmall2Big = 200;
    private Context mContext;

    Toolbar toolbar = null;
    HomePagerFragmentAdapter homePagerFragmentAdapter = null;
    androidx.drawerlayout.widget.DrawerLayout mDrawerLayout = null;
    List<Fragment> fragments = null;
    BottomNavigationView navView = null;
    ViewPager mViewPager = null;
    private int whichAnimation = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        }
    };

    private synchronized void setWhichAnimationTag(int tag) {
        whichAnimation = tag;
    }

    private synchronized int getWhichAnimationTag() {
        return whichAnimation;
    }

    @Override
    public void initView() {
        initViewPagerWithFragments();
        mContext=MainActivity.this;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.dwl_root);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navView = findViewById(R.id.nav_bot);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        AppLifeCircleUtil.getInstance().finishActivity(AppLifeCircleUtil.activityStack.get(0));
        initListener();
    }

    private void initListener() {
        try {
            Field field = toolbar.getClass().getDeclaredField("mLogoView");
            field.setAccessible(true);
            ImageView imageView = (ImageView) field.get(toolbar);
            imageView.setTransitionName("SEIV");
            imageView.setId(R.id.tool_bar_log);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair<View, String>[] pairs = new Pair[]{
                            Pair.create(mDrawerLayout.findViewById(R.id.tool_bar_log), "SEIV")};
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(AppLifeCircleUtil.activityStack.get(0), pairs);
                    ToastUtils.showLongToast("2");
                    ARouter.getInstance().build(ARouterPage.MYPAGE_ACTIVITY).withOptionsCompat(options)
                            .navigation(MainActivity.this);
                }
            });


        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            ToastUtils.showLongToast("1");

            e.printStackTrace();
        }
    }

    private void initViewPagerWithFragments() {
        mViewPager = findViewById(R.id.vp_container);
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());
        fragments.add(new HomeFragment());

        homePagerFragmentAdapter = new HomePagerFragmentAdapter(getSupportFragmentManager(), 0, fragments);
        mViewPager.setAdapter(homePagerFragmentAdapter);

    }

    @Override
    public void initDate() {
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startCircularReveal(mDrawerLayout,
                        mDrawerLayout.getWidth() / 2, 0, 0,
                        mDrawerLayout.getHeight(),
                        FristPageAnimation);
            }
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

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
}