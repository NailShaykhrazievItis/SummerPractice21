package com.itis.summerpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.itis.summerpractice.databinding.FragmentCityBinding
import com.itis.summerpractice.model.City
import com.itis.summerpractice.model.CityRepository

class CityFragment : Fragment() {

    private var binding: FragmentCityBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(ARG_ID)?.also { id ->
            val map = CityRepository.cities.associateBy { it.id }
            map[id]?.also {
                setCityData(it)
            }
//            CityRepository.cities.firstOrNull { it.id == id }?.also {
//                setCityData(it)
//            }
        }

        arguments?.getString(ARG_NAME)?.also {
            binding?.tvName?.text = it
        }
        arguments?.getString(ARG_COUNTRY)?.also {
            binding?.tvCountry?.text = it
        }
        arguments?.getString(ARG_URL)?.also { url ->
            binding?.image?.also {
                Glide.with(this)
                    .load(url)
                    .into(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setCityData(city: City) {
        binding?.run {
            tvName.text = city.name
            tvCountry.text = city.country
            Glide.with(this@CityFragment)
                .load(city.url)
                .into(image)
        }
    }

    companion object {

        private const val ARG_NAME = "ARG_NAME"
        private const val ARG_COUNTRY = "ARG_COUNTRY"
        private const val ARG_URL = "ARG_URL"

        private const val ARG_ID = "ARG_ID"

        fun createBundle(id: Int) = Bundle().apply {
//            putString(ARG_NAME, name)
//            putString(ARG_COUNTRY, country)
//            putString(ARG_URL, url)

            putInt(ARG_ID, id)
        }
    }
}
