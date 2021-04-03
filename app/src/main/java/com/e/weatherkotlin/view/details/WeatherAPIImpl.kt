package com.e.weatherkotlin.view.details

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.e.weatherkotlin.BuildConfig
import com.e.weatherkotlin.model.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import java.lang.Exception
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.N)
class WeatherAPIImpl(private val receiver: WeatherDataReceiver, private val lat: Double, private val lon: Double) : WeatherAPI {
    private val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
    private val handler = Handler()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getWeather() {
        try {
            makeAPIRequest()
        } catch (e: MalformedURLException) {
            handleError(e, "Fail URI")
        }
    }

    private fun makeAPIRequest() {
        Thread(Runnable {
            lateinit var httpsConnection: HttpsURLConnection
            try {
                httpsConnection = uri.openConnection() as HttpsURLConnection
                val bufferedReader = setupConnection(httpsConnection)
                val json = getJson(bufferedReader)
                val data: WeatherDTO = Gson().fromJson(json, WeatherDTO::class.java)
                handler.post { receiver.onLoaded(data) }
            } catch (e: Exception) {
                handler.post{ handleError(e, "Connection Error") }
            } finally {
                httpsConnection.disconnect()
            }
        }).start()
    }

    private fun setupConnection(connection: HttpsURLConnection): BufferedReader{
        connection.requestMethod = "GET"
        connection.addRequestProperty(
            "X-Yandex-API-Key",
            BuildConfig.WEATHER_API_KEY
        )
        connection.readTimeout = 10000
        return BufferedReader(InputStreamReader(connection.inputStream))
    }

    private fun getJson(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun handleError(e: Exception, msg: String) {
        Log.e("", msg, e)
        e.printStackTrace()
        receiver.onFailed(e)
    }
}