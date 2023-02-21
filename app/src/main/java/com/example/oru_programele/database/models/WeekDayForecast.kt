package com.example.oru_programele.database.models

import java.time.LocalDate

data class WeekDayForecast(
    val day: LocalDate,
    val dayTemperature: Double,
    val nightTemperature: Double,
    var city: String
)