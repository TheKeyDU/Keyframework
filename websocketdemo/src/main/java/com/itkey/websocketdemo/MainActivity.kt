package com.itkey.websocketdemo

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itkey.websocketdemo.adapter.MessageAdapter
import com.itkey.websocketdemo.bean.MessageBean
import com.itkey.websocketdemo.mvp.ShowWebSocket.ShowWebSocketPresenterImpl
import com.itkey.websocketdemo.mvp.ShowWebSocket.ShowWebSocketView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), ShowWebSocketView {

    lateinit var MessageAdapter: MessageAdapter
    lateinit var date: ArrayList<MessageBean>
      var WebSocketUrl: String="ws://www.panjianghong.cn:8080/websocket"
    override fun onSentSuccess(str: String?) {
        MessageAdapter.list.add(object : MessageBean(str, null, null, MessageBean.TYPE_ME) {})
        MessageAdapter.notifyDataSetChanged()

    }

    override fun onConnecting(string: String?) {
        Snackbar.make(fab, "onConnecting", 1000).setAction("ok", null).show()

    }

    @SuppressLint("WrongConstant")
    override fun onSentError(str: String?) {
        Toast.makeText(this, "发送失败", 1000).show()
        MessageAdapter.list.add(object : MessageBean(str, null, null, MessageBean.TYPE_SYSYTEM) {})
        MessageAdapter.notifyDataSetChanged()

    }

    @SuppressLint("WrongConstant")
    override fun onConnectSuccseeView() {
        MessageAdapter.list.add(object : MessageBean("连接成功", null, null, MessageBean.TYPE_SYSYTEM) {})
        MessageAdapter.notifyDataSetChanged()
        Toast.makeText(this, "连接成功", 1000).show()

    }

    override fun onMessageView(text: String?) {
        Snackbar.make(fab, text.toString(), 1000).setAction("ok", null).show()
        MessageAdapter.list.add(object : MessageBean(text, null, null, MessageBean.TYPE_OTHER) {})
        MessageAdapter.notifyDataSetChanged()
        rec_messages.scrollToPosition(MessageAdapter.list.size - 1)


    }

    @SuppressLint("WrongConstant")
    override fun onCloseView() {
        MessageAdapter.list.add(object : MessageBean("连接关闭", null, null, MessageBean.TYPE_SYSYTEM) {})
        MessageAdapter.notifyDataSetChanged()
        Toast.makeText(this, "断开", 1000).show()

    }

    @SuppressLint("WrongConstant")
    override fun onFailureView(t: Throwable?) {
        MessageAdapter.list.add(object : MessageBean("onFailureView", null, null, MessageBean.TYPE_SYSYTEM) {})
        Toast.makeText(this, "error! $t", 1000).show();
    }


    lateinit var showWebSocketPresenterImpl: ShowWebSocketPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initMessageRecylerView()
        initWebSocketConnect(WebSocketUrl)
    }

    private fun initWebSocketConnect(mUrl: String) {
        showWebSocketPresenterImpl = ShowWebSocketPresenterImpl(mUrl, this, this)
        showWebSocketPresenterImpl?.onConnect()
        initLisenter()
    }

    private fun reSetUrl(mUrl: String) {
        showWebSocketPresenterImpl.closeConnect()
        initWebSocketConnect(mUrl)
    }

    private fun initLisenter() {
        fab.setOnClickListener {
            var mes = et_message.text.toString()
            if (!mes.equals("")) {
                showWebSocketPresenterImpl?.sent(mes)
                //  Snackbar.make(rec_messages, mes, 1000).setAction("ok", null).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        showWebSocketPresenterImpl.closeConnect()
    }

    private fun initMessageRecylerView() {
        date = ArrayList<MessageBean>()
        MessageAdapter = MessageAdapter(date)
        val ll = LinearLayoutManager(this)
        ll.orientation = RecyclerView.VERTICAL
        rec_messages.layoutManager = ll
        rec_messages.adapter = MessageAdapter

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                var et = EditText(this)
                et.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("修改url").setView(et).setPositiveButton("确定") { dialog, which ->
                    val Url = et.text.toString()
                    if (Url != null) {
                        reSetUrl(Url)
                        dialog.dismiss()
                        MessageAdapter.moveAllDate()
                        MessageAdapter.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this, "不能空", 1000).show()
                    }
                }
                builder.setNegativeButton("取消") { dilog, which ->
                    dilog.dismiss()
                }
                builder.show()


            }
            R.id.action_close_websocket -> {
                if (showWebSocketPresenterImpl != null) {
                    showWebSocketPresenterImpl.closeConnect()

                }
            }
            R.id.delete_all -> {

                MessageAdapter.moveAllDate()
                MessageAdapter.notifyDataSetChanged()
                Toast.makeText(this, "不能空", 1000).show()

            }
            R.id.re_connect->{
                reSetUrl(WebSocketUrl)
            }

        }
        return true
    }
}

