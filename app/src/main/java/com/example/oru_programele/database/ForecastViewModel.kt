package com.example.oru_programele.database

import android.app.Application
import android.content.ClipData
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.oru_programele.database.models.DayForecast
import com.example.oru_programele.database.models.WeekDayForecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForecastViewModel(application: Application) : AndroidViewModel(application) {

    val readAllDayForecastData: LiveData<List<DayForecast>>
    private val dayForecastRepository: ForecastRepository
    private val mutableSelectedItem = MutableLiveData<View>()
    val selectedItem: LiveData<View> get() = mutableSelectedItem

    init {
        val dayForecastDao = ForecastDatabase.getDatabase(application).dayForecastDao()
        dayForecastRepository = ForecastRepository(dayForecastDao)
        readAllDayForecastData = dayForecastRepository.readAllData
    }

    fun addDayHourlyForecast(list: MutableList<DayForecast>) {
        viewModelScope.launch(Dispatchers.IO) {
            dayForecastRepository.addDayHourlyForecast(list)
        }
    }

    fun readMultipleDaysForecast(
        city: String,
        dateFrom: String,
        dateTo: String
    ): LiveData<List<WeekDayForecast>> {
        return dayForecastRepository.readMultipleDaysForecast(city, dateFrom, dateTo)
    }

    fun readOneDayHourlyForecast(city: String, date: String): LiveData<List<DayForecast>> {
        return dayForecastRepository.readOneDayHourlyForecast(city, date)
    }

    fun selectItem(item: View) {
        mutableSelectedItem.value = item
    }
}