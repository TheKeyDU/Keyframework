package com.itkey.websocketdemo

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.itkey.websocketdemo.presenter.ShowWebSocket.ShowWebSocketPresenterImpl
import com.itkey.websocketdemo.presenter.ShowWebSocket.ShowWebSocketView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity(),ShowWebSocketView{
    override fun onConnectSuccseeView() {
    }

    @SuppressLint("WrongConstant")
    override fun onMessageView(text: String?) {
        Toast.makeText(this,text,1000).show();
    }

    override fun onCloseView() {
    }

    @SuppressLint("WrongConstant")
    override fun onFailureView(t: Throwable?) {
        Toast.makeText(this,"error! $t",1000).show();
    }


    lateinit var uri: URI
    lateinit var SB: StringBuilder
    var a = 1;
    lateinit var showWebSocketPresenterImpl: ShowWebSocketPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showWebSocketPresenterImpl=ShowWebSocketPresenterImpl(this,this)
        showWebSocketPresenterImpl?.onConnect()

        fab.setOnClickListener {
            Snackbar.make(fab, "sent", 1000).setAction("ok", null).show()
            showWebSocketPresenterImpl?.sent("hhhhh")

        }
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

