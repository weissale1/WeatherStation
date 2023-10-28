package aw.weatherstation.services

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.repositories.DataReader
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class WeatherService(@Qualifier("fileReader") private val dr: DataReader) {

    fun calcCurrentWeather(): WeatherData? {
        return dr.readData()?.last()
    }
}