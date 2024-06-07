package com.example.todolist

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedTodo?.let {
            binding.todoEditText.setText(it.title)
            binding.calendarView.date = it.date
        }

        val calendar = Calendar.getInstance()

        binding.calendarView.setOnDateChangeListener { _, year, mon, day ->
            calendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, mon)
                set(Calendar.DAY_OF_MONTH, day)
            }
        }

        binding.doneFab.setOnClickListener {
            if(binding.todoEditText.text.toString().isNotEmpty()){
                if(viewModel.selectedTodo != null){
                    viewModel.updateTodo(binding.todoEditText.text.toString(), calendar.timeInMillis)
                } else{
                    viewModel.addTodo(binding.todoEditText.text.toString(), calendar.timeInMillis)
                }
                findNavController().popBackStack()
            }
        }

        binding.deleteFab.setOnClickListener {
            viewModel.deleteTodo(viewModel.selectedTodo!!.id)
            findNavController().popBackStack()
        }

        if(viewModel.selectedTodo == null){ binding.deleteFab.visibility = View.GONE }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}