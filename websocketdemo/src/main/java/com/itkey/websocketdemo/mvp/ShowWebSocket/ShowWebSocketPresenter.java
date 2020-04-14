package com.itkey.websocketdemo.mvp.ShowWebSocket;

/**
 * Created by Administrator on 2020/4/13 15:31
 */
public interface ShowWebSocketPresenter {
    void onConnect();

    void onMessage();

    String sent(String string);
}
