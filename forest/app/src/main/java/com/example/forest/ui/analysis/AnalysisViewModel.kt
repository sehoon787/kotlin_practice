package com.example.forest.ui.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnalysisViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Data Manage Fragment"
    }
    val text: LiveData<String> = _text
}