package com.itkey.websocketdemo.mvp.ShowWebSocket

import android.app.Activity
import android.net.Uri
import android.util.Log
import com.itkey.websocketdemo.websocket.MyWebSocketWebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString
import retrofit2.http.Url
import java.lang.Exception
import java.net.URL
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2020/4/13 16:14
 */
class ShowWebSocketPresenterImpl(val uri: String, val mView: ShowWebSocketView, val Activity: Activity) : ShowWebSocketPresenter {

    lateinit var mWebSocketClinet: WebSocket;
    lateinit var mOkHttpClient: OkHttpClient;
    var request = Request.Builder().url(uri).build();
    var isConnectSuccess = false
    private fun initOkhttp() {

        mOkHttpClient = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        mOkHttpClient.newWebSocket(request, MyWebSocketWebSocketListener.getInstance(object : MyWebSocketWebSocketListener.ConnectCallBack {
            override fun onConnectSuccsee() {
                mWebSocketClinet = MyWebSocketWebSocketListener.getWebSocketInstance()
                isConnectSuccess = true;

                Activity.runOnUiThread { mView.onConnectSuccseeView() }
            }

            override fun onMessage(text: String?) {
                Activity.runOnUiThread { mView.onMessageView(text) }
            }

            override fun onBase64Message(BS: ByteString?) {
            }

            override fun onClose(webSocket: WebSocket?, code: Int, reason: String?) {
                isConnectSuccess = false

                Activity.runOnUiThread { mView.onCloseView() }

            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                isConnectSuccess = false

            }

            override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
                isConnectSuccess = false
                Activity.runOnUiThread { mView.onFailureView(t) }

            }
        }))
        mOkHttpClient.dispatcher().executorService().shutdown();
    }
    fun reSetUrL(str:String)
    {

    }

    override fun onMessage() {

    }

    override fun onConnect() {
        initOkhttp()
    }

    fun closeConnect() {


        if (mWebSocketClinet==null && isConnectSuccess) {
            mWebSocketClinet.close(1000, "byebye")
            mView.onCloseView()
            isConnectSuccess = false
        } else {
            mView.onConnecting("还在连接中")

        }

    }

    override fun sent(str: String): String? {
        try {
            if (isConnectSuccess) {
                mWebSocketClinet.send(str)
                mView.onSentSuccess(str)

                Log.e("sent~~", str)
                return "ok"
            } else {
                mView.onConnecting("还在连接中")
            }
        } catch (e: Exception) {
            Log.e("sent error~~", e.toString())
            mView.onSentError(e.toString())
            return e.toString()
        }
        return null
    }
}