package com.example.oru_programele.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.oru_programele.database.models.DayForecast
import com.example.oru_programele.database.models.WeekDayForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel(application: Application): AndroidViewModel(application) {

    val readAllDayForecastData: LiveData<List<DayForecast>>
   // val readWeekForecasts: LiveData<List<DayForecast>>
    private val dayForecastRepository: DayForecastRepository

    init {
        val dayForecastDao = ForecastDatabase.getDatabase(application).dayForecastDao()
        dayForecastRepository = DayForecastRepository(dayForecastDao)
        readAllDayForecastData = dayForecastRepository.readAllData
        //readWeekForecasts = dayForecastRepository.readWeekForecasts
    }

    fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        //running in background thread
        viewModelScope.launch(Dispatchers.IO) {
            dayForecastRepository.addDayHourlyForecast(list)
        }
    }

    fun readMultipleDaysForecast(city : String, dateFrom : String, dateTo : String) : LiveData<List<WeekDayForecast>> {
        return dayForecastRepository.readMultipleDaysForecast(city, dateFrom, dateTo)
    }
}