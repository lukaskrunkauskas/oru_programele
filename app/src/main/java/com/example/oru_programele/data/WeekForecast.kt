package com.example.oru_programele.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(tableName = "week_forecast")
data class WeekForecast (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var dayTemperature: Double,
    var nightTemperature: Double,
    var city: String
) {
//    @Ignore
//    constructor(id: Int, dayTemperature: Double, nightTemperature: Double, date: Date) : this(id, dayTemperature, nightTemperature, date) {}
}