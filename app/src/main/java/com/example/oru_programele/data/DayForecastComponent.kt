package com.example.oru_programele.data

import dagger.Component

@Component
public interface DayForecastComponent {

    fun getDayForecast(): DayForecast
}