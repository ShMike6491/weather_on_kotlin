package com.e.weatherkotlin.view.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.e.weatherkotlin.R
import com.e.weatherkotlin.databinding.DetailsFragmentBinding
import com.e.weatherkotlin.model.WeatherDTO
import com.e.weatherkotlin.model.WeatherModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment(), WeatherDataReceiver {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: WeatherModel

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
        displayLoadingPage()


//        val dataAPI = WeatherAPIImpl(this, weatherBundle.city.lat, weatherBundle.city.lon)
//        dataAPI.getWeather()
    }

    private fun displayLoadingPage() {
        with(binding) {
            mainView.visibility = View.GONE
            loadingLayout.visibility = View.VISIBLE
        }
    }

    private fun renderView(data: WeatherDTO = WeatherDTO(null)) {
        with(binding) {
            mainView.visibility = View.VISIBLE
            loadingLayout.visibility = View.GONE
            val city = weatherBundle.city
            cityName.text = city.city
            cityCoordinates.text = String.format(
                getString(R.string.coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            temperatureValue.text = data.fact?.temp.toString()
            feelsLikeValue.text = data.fact?.feels_like.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLoaded(weatherDTO: WeatherDTO) {
        renderView(weatherDTO)
    }

    override fun onFailed(throwable: Throwable) {
        Log.e("", "Error on Request", throwable)
        throwable.printStackTrace()
        renderView()
    }

    private fun View.showSnackBar(
        msg: String,
        actionMsg: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar
            .make(this, msg, length)
            .setAction(actionMsg, action)
            .show()
    }
}