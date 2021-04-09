package com.e.weatherkotlin.view.details

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.weatherkotlin.R
import com.e.weatherkotlin.databinding.DetailsFragmentBinding
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.utils.showSnackBar
import com.e.weatherkotlin.viewmodel.AppState
import com.e.weatherkotlin.viewmodel.DetailsViewModel
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: WeatherModel
    private lateinit var viewModel: DetailsViewModel

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: WeatherModel()
        setViewModel()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeatherInfo(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(it: AppState) {
        when (it) {
            is AppState.Loading -> {
                displayLoadingPage()
            }
            is AppState.Success -> {
                setVisible()
                renderView(it.weatherData[0])
            }
            is AppState.Error -> {
                renderError()
            }
        }
    }

    private fun renderView(data: WeatherModel) {
        with(binding) {
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            GlideToVectorYou.justLoadImage(
                activity,
                Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${data.icon}.svg"),
                weatherIcon
            )
            temperatureValue.text = data.temperature.toString()
            feelsLikeValue.text = data.feelsLike.toString()
        }
    }

    private fun renderError() {
        setVisible()
        binding.mainView.showSnackBar(
            getString(R.string.error),
            getString(R.string.reload),
            { viewModel.getWeatherInfo(
                weatherBundle.city.lat,
                weatherBundle.city.lon
            ) }
        )
    }

    private fun displayLoadingPage() {
        with(binding) {
            mainView.visibility = View.GONE
            loadingLayout.visibility = View.VISIBLE
        }
    }

    private fun setVisible() {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}