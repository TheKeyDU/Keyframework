package com.maosong.component.view;

import android.content.Context;
import android.content.DialogInterface;
import com.maosong.component.net.TokenException;

/**
 * Created by tianweiping on 2017/12/11.
 */

public interface BaseView {


    void showLoading();

    void showLoading(String message);

    void dismissLoading();


    void showTipMessage(String msg);


    String getNetKey();




}
