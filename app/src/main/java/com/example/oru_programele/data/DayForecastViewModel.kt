package com.example.oru_programele.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DayForecastViewModel(application: Application): AndroidViewModel(application) {

    private val readAllDayForecastData: LiveData<List<DayForecast>>
    private val readAllWeekForecastData: LiveData<List<WeekForecast>>
    private val dayForecastRepository: DayForecastRepository
    private val weekForecastRepository: WeekForecastRepository

    init {
        val dayForecastDao = ForecastDatabase.getDatabase(application).dayForecastDao()
        val weekForecastDao = ForecastDatabase.getDatabase(application).weekForecastDao()
        dayForecastRepository = DayForecastRepository(dayForecastDao)
        weekForecastRepository = WeekForecastRepository(weekForecastDao)
        readAllDayForecastData = dayForecastRepository.readAllData
        readAllWeekForecastData = weekForecastRepository.readAllData
    }

    //fun add


}