package com.example.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.TaskDatabase
import com.example.todolist.database.TaskEntry
import com.example.todolist.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private var repository = TaskRepository(taskDao)

    val getAllTasks: LiveData<List<TaskEntry>>

    init {
        getAllTasks = repository.getAllTasks()
        repository = TaskRepository(taskDao)
    }
    fun insert(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItem(taskEntry)
        }
    }

    fun update(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(taskEntry)
        }
    }

    fun delete(taskEntry: TaskEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(taskEntry)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}