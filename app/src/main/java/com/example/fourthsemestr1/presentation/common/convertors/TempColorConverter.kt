package com.example.fourthsemestr1.presentation.common.convertors

import com.example.fourthsemestr1.R

object TempColorConverter {
    fun getColor(temp: Double): Int {
        return when {
            temp < -20 -> R.color.temp_less_minus20
            temp < 10 -> R.color.temp_minus20_minus10
            temp < 0 -> R.color.temp_minus10_0
            temp < 10 -> R.color.temp_0_10
            temp < 20 -> R.color.temp_10_20
            else -> R.color.temp_more20
        }
    }
}

