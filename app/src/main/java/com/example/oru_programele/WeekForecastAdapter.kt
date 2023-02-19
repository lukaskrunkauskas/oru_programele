package com.example.oru_programele

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.oru_programele.data.WeekForecast
import com.example.oru_programele.R.id
import com.example.oru_programele.R.layout


class WeekForecastAdapter(val activity: FragmentActivity, val list:List<WeekForecast>) : ArrayAdapter<WeekForecast>(
    activity, layout.list_item) {

    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val context = activity.layoutInflater
        val rowView = context.inflate(layout.list_item, null)

        val dayTemp = rowView.findViewById<TextView>(id.dayTemperature)
        val nightTemp = rowView.findViewById<TextView>(id.nightTemperature)
        val date = rowView.findViewById<TextView>(id.date)

        dayTemp.text = list[position].dayTemperature.toString()
        nightTemp.text = list[position].nightTemperature.toString()
        date.text = list[position].date.toString()

        return rowView
    }
}