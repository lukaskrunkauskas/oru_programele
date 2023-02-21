package com.example.oru_programele.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oru_programele.R
import com.example.oru_programele.ui.SettingsFragment
import com.example.oru_programele.database.models.DayForecast
import java.math.RoundingMode
import java.text.DecimalFormat

class DayForecastListAdapter : RecyclerView.Adapter<DayForecastListAdapter.MyViewHolder>() {

    private var weekForecastList = emptyList<DayForecast>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.one_hour_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = weekForecastList[position]

        holder.itemView.findViewById<TextView>(R.id.date).text =
            item.dateTime.toString().substring(0, 10)
        holder.itemView.findViewById<TextView>(R.id.hourText).text =
            item.dateTime.toString().substring(11)
        holder.itemView.findViewById<TextView>(R.id.dayTemperature).text =
            fromCelsiusToFahrenheit(round(item.temperature))
    }

    override fun getItemCount(): Int {
        return weekForecastList.size
    }

    fun setData(weekForecast: List<DayForecast>) {
        this.weekForecastList = weekForecast
        notifyDataSetChanged()
    }

    fun fromCelsiusToFahrenheit(celsiusTemperature: Double): String {
        if (SettingsFragment.isFahrenheit) {
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