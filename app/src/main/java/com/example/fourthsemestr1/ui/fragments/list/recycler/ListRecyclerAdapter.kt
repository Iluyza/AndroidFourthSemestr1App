package com.example.fourthsemestr1.ui.fragments.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.fourthsemestr1.R
import com.example.fourthsemestr1.models.CityWeather

class ListRecyclerAdapter(
    private val onItemClick: (city: String) -> Unit
) : ListAdapter<CityWeather, ListItemViewHolder>(CityWeatherDiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ListItemViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_city, parent, false),
        onItemClick
    )

    override fun onBindViewHolder(
        holder: ListItemViewHolder,
        position: Int
    ) = holder.bind(
        getItem(position)
    )

    override fun submitList(list: MutableList<CityWeather>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
