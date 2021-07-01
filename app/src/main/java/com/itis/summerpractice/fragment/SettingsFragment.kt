package com.itis.summerpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.itis.summerpractice.R
import com.itis.summerpractice.adapter.CityAdapter
import com.itis.summerpractice.databinding.FragmentSettingsBinding
import com.itis.summerpractice.model.City
import com.itis.summerpractice.model.CityRepository

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null
    private var adapter: CityAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CityAdapter(CityRepository.cities, Glide.with(this)) { city ->
            findNavController().navigate(
                R.id.action_settingsFragment_to_cityFragment,
                CityFragment.createBundle(city.id)
            )

//            binding?.also {
//                Snackbar.make(it.root, "Item clicked: ${city.name}", Snackbar.LENGTH_LONG).show()
//            }
        }
        binding?.rvCities?.also {
//            it.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
