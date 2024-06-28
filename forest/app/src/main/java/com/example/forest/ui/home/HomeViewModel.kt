package com.example.forest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forest.data.models.Schedules
import com.example.forest.ui.models.ScheduleModel

class HomeViewModel : ViewModel() {
    private val _homeSchedules = MutableLiveData<List<ScheduleModel>>()
    private val _homeReminderSchedules = MutableLiveData<List<ScheduleModel>>()

    fun updateSchedule(newSchedules: Schedules) {
        val scheduleModels = newSchedules.schedule.map { ScheduleModel(it) }
        _homeSchedules.value = scheduleModels
    }

    fun updateReminderSchedule(newSchedules: Schedules) {
        val scheduleModels = newSchedules.schedule.map { ScheduleModel(it) }
        _homeReminderSchedules.value = scheduleModels
    }

    val homeSchedules: LiveData<List<ScheduleModel>>
        get() = _homeSchedules

    val homeReminderSchedules: LiveData<List<ScheduleModel>>
        get() = _homeReminderSchedules
}