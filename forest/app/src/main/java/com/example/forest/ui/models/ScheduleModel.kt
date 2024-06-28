package com.example.forest.ui.models

import java.text.SimpleDateFormat
import com.example.forest.data.models.Schedule
import com.example.forest.data.models.User
import java.util.Locale

/**
 * Used for Recycler View and Adapter */
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

    val warning: Int?
        get() {
            return when (schedule.warning) {
                "WARN" -> 2
                "ALERT" -> 1
                "SAFE" -> 0
                else -> null
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