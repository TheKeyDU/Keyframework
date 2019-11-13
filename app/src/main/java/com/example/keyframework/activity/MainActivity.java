package com.example.keyframework.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.keyframework.Constants.ARouterPage;
import com.example.keyframework.R;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.maosong.component.Base.BaseActivity;
import com.maosong.tools.AppLifeCircleUtil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.Animator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;

@Route(path = ARouterPage.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    Animator animator = null;
    androidx.drawerlayout.widget.DrawerLayout mDrawerLayout = null;
    BottomNavigationView navView = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            BottomNavigationMenuView itemViews = (BottomNavigationMenuView) navView.getChildAt(0);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    itemViewStartAnimation(itemViews.getChildAt(0));
                    return true;
                case R.id.navigation_dashboard:
                    itemViewStartAnimation(itemViews.getChildAt(1));
                    return true;
                case R.id.navigation_notifications:
                    itemViewStartAnimation(itemViews.getChildAt(2));
                    return true;
            }
            return false;
        }
    };


    @Override
    public void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
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
    }

    @Override
    public void initDate() {
        mDrawerLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startCircularReveal(mDrawerLayout, mDrawerLayout.getWidth() / 2, 0, mDrawerLayout.getHeight(), 700);
            }
        }, 100);
    }

    private void itemViewStartAnimation(View view) {
        startCircularReveal(mDrawerLayout, (int) view.getX() + view.getWidth() / 2, mDrawerLayout.getHeight(), mDrawerLayout.getHeight(), 200);
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
                mDrawerLayout.setVisibility(View.VISIBLE);

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
            onBackPressed();
        }
    }
}
