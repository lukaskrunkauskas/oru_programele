package com.example.oru_programele.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.oru_programele.R
import java.time.LocalDate

class SettingsFragment : Fragment() {

    companion object {
        var isFahrenheit = false
        var isAutoUpdate = false
        var updateDate = LocalDate.of(2000, 1, 1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        var date = view.findViewById<TextView>(R.id.updateDate)
        date.text = updateDate.toString()

        var tempTypeSwitch = view.findViewById<Switch>(R.id.fahrenheitSwitch)
        var autoUpdateSwitch = view.findViewById<Switch>(R.id.updateDataSwitch)

        tempTypeSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            isFahrenheit = isChecked
        })

        autoUpdateSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            isAutoUpdate = isChecked
        })
        return view
    }
}