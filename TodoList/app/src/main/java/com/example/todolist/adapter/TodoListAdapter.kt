package com.example.todolist.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTodoBinding

class TodoListAdapter(
    private val onClick: (Todo) -> Unit,
) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoDiffUtilCallback()){

    private lateinit var binding: ItemTodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TodoViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.setOnclickListener(getItem(position))
    }

    class TodoViewHolder(
        private val binding: ItemTodoBinding,
        private val onClick: (Todo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root){

        fun bind(todo: Todo){
            binding.text1.text = todo.title
            binding.text2.text = DateFormat.format("yyyy/MM/dd", todo.date)
        }

        fun setOnclickListener(todo: Todo){
            binding.root.setOnClickListener {
                onClick(todo)
            }
        }
    }
}