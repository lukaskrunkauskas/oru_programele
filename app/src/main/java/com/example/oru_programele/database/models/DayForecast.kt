package com.example.oru_programele.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "day_forecast", indices = [Index(value = ["dateTime", "city"],
    unique = true)])
data class DayForecast(//reikalingas @Inject kad dagger veiktu
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateTime: LocalDateTime,
    val temperature: Double,
    var city: String
) {

}