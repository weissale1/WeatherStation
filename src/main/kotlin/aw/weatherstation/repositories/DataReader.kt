package aw.weatherstation.repositories

import aw.weatherstation.domain.WeatherData
import org.springframework.stereotype.Component

@Component
interface DataReader {
    fun readData(): List<WeatherData>?
}