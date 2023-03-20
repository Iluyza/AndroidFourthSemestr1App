package com.example.fourthsemestr1.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.fourthsemestr1.databinding.ActivityMainBinding
import com.example.fourthsemestr1.extention.findController

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var controller: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        controller = binding?.navHostFragmentMain?.id?.let { findController(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        controller = null
    }
}



