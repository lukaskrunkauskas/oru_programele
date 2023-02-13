package com.example.oru_programele.data

import androidx.lifecycle.LiveData

class WeekForecastRepository(private val weekForecastDao: WeekForecastDao) {

    val readAllData: LiveData<List<WeekForecast>> = weekForecastDao.readAllWeekForecasts()

    suspend fun addWeekForecast(weekForecast: WeekForecast) {
        weekForecastDao.addWeekForecast(weekForecast)
    }
}