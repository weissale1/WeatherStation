package aw.weatherstation.services

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

import kotlinx.coroutines.*

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.WeatherRepository
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.awaitBody

@Service
class WeatherService(private val db: WeatherRepository) {
    val webClient = WebClient.builder()
        .baseUrl("http://sensors:5000")
        .build()

    fun getCurrentWeather(): WeatherData {
        val wd: WeatherData? = runBlocking {
            callWeatherSensorService()
        }
        return wd ?: getLastSavedWeather()
    }

    private fun getLastSavedWeather(): WeatherData {
        return db.findAll().last()
    }

    private suspend fun callWeatherSensorService(): WeatherData? {
        return try {
            webClient.get()
                .uri("/current")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<WeatherData>()
        } catch (ex: Exception) {
            null
        }
    }

}