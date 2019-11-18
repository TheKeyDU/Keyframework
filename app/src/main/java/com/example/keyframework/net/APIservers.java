package com.example.keyframework.net;


import com.example.keyframework.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIservers {
    @GET("/json/newslist/news")
    Observable<List<UserBean>> getHomeList(@Query("item")int num);
}
