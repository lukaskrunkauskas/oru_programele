package com.example.oru_programele.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oru_programele.R
import com.example.oru_programele.database.models.WeekDayForecast
import java.math.RoundingMode
import java.text.DecimalFormat
import com.example.oru_programele.ui.SettingsFragment.Companion.isFahrenheit

class WeekForecastListAdapter : RecyclerView.Adapter<WeekForecastListAdapter.MyViewHolder>() {

    private var weekForecastList = emptyList<WeekDayForecast>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.day_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = weekForecastList[position]

        holder.itemView.findViewById<TextView>(R.id.date).text = item.day.toString()
        holder.itemView.findViewById<TextView>(R.id.dayTemperature).text =
            fromCelsiusToFahrenheit(round(item.dayTemperature))
        holder.itemView.findViewById<TextView>(R.id.nightTemperature).text =
            fromCelsiusToFahrenheit(round(item.nightTemperature))
    }

    override fun getItemCount(): Int {
        return weekForecastList.size
    }

    fun setData(weekForecast: List<WeekDayForecast>) {
        this.weekForecastList = weekForecast
        notifyDataSetChanged()
    }

    fun fromCelsiusToFahrenheit(celsiusTemperature: Double): String {
        if (isFahrenheit) {
            val fahrenheitTemperature = round(celsiusTemperature * 1.8 + 32)
            return "$fahrenheitTemperature °F"
        }
        return "$celsiusTemperature °C"
    }

    fun round(number: Double): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }
}