package com.example.oru_programele.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "week_forecast")
data class WeekForecast(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: LocalDate,
    var dayTemperature: Double,
    var nightTemperature: Double,
    var city: String
) {
}