package com.itkey.websocketdemo.mvp.ShowWebSocket;

/**
 * Created by Administrator on 2020/4/13 15:31
 */
public interface ShowWebSocketView {
    void onConnectSuccseeView();

    void onMessageView(String text);

    void onCloseView();

    void onFailureView(Throwable t);

    void onSentError(String str);

    void onSentSuccess(String str);
}
