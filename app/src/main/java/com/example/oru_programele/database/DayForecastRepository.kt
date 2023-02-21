package com.example.oru_programele.database

import androidx.lifecycle.LiveData
import com.example.oru_programele.database.models.DayForecast
import com.example.oru_programele.database.models.WeekDayForecast

class DayForecastRepository(private val dayForecastDao: DayForecastDao) {

    val readAllData: LiveData<List<DayForecast>> = dayForecastDao.readAllDayForecasts()
    //val readWeekForecasts: LiveData<List<DayForecast>> = dayForecastDao.readWeekForecasts()



//    suspend fun addDayForecast(dayForecast: DayForecast) {
//        dayForecastDao.addDayForecast(dayForecast)
//    }

    suspend fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        dayForecastDao.addDayForecasts(list)
    }

     fun readMultipleDaysForecast(city : String, dateFrom : String, dateTo : String): LiveData<List<WeekDayForecast>> {
         return dayForecastDao.readMultipleDaysForecast(city, dateFrom, dateTo)

    }
}