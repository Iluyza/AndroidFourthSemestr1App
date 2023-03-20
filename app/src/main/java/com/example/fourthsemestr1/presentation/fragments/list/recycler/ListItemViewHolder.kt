package com.example.fourthsemestr1.presentation.fragments.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthsemestr1.databinding.ListItemCityBinding
import com.example.fourthsemestr1.domain.entity.Weather
import com.example.fourthsemestr1.presentation.convertors.TempColorConverter.getColor

class ListItemViewHolder(
    private val binding: ListItemCityBinding,
    private val onItemClick: (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(weather: Weather) {
        with(binding) {
            tvCityItem.text = weather.name
            tvTempItem.text = weather.temp.toString()
            tvTempItem.setTextColor(
                ContextCompat.getColor(
                    tvTempItem.context,
                    getColor(weather.temp)
                )
            )

            root.setOnClickListener {
                onItemClick(weather.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (id: Int) -> Unit
        ) = ListItemViewHolder(
            ListItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }
}




