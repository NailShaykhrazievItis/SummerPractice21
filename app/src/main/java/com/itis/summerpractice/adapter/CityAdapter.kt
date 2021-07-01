package com.itis.summerpractice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.summerpractice.databinding.ItemCityBinding
import com.itis.summerpractice.databinding.ItemCityCardBinding
import com.itis.summerpractice.model.City

class CityAdapter(
    var list: List<City>,
    private val manager: RequestManager,
    private val action: (City) -> Unit
) : RecyclerView.Adapter<CityHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityHolder = CityHolder(
        ItemCityCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        manager,
        action
    )

    override fun onBindViewHolder(holder: CityHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}
