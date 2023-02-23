package com.example.oru_programele.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import com.example.oru_programele.ui.SettingsFragment.Companion.updateDate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

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
        insertForecastDataToDatabase()

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
        if (isOnline(applicationContext)) {
            GlobalScope.launch {
                val dayForecastList = fromJsonConverter.coverterHourly(city)
                forecastViewModel.addDayHourlyForecast(dayForecastList)
                updateDate = LocalDate.now()
            }
        }
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

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}