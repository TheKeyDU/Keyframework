package com.itkey.wangyuelauncher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  ActionBar actionBar=getSupportActionBar();
        if (actionBar == null) {
            actionBar.hide();
        }*/


        ImageButton imageButton=findViewById(R.id.btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getPackageManager().getLaunchIntentForPackage("com.huage.wangyuedriver");
                startActivity(i);
            }
        });
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i=getPackageManager().getLaunchIntentForPackage("com.microsoft.launcher");
                startActivity(i);
                return false;
            }
        });
    }


    @Override
    protected void onResume() {
       super.onResume();
    }
}
