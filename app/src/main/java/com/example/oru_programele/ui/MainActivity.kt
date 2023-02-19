package com.example.oru_programele.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.oru_programele.R
import com.example.oru_programele.data.ForecastViewModel
import com.example.oru_programele.json.FromJsonConverter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var forecastViewModel: ForecastViewModel
    var fromJsonConverter: FromJsonConverter = FromJsonConverter()
    lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dayForecastFragment = DayForecastFragment()
        val weekForecastFragment = WeekForecastFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val refreshButton = findViewById<Button>(R.id.getDataButton)

        ArrayAdapter.createFromResource(
            this,
            R.array.cities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            city = spinner.selectedItem.toString()
        }


        replaceFragment(dayForecastFragment)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.dayForecast -> replaceFragment(dayForecastFragment)
                R.id.weekforecast -> replaceFragment(weekForecastFragment)
            }
            true
        }

        //spinner.selectedItem.toString()

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        refreshButton.setOnClickListener {
            city = spinner.selectedItem.toString()
            insertForecastDataToDatabase()
        }

    }

    private fun insertForecastDataToDatabase() {
        val dayForecastList = fromJsonConverter.coverterHourly(city)
        val weekForecastList = fromJsonConverter.converterWeekly(city)
        forecastViewModel.addDayHourlyForecast(dayForecastList)
        forecastViewModel.addWeekDailyForecast(weekForecastList)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}