package com.example.keyframework.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.keyframework.Constants.ARouterPage;
import com.example.keyframework.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maosong.component.Base.BaseActivity;
import androidx.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;
@Route(path = ARouterPage.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    @Override
    public void initView() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public int getContentViewRes() {
        return R.layout.activity_mainbot;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
