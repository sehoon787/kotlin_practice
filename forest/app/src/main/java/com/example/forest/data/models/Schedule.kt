package com.example.forest.data.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Schedule(
    val code: String,
    val name: String,
    val location: Location,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val companion: ArrayList<User>? = null,
    val category: Category? = null,
    val file: ArrayList<File>? = null,
    val note: String? = null,
    val warning: String? = null,
) : Serializable

data class Location(
    val code: String,
    val name: String,
) : Serializable

data class Category(
    val code: String,
    val name: String,
    val color: String,
) : Serializable

data class File(
    val code: String,
    val name: String,
    val fileType: String,
    val uri: String,
) : Serializable

@RequiresApi(Build.VERSION_CODES.O)
val scheduleSample1 = Schedule(
    code = "100001",
    name = "카페 방문하기",
    location = Location(code = "0001", name = "잠실 할리스 커피"),
    startDate = LocalDateTime.parse("2024-04-26 09:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    endDate = LocalDateTime.parse("2024-04-26 11:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    companion = arrayListOf(userSample),
    warning = "WARN"
)

@RequiresApi(Build.VERSION_CODES.O)
val scheduleSample2 = Schedule(
    code = "100002",
    name = "거래처 방문하기",
    location = Location(code = "0001", name = "생각공장 데시앙플렉스"),
    startDate = LocalDateTime.parse("2024-04-26 12:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    endDate = LocalDateTime.parse("2024-04-26 14:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    companion = arrayListOf(userSample),
    warning = "SAFE"
)