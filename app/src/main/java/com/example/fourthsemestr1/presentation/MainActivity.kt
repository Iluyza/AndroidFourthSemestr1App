package com.example.fourthsemestr1.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fourthsemestr1.R
import com.example.fourthsemestr1.WeatherApp
import com.example.fourthsemestr1.databinding.ActivityMainBinding
import com.example.fourthsemestr1.extentions.findController

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WeatherApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        controller = binding.navHostFragmentMain.id.let { findController(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        controller = null
    }
}
