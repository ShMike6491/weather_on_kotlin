package com.e.weatherkotlin.view.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.weatherkotlin.R
import com.e.weatherkotlin.databinding.MainFragmentBinding
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.utils.showSnackBar
import com.e.weatherkotlin.view.CallbackClickHandler
import com.e.weatherkotlin.view.RvAdapter
import com.e.weatherkotlin.view.details.DetailsFragment
import com.e.weatherkotlin.view.favorites.FavoritesFragment
import com.e.weatherkotlin.viewmodel.AppState
import com.e.weatherkotlin.viewmodel.MainViewModel

private const val LIST_OF_CITIES = "com.e.weatherkotlin.view.main.CITIES_LIST"

class MainFragment : Fragment(), CallbackClickHandler {

    var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private var isRusData: Boolean = true
    private val adapter = RvAdapter(this)

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener { changeWeatherDataSet() }
        setViewModel()
        showCitiesList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_saved -> {
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.add(R.id.container, FavoritesFragment.newInstance())
                    ?.addToBackStack("")
                    ?.commitAllowingStateLoss()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun changeWeatherDataSet() {
        if (!isRusData) {
            viewModel.getDataFromCashRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        } else {
            viewModel.getDataFromCashWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        }
        isRusData = !isRusData

        saveCitiesList()
    }

    private fun saveCitiesList() {
        activity?.let {
            with(it.getPreferences(Context.MODE_PRIVATE).edit()) {
                putBoolean(LIST_OF_CITIES, !isRusData)
                apply()
            }
        }
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getDataFromCashRus()
    }

    private fun showCitiesList() {
        activity?.let {
            if (it.getPreferences(Context.MODE_PRIVATE).getBoolean(LIST_OF_CITIES, false)) {
                changeWeatherDataSet()
            } else {
                viewModel.getDataFromCashRus()
            }
        }
    }

    private fun renderData(state: AppState?) {
        when (state) {
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(state.weatherData)
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                binding.mainFragmentRootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    { viewModel.getDataFromCashRus() }
                )
            }
        }
    }

    override fun onDestroyView() {
        adapter.removeListener()
        super.onDestroyView()
        _binding = null
    }
}