package com.example.oru_programele.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "day_forecast")
data class DayForecast(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: LocalDateTime,
    val temperature: Double,
    var city: String
) {
}