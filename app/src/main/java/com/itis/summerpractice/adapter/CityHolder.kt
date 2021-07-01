package com.itis.summerpractice.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.summerpractice.databinding.ItemCityCardBinding
import com.itis.summerpractice.model.City

class CityHolder(
    private val binding: ItemCityCardBinding,
    private val manager: RequestManager,
    private val action: (City) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: City) {
        with(binding) {
            tvCountry.text = item.country
            tvName.text = item.name

            manager.load(item.url)
                .into(ivPromo)
        }
        itemView.setOnClickListener {
            action(item)
        }
    }
}
