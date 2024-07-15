package com.example.todolist.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class TaskEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val task: String,
    var priority: Int,
    var note: String,
    var timestamp: Long
) : Parcelable
