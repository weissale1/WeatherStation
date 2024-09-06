package aw.weatherstation.services

import kotlinx.coroutines.runBlocking

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import aw.weatherstation.connectors.WeatherSensorsConnector
import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.WeatherRepository

@Service
class WeatherRecorderService(
    private val weatherSensorsConnector: WeatherSensorsConnector,
    private val db: WeatherRepository
) {
    val logger: Logger = LoggerFactory.getLogger(WeatherRecorderService::class.java)

    @Scheduled(fixedRate = 300000) // 300.000 milliseconds = 5 minutes
    fun recordWeatherData() {
        val wd: WeatherData? = runBlocking {
            weatherSensorsConnector.callWeatherSensorService()
        }
        if (wd != null) {
            db.save(wd)
            logger.info("Recorded WeatherData: ${wd.toString()}")
        }
    }
}