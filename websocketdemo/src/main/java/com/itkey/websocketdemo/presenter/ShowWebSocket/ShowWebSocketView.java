package com.itkey.websocketdemo.presenter.ShowWebSocket;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

/**
 * Created by Administrator on 2020/4/13 15:31
 */
public interface ShowWebSocketView {
    void onConnectSuccseeView();

    void onMessageView(String text);

    void onCloseView();

    void onFailureView(Throwable t);
}
