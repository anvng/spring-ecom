package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: TaskEntry)

    @Delete
    suspend fun delete(task: TaskEntry)

    @Update
    suspend fun update(task: TaskEntry)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<TaskEntry>>

}