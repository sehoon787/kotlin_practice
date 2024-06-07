package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        TodoDatabase::class.java, "todo"
    ).build()

    private val _items = MutableStateFlow<List<Todo>>(emptyList())
    val items: StateFlow<List<Todo>> = _items

    var selectedTodo: Todo? = null

    init {
        viewModelScope.launch {
            db.todoDao().getAll().collect{ todos ->
                _items.value = todos
            }
        }
    }

    fun addTodo(text: String, date: Long){
        viewModelScope.launch {
            db.todoDao().insert(Todo(title = text, date = date))
        }
    }

    fun updateTodo(text: String, date: Long) {
        selectedTodo?.let { todo ->
            todo.apply {
                this.title = text
                this.date = date
            }

            viewModelScope.launch {
                db.todoDao().update(todo)
            }
            selectedTodo = null
        }
    }

    fun deleteTodo(id: Long){
        _items.value
            .find { todo -> todo.id == id }
            ?.let { todo ->
                viewModelScope.launch {
                    db.todoDao().delete(todo)
                }
            }
    }
}