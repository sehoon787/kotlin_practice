package com.example.todolist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.data.Todo

class TodoDiffUtilCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }
}