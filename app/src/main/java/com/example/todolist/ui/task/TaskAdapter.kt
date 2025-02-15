package com.example.todolist.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.TaskEntry
import com.example.todolist.databinding.GidLayoutBinding

class TaskAdapter(private val clickListener: TaskClickListener) : ListAdapter<TaskEntry, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {

    companion object TaskDiffCallback :DiffUtil.ItemCallback<TaskEntry>(){
        override fun areItemsTheSame(oldItem:TaskEntry, newItem:TaskEntry) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem:TaskEntry, newItem:TaskEntry) = oldItem == newItem
    }

    class TaskViewHolder(val binding: GidLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskEntry: TaskEntry, clickListener: TaskClickListener){
            binding.taskEntry = taskEntry
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(GidLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, clickListener)
    }

}

class TaskClickListener(val clickListener: (taskEntry: TaskEntry) -> Unit) {
    fun onClick(taskEntry: TaskEntry) = clickListener(taskEntry)
}