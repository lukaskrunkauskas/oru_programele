package com.example.oru_programele.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.oru_programele.data.models.DayForecast
import com.example.oru_programele.data.models.WeekDayForecast

@Dao
interface DayForecastDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addDayForecast(dayForecast: DayForecast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDayForecasts(dayForecastList: MutableList<DayForecast>)

    @Query("SELECT * FROM day_forecast ORDER BY id ASC")
    fun readAllDayForecasts(): LiveData<List<DayForecast>>

//    @Query("SELECT * FROM day_forecast WHERE date >= DATETIME('now') AND date < DATETIME('now', '+7 day')")
//    fun readWeekForecasts() : LiveData<List<DayForecast>>

    @Query("""
        SELECT 
        day,
        sum(avg_day_temp) as dayTemperature,
        sum(avg_night_temp) as nightTemperature,
        city
        FROM 
        (
            SELECT strftime('%Y-%m-%d', dateTime) AS day, 
            AVG(temperature) AS avg_day_temp,
            0 AS avg_night_temp,
            city
            FROM day_forecast
            WHERE strftime('%H', dateTime) >= '12' 
            AND strftime('%H', dateTime) <= '23' 
            AND strftime('%Y-%m-%d', dateTime) >= :dateFrom
            AND strftime('%Y-%m-%d', dateTime) <= :dateTo
            AND city = :city
            GROUP BY day
            
            UNION
            
            SELECT strftime('%Y-%m-%d', dateTime) AS day, 
            0 AS avg_day_temp,
            AVG(temperature) AS avg_night_temp,
            city
            FROM day_forecast
            WHERE strftime('%H', dateTime) >= '00' 
            AND strftime('%H', dateTime) < '12' 
            AND strftime('%Y-%m-%d', dateTime) >= :dateFrom
            AND strftime('%Y-%m-%d', dateTime) <= :dateTo
            AND city = :city
            GROUP BY day 
        ) as temps
        group by day
    """)
    fun readMultipleDaysForecast(city : String, dateFrom : String, dateTo : String) : LiveData<List<WeekDayForecast>>
}