package com.example.oru_programele.data

import com.example.oru_programele.data.models.DayForecast
import dagger.Component

@Component
public interface DayForecastComponent {

    fun getDayForecast(): DayForecast
}