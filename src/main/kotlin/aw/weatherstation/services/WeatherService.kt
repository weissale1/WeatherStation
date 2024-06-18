package aw.weatherstation.services

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.WeatherRepository
import org.springframework.stereotype.Service

@Service
class WeatherService(private val db: WeatherRepository) {

    fun getCurrentWeather(): WeatherData {
        return db.findAll().last()
    }

}