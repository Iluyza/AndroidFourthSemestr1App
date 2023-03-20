package com.example.fourthsemestr1.presentation.fragments.list.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.fourthsemestr1.domain.entity.Weather

object CityWeatherDiffCallback : DiffUtil.ItemCallback<Weather>(){
    override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
        return oldItem == newItem
    }
}





