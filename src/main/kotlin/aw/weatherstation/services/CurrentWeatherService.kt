package aw.weatherstation.services

import kotlinx.coroutines.runBlocking

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import aw.weatherstation.connectors.WeatherSensorsConnector
import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.WeatherRepository

@Service
class CurrentWeatherService(
    private val weatherSensorsConnector: WeatherSensorsConnector,
    private val db: WeatherRepository
) {
    val logger: Logger = LoggerFactory.getLogger(CurrentWeatherService::class.java)

    fun getCurrentWeather(): WeatherData {
        val wd: WeatherData? = runBlocking {
            weatherSensorsConnector.callWeatherSensorService()
        }
        if (wd != null) {
            logger.info("Successfully measured WeatherData: ${wd.toString()}")
            return wd
        } else {
            logger.warn("Returning last saved data from db.")
            return getLastSavedWeather()
        }
    }

    private fun getLastSavedWeather(): WeatherData {
        return db.findAll().last()
    }
}