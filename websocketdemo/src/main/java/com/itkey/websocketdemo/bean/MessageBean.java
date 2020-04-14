package com.itkey.websocketdemo.bean;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2020/4/14 10:13
 */
public class MessageBean implements MultiItemEntity {
    @Override
    public int getItemType() {
        return type;
    }

    public static final int TYPE_ME=1;
    public static final int TYPE_OTHER=2;
    public static final int TYPE_SYSYTEM=3;

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getUser() {
        return user;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MessageBean(String message, String time, String user, int type) {
        this.message = message;
        this.time = time;
        this.user = user;
        this.type = type;
    }

    private String message;


    private String time;
    private String user;
    private int type;

}
