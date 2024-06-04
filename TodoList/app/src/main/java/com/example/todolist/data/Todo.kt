package com.example.todolist.data

import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Todo(
    var title: String,
    var date: Long = Calendar.getInstance().timeInMillis,) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}