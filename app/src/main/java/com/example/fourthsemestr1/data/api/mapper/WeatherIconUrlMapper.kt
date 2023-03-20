package com.example.fourthsemestr1.data.api.mapper

class WeatherIconUrlMapper {
    fun map(iconId: String): String {
        return "http://openweathermap.org/img/wn/${iconId}@2x.png"
    }
}

