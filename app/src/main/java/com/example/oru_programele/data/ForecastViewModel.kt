package com.example.oru_programele.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel(application: Application): AndroidViewModel(application) {

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

    fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        //running in background thread
        viewModelScope.launch(Dispatchers.IO) {
            dayForecastRepository.addDayHourlyForecast(list)
        }
    }

    fun addWeekDailyForecast(list: MutableList<WeekForecast>) {
        viewModelScope.launch(Dispatchers.IO) {
            weekForecastRepository.addWeekDailyForecast(list)
        }
    }


}