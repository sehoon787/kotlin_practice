package com.example.forest.data.models

import java.io.Serializable

data class LoginResponse(
    val token: String,
    val code: String,
    val name: String,
    val department: String,
    val position: String,
    val phoneNumber: String,
    val email: String,
    val imageUrl: String,
) : Serializable{
    fun toUser(): User {
        return User(
            code = this.code,
            name = this.name,
            department = this.department,
            position = this.position,
            phoneNumber = this.phoneNumber,
            email = this.email,
            imageUrl = this.imageUrl,
        )
    }
}

data class User(
    val code: String,
    val name: String,
    val department: String,
    val position: String,
    val phoneNumber: String,
    val email: String,
    val imageUrl: String,
) : Serializable


val loginResponseSample = LoginResponse(
    token = "dwavyuhabijk3",
    code = "100001",
    name = "홍길동",
    department = "기술연구소",
    position = "과장",
    phoneNumber = "010-1234-5678",
    email = "gildong364@naver.com",
    imageUrl = "https://tistory1.daumcdn.net/tistory/5057158/attach/31fd96ec0edf48f78c7be5f19bc09954"
)

val userSample = User(
    code = "100001",
    name = "홍길동",
    department = "기술연구소",
    position = "과장",
    phoneNumber = "010-1234-5678",
    email = "gildong364@naver.com",
    imageUrl = "https://tistory1.daumcdn.net/tistory/5057158/attach/31fd96ec0edf48f78c7be5f19bc09954"
)
