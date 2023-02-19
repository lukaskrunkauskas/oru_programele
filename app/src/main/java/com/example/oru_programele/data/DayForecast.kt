package com.example.oru_programele.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "day_forecast")
data class DayForecast(//reikalingas @Inject kad dagger veiktu
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Date,
    val temperature: Double,
    var city: String
) {

}