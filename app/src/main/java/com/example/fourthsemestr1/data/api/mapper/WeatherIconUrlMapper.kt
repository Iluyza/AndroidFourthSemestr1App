package com.example.fourthsemestr1.data.api.mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherIconUrlMapper @Inject constructor() {


    fun mapToSmallIcon(iconId: String): String {
        return "http://openweathermap.org/img/wn/${iconId}.png"
    }

    fun mapToLargeIcon(iconId: String): String {
        return "http://openweathermap.org/img/wn/${iconId}@2x.png"
    }
}


