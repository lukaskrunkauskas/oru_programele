package com.example.oru_programele.data

import androidx.lifecycle.LiveData
import com.example.oru_programele.data.models.DayForecast
import com.example.oru_programele.data.models.WeekDayForecast
import com.example.oru_programele.data.models.WeekForecast

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
        var temp = dayForecastDao.readMultipleDaysForecast(city, dateFrom, dateTo)
         return temp

    }
}