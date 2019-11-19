package com.example.keyframework.module;

import androidx.databinding.ObservableList;

import com.example.keyframework.bean.HomeListBean;
import com.example.keyframework.bean.UserBean;
import com.example.keyframework.net.BuildAPI;
import com.example.keyframework.net.BuildApiServers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetModules {
    public Observable<HomeListBean> getHomeList( )
    {
        return BuildApiServers.buildApiServers()
                .getHomeList( )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
