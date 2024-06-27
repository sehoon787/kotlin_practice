package com.example.forest.ui.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forest.R
import com.example.forest.ui.models.ScheduleModel

class ScheduleAdapter(private val items: ArrayList<ScheduleModel>) :
    RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {
    private val list = ArrayList<ScheduleModel>()

    val safeColor = "#1547F9"
    val warnColor = "#C91C1C"

    private fun setContainer(color: String) = GradientDrawable().apply {
        shape = GradientDrawable.RECTANGLE
        cornerRadius = 20f
        setColor(Color.parseColor(color))
        setStroke(1, Color.parseColor(color))
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
        fun bindItems(items: ScheduleModel) {
            val container = itemView.findViewById<LinearLayout>(R.id.lnrLy_schedule)
            val stateView = itemView.findViewById<ImageView>(R.id.iv_state)

            val nameView = itemView.findViewById<TextView>(R.id.tv_name)
            val locationView = itemView.findViewById<TextView>(R.id.tv_location)
            val startTimeView = itemView.findViewById<TextView>(R.id.tv_start_time)

            val warningView1 = itemView.findViewById<ImageView>(R.id.iv_warning1)
            val warningView2 = itemView.findViewById<ImageView>(R.id.iv_warning2)
            val warningView3 = itemView.findViewById<ImageView>(R.id.iv_warning3)
            val companionView1 = itemView.findViewById<ImageView>(R.id.iv_companion1)
            val companionView2 = itemView.findViewById<ImageView>(R.id.iv_companion2)
            val companionCountView = itemView.findViewById<TextView>(R.id.tv_companion_count)

            nameView.text = items.name
            locationView.text = items.locationName

            if(items.warning==2){
                container.background = setContainer(warnColor)
                stateView.setColorFilter(Color.parseColor(warnColor))
            } else if(items.warning==1){
                container.background = setContainer(safeColor)
                stateView.setColorFilter(Color.parseColor(safeColor))
            } else{
                container.background = setContainer(safeColor)
                stateView.setColorFilter(Color.parseColor(safeColor))
            }

//            startTimeView.text = items.startTime

//            if(2<items.warning){ warningView3.setImageResource(items.companionImages) }
//            if(1<items.warning){ warningView2.setImageResource(items.companionImages) }
//            if(0<items.warning){ warningView1.setImageResource(items.companionImages) }
//            stateView.setImageResource(items.companionImages) // -> current time과 start time 비교
//            companionView.setImageResource(items.companionImages)
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

    fun setData(scheduleList: ArrayList<ScheduleModel>) {
        list.clear()
        list.addAll(scheduleList)
        notifyDataSetChanged()
    }
}