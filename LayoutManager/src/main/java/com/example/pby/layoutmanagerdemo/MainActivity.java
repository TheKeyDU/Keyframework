package com.example.pby.layoutmanagerdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;

    List<Bean> mList = new ArrayList<>();
    RecyclerAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recyclerView);
        initData();
        mLayoutManager = new CustomLayoutManger(1.5f, 0.85f);
        mAdapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add(new Bean("content = " + i, Color.parseColor(ColorUtils.generateRandomColor())));
        }
    }
}
