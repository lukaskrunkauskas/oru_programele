package com.example.oru_programele.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oru_programele.adapters.WeekForecastListAdapter
import com.example.oru_programele.R
import com.example.oru_programele.database.ForecastViewModel
import java.time.LocalDate

class WeekForecastFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private lateinit var forecastViewModel: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var v = inflater.inflate(R.layout.fragment_week_forecast, container, false)
        mainActivity = activity as MainActivity
        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)
        fillRecyclerViewData(v)
        return v
    }

    fun fillRecyclerViewData(v: View) {
        val adapter = WeekForecastListAdapter()
        val recyclerView = v.findViewById<RecyclerView>(R.id.weekForecastRecyclerView)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        forecastViewModel.readMultipleDaysForecast(
            mainActivity.city,
            LocalDate.now().toString(),
            LocalDate.now().plusDays(7).toString()
        )
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer { forecast ->
                adapter.setData(forecast)
            })
    }
}