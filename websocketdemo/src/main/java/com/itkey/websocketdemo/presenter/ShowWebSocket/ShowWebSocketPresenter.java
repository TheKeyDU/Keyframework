package com.itkey.websocketdemo.presenter.ShowWebSocket;

/**
 * Created by Administrator on 2020/4/13 15:31
 */
public interface ShowWebSocketPresenter {
    void onConnect();

    void onMessage();

    void sent(String string);
}
