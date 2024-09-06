package aw.weatherstation.controllers

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.services.CurrentWeatherService
import aw.weatherstation.services.QueryWeatherService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(
    private val currentWeatherService: CurrentWeatherService,
    private val queryWeatherService: QueryWeatherService
) {

    @GetMapping("/current")
    fun currentWeather(): WeatherData {
        val wd: WeatherData = currentWeatherService.getCurrentWeather()
        return wd
    }

    @GetMapping("/query")
    fun queryWeather(
        @RequestParam params: Map<String, String>
    ): List<WeatherData> {
        val wds: List<WeatherData> = queryWeatherService.query(params)
        return wds
    }
}