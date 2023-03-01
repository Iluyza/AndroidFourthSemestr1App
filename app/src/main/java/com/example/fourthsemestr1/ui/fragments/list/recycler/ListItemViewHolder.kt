package com.example.fourthsemestr1.ui.fragments.list.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthsemestr1.databinding.ListItemCityBinding
import com.example.fourthsemestr1.models.CityWeather
import com.example.fourthsemestr1.models.convertors.TempColorConverter.getColor

class ListItemViewHolder(
    itemView: View,
    private val onItemClick: (city: String) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListItemCityBinding.bind(itemView)

    fun bind(cityWeather: CityWeather) {
        with(binding) {
            tvCityItem.text = cityWeather.name
            tvTempItem.text = cityWeather.temp.toString()
            tvTempItem.setTextColor(getColor(cityWeather.temp))

            root.setOnClickListener {
                onItemClick(cityWeather.name)
            }
        }
    }
}

