package com.itkey.websocketdemo.websocket;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Administrator on 2020/4/13 11:00
 */
public class MyWebSocketWebSocketListener extends WebSocketListener {
    private static WebSocket mWebSocketClinet = null;
    private static MyWebSocketWebSocketListener mMyWebSocketClient = null;
    private static ConnectCallBack connectCallBack;

    private void BindWebSocket(WebSocket mWebSocket) {
        mWebSocketClinet = mWebSocket;

    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        BindWebSocket(webSocket);
        connectCallBack.onConnectSuccsee();
        Log.e("onOpen ~~", response.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        connectCallBack.onMessage(text);
        Log.e("onMessage ~~", text);

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        connectCallBack.onBase64Message(bytes);
        Log.e("onMessage bytes ~~:", bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        connectCallBack.onClosing(webSocket, code, reason);
        Log.e("onClosing ~~", reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        connectCallBack.onClose(webSocket, code, reason);
        webSocket = null;
        Log.e("onClosed ~~", reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        connectCallBack.onFailure(webSocket, t, response);
        Log.e("onFailure ~~", t.toString());

    }

    public static MyWebSocketWebSocketListener getInstance(ConnectCallBack onSuccess) {
        if (mMyWebSocketClient == null) {
            connectCallBack = onSuccess;
            mMyWebSocketClient = new MyWebSocketWebSocketListener();

            return mMyWebSocketClient;
        } else {
            return mMyWebSocketClient;
        }
    }

    public static WebSocket getWebSocketInstance() throws NullPointerException {
        if (mWebSocketClinet != null) {
            return mWebSocketClinet;
        } else {
            throw new NullPointerException();
        }


    }

    public interface ConnectCallBack {
        void onConnectSuccsee();

        void onMessage(String text);

        void onBase64Message(ByteString BS);

        void onClose(WebSocket webSocket, int code, String reason);

        void onClosing(WebSocket webSocket, int code, String reason);

        void onFailure(WebSocket webSocket, Throwable t, Response response);
    }
}
