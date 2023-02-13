package com.example.oru_programele

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dayForecast = DayForecast()
        val weekForecast = WeekForecast()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val spinner = findViewById<Spinner>(R.id.spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.cities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        replaceFragment(dayForecast)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.dayForecast -> replaceFragment(dayForecast)
                R.id.weekforecast -> replaceFragment(weekForecast)
            }
            true
        }

        //spinner.selectedItem.toString()
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        }
    }
}