package com.itkey.websocketdemo

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import okhttp3.Request
import com.itkey.websocketdemo.websocket.MyWebSocketWebSocketListener
import okhttp3.WebSocket


class MainActivity : AppCompatActivity() {
    lateinit var uri: URI
    lateinit var SB: StringBuilder
    var a = 1;
    lateinit var mOkHttpClient: OkHttpClient;
    lateinit var mWebSocketClinet: WebSocket ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initOkhttp();
        fab.setOnClickListener {
            Snackbar.make(fab, "sent", 1000).setAction("ok", null).show()
            mWebSocketClinet.send("hello")

        }
    }

    private fun initOkhttp() {
        mOkHttpClient= OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        val request = Request.Builder().url("ws://echo.websocket.org").build()
        mOkHttpClient.newWebSocket(request,MyWebSocketWebSocketListener.getInstance {
            mWebSocketClinet=MyWebSocketWebSocketListener.getWebSocketInstance()

        })
        mOkHttpClient.dispatcher().executorService().shutdown();

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {

            }

            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}

