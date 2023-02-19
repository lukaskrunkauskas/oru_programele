package com.example.oru_programele.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oru_programele.R
import com.example.oru_programele.json.FromJsonConverter

class DayForecastFragment : Fragment() {

    var fromJsonConverter: FromJsonConverter = FromJsonConverter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fromJsonConverter.coverterHourly("vilnius");
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_day_forecast, container, false)
    }
}