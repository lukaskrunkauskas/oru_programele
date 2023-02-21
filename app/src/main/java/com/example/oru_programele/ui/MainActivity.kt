package com.example.oru_programele.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
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

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        refreshButton.setOnClickListener {
            city = spinner.selectedItem.toString()
            insertForecastDataToDatabase()
            weekForecastFragment.fillRecyclerViewData()
            println("updated db")
        }
    }

    private fun insertForecastDataToDatabase() {
        val dayForecastList = fromJsonConverter.coverterHourly(city)
        forecastViewModel.addDayHourlyForecast(dayForecastList)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()

        }
    }
}