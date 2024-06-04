package com.example.todolist

import android.app.Application
import android.icu.util.Calendar
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.todolist.data.Todo
import com.example.todolist.data.TodoDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        TodoDatabase::class.java, "todo"
    ).build()

    private val _items = MutableStateFlow<List<Todo>>(emptyList())
    val items: StateFlow<List<Todo>> = _items

    init {
        viewModelScope.launch {
            db.todoDao().getAll().collect{ todos ->
                _items.value = todos
            }
        }
    }

    fun addTodo(text: String){
        viewModelScope.launch {
            db.todoDao().insert(Todo(title = text))
        }
    }

    fun updateTodo(id: Long, text: String){
        _items.value
            .find { todo -> todo.id == id }
            ?.let { todo ->
                todo.apply {
                    title = text
                    date = Calendar.getInstance().timeInMillis
                }
                viewModelScope.launch {
                    db.todoDao().update(todo)
                }
            }
    }

    fun deleteTodo(id: Long){
        _items.value
            .find { todo -> todo.id == id }
            ?.let { todo ->
                viewModelScope.launch {
                    db.todoDao().update(todo)
                }
            }
    }
}