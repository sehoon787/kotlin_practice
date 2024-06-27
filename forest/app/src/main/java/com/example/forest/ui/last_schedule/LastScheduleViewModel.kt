package com.example.forest.ui.last_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LastScheduleViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Checklist Fragment"
    }
    val text: LiveData<String> = _text
}