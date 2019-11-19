package com.example.keyframework.net;


import com.example.keyframework.bean.HomeListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIservers {
    @GET("/json/newslist/news")
    Observable<HomeListBean> getHomeList( );
}
