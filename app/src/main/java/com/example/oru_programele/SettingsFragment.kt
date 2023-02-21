package com.example.oru_programele

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    companion object {
        var isFahrenheit = false
        var isAutoUpdate = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        var tempTypeSwitch = view.findViewById<Switch>(R.id.fahrenheitSwitch)
        var autoUpdateSwitch = view.findViewById<Switch>(R.id.updateDataSwitch)

        tempTypeSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            isFahrenheit = isChecked
        })

        //TODO: auto update
        autoUpdateSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->

        })
        return view

    }

}