package aw.weatherstation.connectors

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

import aw.weatherstation.domain.WeatherData

@Service
class WeatherSensorsConnector {
    val log: Logger = LoggerFactory.getLogger(WeatherSensorsConnector::class.java)
    val webClient = WebClient.builder()
        .baseUrl("http://sensors:5000")
        .build()

    suspend fun callWeatherSensorService(): WeatherData? {
        return try {
            webClient.get()
                .uri("/current")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .awaitBody<WeatherData>()
        } catch (ex: Exception) {
            log.warn("Could not read from WeatherSensors!")
            null
        }
    }
}