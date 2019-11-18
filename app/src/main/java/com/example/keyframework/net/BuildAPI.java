package com.example.keyframework.net;

import com.example.keyframework.constants.EnvironmentConstants;
import com.maosong.component.net.HttpLogInterceptor;
import com.maosong.tools.ToolsApp;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class BuildAPI {
    private static Retrofit headerRetrofit;

    public static <T> T buildApiServers(Class<T> service) {
        if (headerRetrofit == null) {
            headerRetrofit = new Retrofit.Builder()
                    .baseUrl(EnvironmentConstants.ITHOME_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getDefaultClient())
                    .build();
        }
        return headerRetrofit.create(service);
    }

    private static OkHttpClient getDefaultClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                /* .addHeader("Content-Type", "application/x-www-form-urlencoded")
                                 .addHeader("Accept", "application/json;charset=UTF-8")
                                 .addHeader("device", "android")*/
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new HttpLogInterceptor("-httpLOG-", true))
                .addInterceptor(new ChuckInterceptor(ToolsApp.getAppContext()))
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

    }
}
