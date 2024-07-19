package com.example.todolist.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DateFormat

@SuppressLint("SetTextI18n")
@BindingAdapter("setPriorities")
fun setPriorities(view: TextView, priority: Int) {
    when (priority){
        0 -> {
            view.text = "Low"
            view.setTextColor(Color.GREEN)
        }
        1 -> {
            view.text = "Medium"
            view.setTextColor(Color.YELLOW)
        }
        else -> {
            view.text = "High"
            view.setTextColor(Color.RED)
        }
    }
}

@BindingAdapter("setTimestamp")
fun setTimestamp(view: TextView, timestamps: Long) {
    view.text = DateFormat.getInstance().format(timestamps)
}