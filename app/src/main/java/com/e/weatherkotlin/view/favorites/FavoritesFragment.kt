package com.e.weatherkotlin.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.weatherkotlin.R
import com.e.weatherkotlin.databinding.FavoritesFragmentBinding
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.utils.showSnackBar
import com.e.weatherkotlin.view.CallbackClickHandler
import com.e.weatherkotlin.view.RvAdapter
import com.e.weatherkotlin.view.details.DetailsFragment
import com.e.weatherkotlin.viewmodel.AppState
import com.e.weatherkotlin.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment(R.layout.favorites_fragment), CallbackClickHandler{
    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by lazy { ViewModelProvider(this).get(FavoritesViewModel::class.java) }
    private val adapter: RvAdapter by lazy { RvAdapter(this) }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvFavorites.adapter = adapter
        setupViewModel()
    }

    override fun handleClick(model: WeatherModel) {
        val manager = activity?.supportFragmentManager
        if (manager != null) {
            val bundle = Bundle()
            bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, model)
            manager.beginTransaction()
                .add(R.id.container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }

    private fun setupViewModel() {
        viewModel.getData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllFavorites()
    }

    private fun renderData(state: AppState?) {
        when (state) {
            is AppState.Success -> {
                handleSuccess(state.weatherData)
            }
            is AppState.Loading -> {
                binding.loadingFavorites.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                handleError(state.error)
            }
        }
    }

    private fun handleSuccess(weatherData: List<WeatherModel>) {
        binding.loadingFavorites.visibility = View.GONE
        binding.tvEmptyList.visibility = View.VISIBLE
        if (weatherData.isNotEmpty()) {
            binding.tvEmptyList.visibility = View.GONE
            adapter.setWeather(weatherData)
        }
    }

    private fun handleError(error: Throwable) {
        binding.loadingFavorites.visibility = View.GONE
        binding.tvEmptyList.visibility = View.VISIBLE
        binding.favoritesRootView.showSnackBar(
            getString(R.string.error),
            getString(R.string.reload),
            { viewModel.getAllFavorites() }
        )
    }

    override fun onDestroyView() {
        adapter.removeListener()
        super.onDestroyView()
        _binding = null
    }
}