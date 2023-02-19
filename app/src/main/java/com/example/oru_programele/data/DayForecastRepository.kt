package com.example.oru_programele.data

import androidx.lifecycle.LiveData

class DayForecastRepository(private val dayForecastDao: DayForecastDao) {

    val readAllData: LiveData<List<DayForecast>> = dayForecastDao.readAllDayForecasts()

//    suspend fun addDayForecast(dayForecast: DayForecast) {
//        dayForecastDao.addDayForecast(dayForecast)
//    }

    suspend fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        dayForecastDao.addDayForecasts(list)
    }
}