package com.example.oru_programele.data.models

import java.time.LocalDate
import java.time.LocalDateTime

data class WeekDayForecast(
    val day: LocalDate,
    val dayTemperature: Double,
    val nightTemperature: Double,
    var city: String) {}