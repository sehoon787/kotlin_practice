package com.example.forest.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forest.data.models.User

class ProfileViewModel : ViewModel() {
    // 내부에서 설정하는 자료형은 Mutable로 변경가능하도록 설정한다.
    private val _user = MutableLiveData<User>()
    // MutableLiveData - 수정 가능
    // LiveData - 값 수정 불가능

    fun updateUser(newUser: User) {
        _user.value = newUser
    }

    val user: LiveData<User>
        get() = _user
}