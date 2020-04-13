package com.itkey.websocketdemo.presenter.ShowWebSocket

import android.app.Activity
import com.itkey.websocketdemo.websocket.MyWebSocketWebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2020/4/13 16:14
 */
class ShowWebSocketPresenterImpl(val mView: ShowWebSocketView, val Activity: Activity) : ShowWebSocketPresenter {

    lateinit var uiThread: Thread
    lateinit var mWebSocketClinet: WebSocket;
    lateinit var mOkHttpClient: OkHttpClient;
    private fun initOkhttp() {

        mOkHttpClient = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        val request = Request.Builder().url("ws://echo.websocket.org").build()
        mOkHttpClient.newWebSocket(request, MyWebSocketWebSocketListener.getInstance(object : MyWebSocketWebSocketListener.ConnectCallBack {
            override fun onConnectSuccsee() {
                mWebSocketClinet = MyWebSocketWebSocketListener.getWebSocketInstance()
                Activity.runOnUiThread { mView.onConnectSuccseeView() }

            }

            override fun onMessage(text: String?) {
                Activity.runOnUiThread { mView.onMessageView(text) }
            }

            override fun onBase64Message(BS: ByteString?) {
            }

            override fun onClose(webSocket: WebSocket?, code: Int, reason: String?) {
                Activity.runOnUiThread { mView.onCloseView() }

            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            }

            override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {

                Activity.runOnUiThread { mView.onFailureView(t) }

            }
        }))
        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    override fun onMessage() {

    }

    override fun onConnect() {
        initOkhttp()
    }


    override fun sent(str: String) {
        mWebSocketClinet.send(str)
    }
}