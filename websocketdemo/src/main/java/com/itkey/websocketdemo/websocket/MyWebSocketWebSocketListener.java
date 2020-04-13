package com.itkey.websocketdemo.websocket;

import android.net.Uri;
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
    private static OnConnectSuccess onConnectSuccess;
    private void BindWebSocket(WebSocket mWebSocket) {

        if (mWebSocketClinet == null) {
            mWebSocketClinet = mWebSocket;
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        BindWebSocket(webSocket);
        onConnectSuccess.onConnectSuccsee();
        webSocket.send("onOpen");
        Log.e("onOpen", response.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.e("onMessage", text);

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        Log.e("onMessage bytes:", bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        Log.e("onClosing ", reason);

    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.e("webSocket ", reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.e("onFailure ", t.toString());

    }

    public static MyWebSocketWebSocketListener getInstance(OnConnectSuccess onSuccess) {
        if (mMyWebSocketClient == null) {
            onConnectSuccess=onSuccess;
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

    public interface OnConnectSuccess {
        void onConnectSuccsee();
    }
}
