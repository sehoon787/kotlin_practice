package com.example.forest.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.forest.R
import com.example.forest.ui.models.ScheduleModel

class ScheduleAdapter(private val items: List<ScheduleModel>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val list = ArrayList<ScheduleModel>()

    private fun setContainer(color: Int) = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 20f
        setColor(color)
        setStroke(1, color)
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var itemClickListener: onItemClickListener

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: ScheduleModel) {
            val safeColor = ContextCompat.getColor(itemView.context, R.color.safe)
            val alertColor = ContextCompat.getColor(itemView.context, R.color.alert)
            val warnColor = ContextCompat.getColor(itemView.context, R.color.warn)

            val container = itemView.findViewById<LinearLayout>(R.id.lnrLy_schedule)

            val nameView = itemView.findViewById<TextView>(R.id.tv_name)
            val locationView = itemView.findViewById<TextView>(R.id.tv_location)
            val startTimeView = itemView.findViewById<TextView>(R.id.tv_start_time)

            val stateView = itemView.findViewById<ImageView>(R.id.iv_state)
            val warningView1 = itemView.findViewById<ImageView>(R.id.iv_warning1)
            val warningView2 = itemView.findViewById<ImageView>(R.id.iv_warning2)
            val warningView3 = itemView.findViewById<ImageView>(R.id.iv_warning3)

            val companionView1 = itemView.findViewById<ImageView>(R.id.iv_companion1)
            val companionView2 = itemView.findViewById<ImageView>(R.id.iv_companion2)
            val companionCountView = itemView.findViewById<TextView>(R.id.tv_companion_count)

            nameView.text = item.name
            locationView.text = item.locationName

            when (item.warning) {
                2 -> {
                    container.background = setContainer(warnColor)
                    stateView.setColorFilter(warnColor)
                    warningView3.isVisible = true
                    warningView2.isVisible = true
                    warningView1.isVisible = true
                }
                1 -> {
                    container.background = setContainer(alertColor)
                    stateView.setColorFilter(alertColor)
                    warningView3.isVisible = false
                    warningView2.isVisible = true
                    warningView1.isVisible = true
                }
                0 -> {
                    container.background = setContainer(safeColor)
                    stateView.setColorFilter(safeColor)
                    warningView3.isVisible = false
                    warningView2.isVisible = false
                    warningView1.isVisible = true
                }
                else -> {
                    container.background = setContainer(safeColor)
                    stateView.setColorFilter(safeColor)
                    warningView3.isVisible = false
                    warningView2.isVisible = false
                    warningView1.setColorFilter(safeColor)
                }
            }

//            startTimeView.text = item.startTime
//            companionView.setImageResource(item.companionImages)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScheduleAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_schedule, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) {
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