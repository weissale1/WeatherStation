package aw.weatherstation.controllers

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.services.CurrentWeatherService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(private val currentWeatherService: CurrentWeatherService) {

    @GetMapping("/current")
    fun currentWeather(): WeatherData {
        val wd: WeatherData = currentWeatherService.getCurrentWeather()
        return wd
    }

    @GetMapping("/query")
    fun queryWeather(): List<WeatherData> {
        val wds: List<WeatherData> = listOf()
        return wds
    }
}