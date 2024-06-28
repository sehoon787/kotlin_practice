package com.example.forest.ui.last_schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forest.data.models.Schedules
import com.example.forest.ui.models.ScheduleModel

class LastScheduleViewModel : ViewModel() {
    private val _lastSchedulesSchedules = MutableLiveData<List<ScheduleModel>>()

    fun updateLastSchedule(newSchedules: Schedules) {
        val scheduleModels = newSchedules.schedule.map { ScheduleModel(it) }
        _lastSchedulesSchedules.value = scheduleModels
    }

    val lastSchedulesSchedules: LiveData<List<ScheduleModel>>
        get() = _lastSchedulesSchedules
}