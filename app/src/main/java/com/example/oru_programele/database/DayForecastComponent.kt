package com.example.oru_programele.database

import com.example.oru_programele.database.models.DayForecast
import dagger.Component

@Component
public interface DayForecastComponent {

    fun getDayForecast(): DayForecast
}