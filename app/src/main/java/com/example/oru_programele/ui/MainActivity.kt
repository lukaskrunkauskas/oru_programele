package com.example.oru_programele.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.oru_programele.R
import com.example.oru_programele.database.ForecastViewModel
import com.example.oru_programele.json.FromJsonConverter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.oru_programele.ui.SettingsFragment.Companion.isAutoUpdate

class MainActivity : AppCompatActivity() {

    private lateinit var forecastViewModel: ForecastViewModel
    var fromJsonConverter: FromJsonConverter = FromJsonConverter()
    lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dayForecastFragment = DayForecastFragment()
        val weekForecastFragment = WeekForecastFragment()
        val spinner = findViewById<Spinner>(R.id.spinner)
        val refreshButton = findViewById<Button>(R.id.getDataButton)

        startTimer()

        ArrayAdapter.createFromResource(
            this,
            R.array.cities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            city = spinner.selectedItem.toString()
        }

        forecastViewModel = ViewModelProvider(this).get(ForecastViewModel::class.java)

        refreshButton.setOnClickListener {
            city = spinner.selectedItem.toString()
            insertForecastDataToDatabase()

            forecastViewModel.selectedItem.observe(this, androidx.lifecycle.Observer { item ->
                if (dayForecastFragment.isVisible) {
                    dayForecastFragment.fillRecyclerViewData(item)
                } else {
                    weekForecastFragment.fillRecyclerViewData(item)
                }
            })
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            val id = navController.currentDestination?.id
            navController.popBackStack(id!!, true)
            navController.navigate(id)
        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setupWithNavController(navController)
    }

    private fun insertForecastDataToDatabase() {
        val dayForecastList = fromJsonConverter.coverterHourly(city)
        forecastViewModel.addDayHourlyForecast(dayForecastList)
    }

    private fun startTimer() {
        val t: Thread = object : Thread() {
            override fun run() {
                while (true) {
                    try {
                        sleep((1000 * 60 * 60).toLong())
                        if (isAutoUpdate) {
                            insertForecastDataToDatabase()
                        }
                    } catch (ie: InterruptedException) {
                    }
                }
            }
        }
        t.start()
    }
}