package com.example.forest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _token = MutableLiveData<String>().apply {value = "" }

    fun updateToken(newToken: String) {
        _token.value = newToken
    }

    val token: LiveData<String> = _token
}
