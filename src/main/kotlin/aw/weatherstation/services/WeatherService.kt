package aw.weatherstation.services

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

import kotlinx.coroutines.*

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.WeatherRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.reactive.function.client.awaitBody

@Service
class WeatherService(private val db: WeatherRepository) {
    val webClient = WebClient.builder()
        .baseUrl("http://sensors:5000")
        .build()
    val logger: Logger = LoggerFactory.getLogger(WeatherService::class.java)

    fun getCurrentWeather(): WeatherData {
        val wd: WeatherData? = runBlocking {
            callWeatherSensorService()
        }
        return wd ?: getLastSavedWeather()
    }

    @Scheduled(fixedRate = 300000) // 300,000 milliseconds = 5 minutes
    fun recordWeatherData() {
        val wd: WeatherData? = runBlocking {
            callWeatherSensorService()
        }
        if (wd != null) {
            db.save(wd)
            logger.info("Recorded WeatherData: ${wd.toString()}")
        } else {
            logger.warn("Could not read from WeatherSensors!")
        }
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

    private fun getLastSavedWeather(): WeatherData {
        return db.findAll().last()
    }
}