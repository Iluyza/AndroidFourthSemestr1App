package com.example.fourthsemestr1.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fourthsemestr1.R
import com.example.fourthsemestr1.data.WeatherRepository
import com.example.fourthsemestr1.databinding.FragmentSearchBinding
import com.example.fourthsemestr1.models.CityWeather
import com.example.fourthsemestr1.models.WindDeg
import com.example.fourthsemestr1.ui.fragments.list.recycler.ListRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch
import timber.log.Timber

private const val COUNT_OF_CITIES_IN_RECYCLER_VIEW = 10
private const val HARDCODE_LAT = 55.7887
private const val HARDCODE_LON = 49.1221

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private val repository by lazy {
        WeatherRepository()
    }
    private var listRecyclerAdapter: ListRecyclerAdapter? = null
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initRecyclerView()

        binding?.svSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.d("Pressed query button")
                kotlin.runCatching {
                    getWeather(query)
                }.onFailure {
                    Toast.makeText(
                        context,
                        "Не удалось найти",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Timber.d("Query text changed")
                return false
            }
        })
    }

    private fun getWeather(city: String) {
        scope.launch {
            withContext(Dispatchers.Main) {
                val weather = repository.getWeather(city)
                showDetailsFragment(weather.name)
            }
        }
    }

    private fun getCities(city: String?) {
        var weather: CityWeather?
        scope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    weather = city?.let { repository.getWeather(it) }
                }
                var weatherCities: MutableList<CityWeather>? = null
                withContext(Dispatchers.IO) {
                    weather?.let { weather ->
                        weatherCities =
                            repository
                                .getCities(
                                    weather.lat,
                                    weather.lon,
                                    COUNT_OF_CITIES_IN_RECYCLER_VIEW
                                )
                    }
                }
                listRecyclerAdapter?.submitList(weatherCities)
            }.onFailure {
                Toast.makeText(
                    context,
                    "Не удалось найти",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun initRecyclerView() {
        binding?.rvSearch?.run {
            listRecyclerAdapter = ListRecyclerAdapter { city ->
                showDetailsFragment(city)
            }
            adapter = listRecyclerAdapter
        }
        listRecyclerAdapter?.submitList(
            arrayListOf(
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
                CityWeather(id=551_487, name="Kazan", lat=0.0, lon=0.0, temp=0.0, humidity=250, WindDeg.NW),
            )
        )
        getCities(HARDCODE_LAT, HARDCODE_LON, COUNT_OF_CITIES_IN_RECYCLER_VIEW)
    }

    private fun getCities(lat: Double, lon: Double, cnt: Int) {
        Log.e("Log","lat is $lat lon is $lon cnt is $cnt" )
    }

    private fun showDetailsFragment(city: String) {
        val bundle = bundleOf(
            "CITY_NAME" to city
        )
        findNavController().navigate(R.id.action_searchFragment_to_detailsFragment, bundle)
    }
}




