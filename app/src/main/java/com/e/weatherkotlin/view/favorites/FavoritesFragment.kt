package com.e.weatherkotlin.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.e.weatherkotlin.R
import com.e.weatherkotlin.databinding.FavoritesFragmentBinding

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {
    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = 1
    private val adapter = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}