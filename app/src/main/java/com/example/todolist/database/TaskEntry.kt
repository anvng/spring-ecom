package com.example.todolist.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class TaskEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val priority: String,
    val notes: String,
    var timestamp: Long
) : Parcelable
