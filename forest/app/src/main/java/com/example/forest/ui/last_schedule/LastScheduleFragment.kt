package com.example.forest.ui.last_schedule

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
import com.example.forest.data.models.schedulesSample3
import com.example.forest.databinding.FragmentLastScheduleBinding
import com.example.forest.ui.adapter.ScheduleAdapter
import com.example.forest.ui.models.ScheduleModel

class LastScheduleFragment : Fragment() {

    private var _binding: FragmentLastScheduleBinding? = null

    private val binding get() = _binding!!

    /**
     * Adapter LastSchedule
     */
    private lateinit var scheduleAdapter: ScheduleAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val lastScheduleViewModel = ViewModelProvider(this)[LastScheduleViewModel::class.java]

        lastScheduleViewModel.updateLastSchedule(schedulesSample3)

        _binding = FragmentLastScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        lastScheduleViewModel.lastSchedulesSchedules.observe(viewLifecycleOwner) {
            setUpLastScheduleAdapter(it)
            setUpLastScheduleClickListener()
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
    private fun setUpLastScheduleClickListener() {
        scheduleAdapter.setItemClickListener(object: ScheduleAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "ScheduleAdapter: hello! I'm callback", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Setting up adapter for recyclerview
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpLastScheduleAdapter(schedules: List<ScheduleModel>) {
        scheduleAdapter = ScheduleAdapter(schedules)
        binding.rvLastSchedule.adapter = scheduleAdapter
        val scheduleLinearLayoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean { return false }
        }
        binding.rvLastSchedule.layoutManager = scheduleLinearLayoutManager
    }
}