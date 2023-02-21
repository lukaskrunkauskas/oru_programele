package com.example.oru_programele.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oru_programele.R
import com.example.oru_programele.adapters.DayForecastListAdapter
import com.example.oru_programele.database.ForecastViewModel
import java.time.LocalDate

class DayForecastFragment : Fragment() {

    lateinit var v: View
    lateinit var mainActivity : MainActivity
    private lateinit var forecastViewModel: ForecastViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //fromJsonConverter.coverterHourly("vilnius");
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =  inflater.inflate(R.layout.fragment_day_forecast, container, false)
        mainActivity = activity as MainActivity

        fillRecyclerViewData()
        return v
    }

    fun fillRecyclerViewData() {
        val adapter = DayForecastListAdapter()
        val recyclerView = v.findViewById<RecyclerView>(R.id.dayForecastRecyclerView)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        //TODO: iskelti i virsu
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        forecastViewModel.readOneDayHourlyForecast(mainActivity.city, LocalDate.now().toString())
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {forecast ->
                adapter.setData(forecast)
            })
    }
}