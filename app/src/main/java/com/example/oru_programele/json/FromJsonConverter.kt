package com.example.oru_programele.json

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.oru_programele.data.DayForecast
import com.example.oru_programele.data.WeekForecast
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class FromJsonConverter(): JSONObject()  {
    fun coverterHourly(city: String) : MutableList<DayForecast> {
        if (Build.VERSION.SDK_INT > 9) {
            val gfgPolicy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(gfgPolicy)
        }
        val jsonObj = JSONObject(readUrl(city, 1))
        val forecastObj = jsonObj.getJSONObject("forecast")
        val forecastDayObj = forecastObj.getJSONArray("forecastday").getJSONObject(0)
        val hourlyArr = forecastDayObj.getJSONArray("hour")

        val dayForecastList: MutableList<DayForecast> = mutableListOf()
        for (i in 0 until hourlyArr.length()) {
            val hourObj = hourlyArr.getJSONObject(i)
            val timeStr = hourObj.getString("time")
            val tempC = hourObj.getDouble("temp_c")
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timeStr)
            dayForecastList.add(DayForecast(0, date, tempC, city))
        }

        return dayForecastList
    }

    fun converterWeekly(city : String) : MutableList<WeekForecast>{
        if (Build.VERSION.SDK_INT > 9) {
            val gfgPolicy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(gfgPolicy)
        }
        val jsonObj = JSONObject(readUrl(city, 1))
        val forecastObj = jsonObj.getJSONObject("forecast")
        val forecastDayObj = forecastObj.getJSONArray("forecastday").getJSONObject(0)
        val hourlyArr = forecastDayObj.getJSONArray("hour")

        val dayForecastList: MutableList<DayForecast> = mutableListOf()
        for (i in 0 until hourlyArr.length()) {
            val hourObj = hourlyArr.getJSONObject(i)
            val timeStr = hourObj.getString("time")
            val tempC = hourObj.getDouble("temp_c")
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm").parse(timeStr)
            dayForecastList.add(DayForecast(0, date, tempC, city))
        }

        return dayForecastList
    }


    fun readUrl(city: String, days: Int) : String{
        val apiKey = "ea4bc5fe6c85452cb12142742231902"
        val url = URL("https://api.weatherapi.com/v1/forecast.json?key=$apiKey&q=$city&days=$days")
        val json = url.readText()
        return json
    }

}