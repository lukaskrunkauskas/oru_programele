package com.example.oru_programele.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.oru_programele.R
import com.example.oru_programele.WeekForecastAdapter
import com.example.oru_programele.data.WeekForecast
import com.google.gson.internal.bind.TypeAdapters
import java.time.LocalDate
import java.util.*

class WeekForecastFragment : Fragment() {

    lateinit var weekForecastAdapter: WeekForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_week_forecast, container, false)
        val list = mutableListOf<WeekForecast>()
        list.add(WeekForecast(1, Date(2000, 4, 5),4.5, 1.2, "Vilnius"))
        list.add(WeekForecast(2, Date(2000, 4, 5), 10.5, 178.2, "Kaunas"))
        list.add(WeekForecast(3, Date(2000, 4, 5), 999.5, 18.2, "Vilkaviskis"))

        weekForecastAdapter = WeekForecastAdapter(requireActivity(), list)

        val listView = view.findViewById<ListView>(R.id.listView)
        listView.setAdapter(weekForecastAdapter)

        return view
    }
}