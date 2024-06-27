package com.example.forest.ui.models

import java.text.SimpleDateFormat
import com.example.forest.data.models.Schedule
import com.example.forest.data.models.User
import java.util.Locale

data class ScheduleModel(
    private val schedule: Schedule,
){
    val name: String
        get() = schedule.name

    val locationName: String
        get() = schedule.location.name

    val startTime: String
        get() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(schedule.startDate)

    val endTime: String
        get() = SimpleDateFormat("HH:mm", Locale.getDefault()).format(schedule.endDate)

    val warning: Int
        get() {
            return when (schedule.warning) {
                "WARN" -> 2
                "Alert" -> 1
                else -> 0
            }
        }

    val companionImages: ArrayList<String>?
        get() {
            return if (!schedule.companion.isNullOrEmpty()) {
                ArrayList(schedule.companion.map { user: User -> user.imageUrl })
            } else {
                null
            }
        }
}