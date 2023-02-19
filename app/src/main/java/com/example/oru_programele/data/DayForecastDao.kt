package com.example.oru_programele.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DayForecastDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addDayForecast(dayForecast: DayForecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDayForecasts(dayForecastList: MutableList<DayForecast>)

    @Query("SELECT * FROM day_forecast ORDER BY id ASC")
    fun readAllDayForecasts(): LiveData<List<DayForecast>>
}