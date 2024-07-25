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

    @Query("SELECT * FROM task_table order by priority asc")
    fun getAllPriorityTasks(): LiveData<List<TaskEntry>>

    @Query("SELECT * FROM task_table where titles like :searchQuery order by timestamps desc")
    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntry>>



}