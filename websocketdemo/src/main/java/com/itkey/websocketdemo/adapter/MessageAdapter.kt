package com.itkey.websocketdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itkey.websocketdemo.R
import com.itkey.websocketdemo.bean.MessageBean
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2020/4/14 10:17
 */
class MessageAdapter constructor(var list: ArrayList<MessageBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return list.get(position).type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            MessageBean.TYPE_ME -> {
                var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
                return sentMessageViewHolder(view)
            }
            MessageBean.TYPE_OTHER -> {
                var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_receive, parent, false);
                return ReceiveMessageViewHoler(view)
            }

            else -> {
                var view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false);
                return ReceiveMessageViewHoler(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (list[position].type) {

            MessageBean.TYPE_ME -> {
                holder as sentMessageViewHolder
                holder.tv.setText(list[position].message)
            }
            MessageBean.TYPE_OTHER -> {
                holder as ReceiveMessageViewHoler
                holder.tv.setText(list[position].message)

            }

            else -> {
            }
        }
    }

    internal inner class sentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView

        init {
            tv = itemView.findViewById<TextView>(R.id.tv_sent)
        }
    }

    internal inner class ReceiveMessageViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView

        init {
            tv = itemView.findViewById<TextView>(R.id.tv_rec)
        }
    }
}