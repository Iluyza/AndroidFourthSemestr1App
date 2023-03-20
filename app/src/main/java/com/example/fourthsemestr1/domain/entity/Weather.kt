package com.example.fourthsemestr1.domain.entity

import com.example.fourthsemestr1.domain.enum.WindDeg

data class Weather(
    val id: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val humidity: Int,
    val windDeg: WindDeg,
    val windSpeed: Double,
    val pressure: Int,
    val iconUrl: String
)

