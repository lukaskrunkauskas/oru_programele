package com.example.oru_programele.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeekForecastDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWeekForecast(weekForecast: WeekForecast)

    @Query("SELECT * FROM week_forecast ORDER BY id ASC")
    fun readAllWeekForecasts(): LiveData<List<WeekForecast>>
}