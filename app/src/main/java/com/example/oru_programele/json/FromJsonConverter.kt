package com.example.oru_programele.json

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.oru_programele.database.models.DayForecast
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


class FromJsonConverter : JSONObject() {
    val DAYS = 7


    suspend fun coverterHourly(city: String): MutableList<DayForecast> {
            val jsonObj = JSONObject(readUrl(city, DAYS))
        val forecastObj = jsonObj.getJSONObject("forecast")
        val dayForecastList: MutableList<DayForecast> = mutableListOf()
        for (i in 0..forecastObj.getJSONArray("forecastday").length() - 1) {
            val forecastDayObj = forecastObj.getJSONArray("forecastday").getJSONObject(i)
            val hourlyArr = forecastDayObj.getJSONArray("hour")
            for (i in 0 until hourlyArr.length()) {
                val hourObj = hourlyArr.getJSONObject(i)
                val timeEpoch = hourObj.getLong("time_epoch")
                val tempC = hourObj.getDouble("temp_c")
                dayForecastList.add(DayForecast(0, getLocalDateTime(timeEpoch), tempC, city))
            }
        }
        return dayForecastList
    }

    suspend fun readUrl(city: String, days: Int) : String = withContext(Dispatchers.IO) {
        val apiKey = "ea4bc5fe6c85452cb12142742231902"
        val url = URL("https://api.weatherapi.com/v1/forecast.json?key=$apiKey&q=$city&days=$days")
        return@withContext url.readText()
    }

    private fun getLocalDateTime(timeEpoch: Long): LocalDateTime {
        val dt = Instant.ofEpochSecond(timeEpoch)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
        return dt
    }
}