package com.example.oru_programele.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oru_programele.R
import com.example.oru_programele.data.models.WeekDayForecast

class WeekForecastListAdapter: RecyclerView.Adapter<WeekForecastListAdapter.MyViewHolder>() {

    private var weekForecastList = emptyList<WeekDayForecast>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = weekForecastList[position]
        holder.itemView.findViewById<TextView>(R.id.date).text = item.day.toString()
        holder.itemView.findViewById<TextView>(R.id.dayTemperature).text = item.dayTemperature.toString()
        holder.itemView.findViewById<TextView>(R.id.nightTemperature).text = item.nightTemperature.toString()
    }

    override fun getItemCount(): Int {
        return weekForecastList.size
    }

    fun setData(weekForecast: List<WeekDayForecast>) {
        this.weekForecastList = weekForecast
        println("RADAU")
        println(weekForecast.get(0).nightTemperature)
        notifyDataSetChanged()

    }

    fun fromCelsiusToFahrenheit()

}