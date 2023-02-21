package com.example.oru_programele.database

import androidx.lifecycle.LiveData
import com.example.oru_programele.database.models.DayForecast
import com.example.oru_programele.database.models.WeekDayForecast

class ForecastRepository(private val dayForecastDao: ForecastDao) {

    val readAllData: LiveData<List<DayForecast>> = dayForecastDao.readAllDayForecasts()

    suspend fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        dayForecastDao.addDayForecasts(list)
    }

    fun readMultipleDaysForecast(
        city: String,
        dateFrom: String,
        dateTo: String
    ): LiveData<List<WeekDayForecast>> {
        return dayForecastDao.readMultipleDaysForecast(city, dateFrom, dateTo)
    }

    fun readOneDayHourlyForecast(city: String, date: String): LiveData<List<DayForecast>> {
        return dayForecastDao.readOneDayHourlyForecast(city, date)
    }
}