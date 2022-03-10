package com.example.notificationfilter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notificationfilter.databinding.NotificationItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NotificationItem(
    val app: String,
    val channel: String,
    val title: String,
    val content: String,
    val intent: String,
    val time: LocalDateTime,
    val icon: Drawable? = null
)

class NotificationItemAdapter(private var notificationItemList: List<NotificationItem>) :
    RecyclerView.Adapter<NotificationItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NotificationItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.run {
            notificationItemList[position].run {
                appnameView.text = app
                textViewDatetime.text =
                    time.format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd"))
                titleView.text = title
                contentView.text = "$channel: $content"
                iconView.setImageDrawable(icon)
            }
        }
    }

    override fun getItemCount() = notificationItemList.count()
}