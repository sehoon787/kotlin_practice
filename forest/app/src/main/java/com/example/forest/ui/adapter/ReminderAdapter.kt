package com.example.forest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forest.R
import com.example.forest.ui.models.ScheduleModel

class ReminderAdapter(private val items: List<ScheduleModel>) :
    RecyclerView.Adapter<ReminderAdapter.ViewHolder>() {
    private val list = ArrayList<ScheduleModel>()

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var itemClickListener: onItemClickListener

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindItems(items: ScheduleModel) {
            val nameView = itemView.findViewById<TextView>(R.id.tv_name)
            val locationView = itemView.findViewById<TextView>(R.id.tv_location)
            val reminderTimeView = itemView.findViewById<TextView>(R.id.tv_reminder_time)

            nameView.text = items.name
            locationView.text = items.locationName
//            reminderTimeView.text = "${items.startTime} - ${items.endTime}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReminderAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_reminder, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReminderAdapter.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun setData(scheduleList: List<ScheduleModel>) {
        list.clear()
        list.addAll(scheduleList)
        notifyDataSetChanged()
    }
}