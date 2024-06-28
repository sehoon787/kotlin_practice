package com.example.forest.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.forest.R
import com.example.forest.data.models.schedulesSample1
import com.example.forest.data.models.schedulesSample2
import com.example.forest.databinding.FragmentHomeBinding
import com.example.forest.ui.adapter.CalendarAdapter
import com.example.forest.ui.adapter.ReminderAdapter
import com.example.forest.ui.adapter.ScheduleAdapter
import com.example.forest.ui.models.CalendarDateModel
import com.example.forest.ui.models.ScheduleModel
import com.example.forest.ui.utils.HorizontalItemDecoration
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    /**
     * Adapter Calendar
     */
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    private lateinit var calendarAdapter: CalendarAdapter
    private val calendarList2 = ArrayList<CalendarDateModel>()

    /**
     * Adapter Schedule
     */
    private lateinit var scheduleAdapter: ScheduleAdapter

    /**
     * Adapter Reminder
     */
    private lateinit var reminderAdapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.updateSchedule(schedulesSample1)
        homeViewModel.updateReminderSchedule(schedulesSample2)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpCalendarAdapter()
        setUpCalendarClickListener()
        setUpCalendar()

        homeViewModel.homeSchedules.observe(viewLifecycleOwner) {
            setUpScheduleAdapter(it)
            setUpScheduleClickListener()
        }

        homeViewModel.homeReminderSchedules.observe(viewLifecycleOwner) {
            setUpReminderAdapter(it)
            setUpReminderClickListener()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Set up click listener
     */
    private fun setUpCalendarClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            setUpCalendar()
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)
            if (cal == currentDate)
                setUpCalendar()
            else
                setUpCalendar()
        }
    }

    private fun setUpScheduleClickListener() {
        scheduleAdapter.setItemClickListener(object: ScheduleAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "ScheduleAdapter: hello! I'm callback", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpReminderClickListener() {
        reminderAdapter.setItemClickListener(object: ReminderAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "ScheduleAdapter: hello! I'm callback", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Setting up adapter for recyclerview
     */
    private fun setUpCalendarAdapter() {
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
        binding.rvCalendar.addItemDecoration(HorizontalItemDecoration(spacingInPixels))
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCalendar)
        calendarAdapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->
            calendarList2.forEachIndexed { index, calendarModel ->
                calendarModel.isSelected = index == position
            }
            calendarAdapter.setData(calendarList2)
        }
        binding.rvCalendar.adapter = calendarAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpScheduleAdapter(schedules: List<ScheduleModel>) {
        scheduleAdapter = ScheduleAdapter(schedules)
        binding.rvSchedule.adapter = scheduleAdapter
        val scheduleLinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean { return false }
        }
        binding.rvSchedule.layoutManager = scheduleLinearLayoutManager
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpReminderAdapter(schedules: List<ScheduleModel>) {
        reminderAdapter = ReminderAdapter(schedules)
        binding.rvReminder.adapter = reminderAdapter
        val reminderLinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean { return false }
        }
        binding.rvReminder.layoutManager = reminderLinearLayoutManager
    }


    /**
     * Set recycler view
     */
    private fun setUpCalendar() {
        val calendarList = ArrayList<CalendarDateModel>()
        binding.tvDateMonth.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList2.clear()
        calendarList2.addAll(calendarList)
        calendarAdapter.setData(calendarList)
    }

    private fun setUpSchedule() {

    }

    private fun setUpReminder() {

    }
}