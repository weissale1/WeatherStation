package aw.weatherstation.controllers

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.services.WeatherService

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/")
    fun currentWeather(): WeatherData {
        val wd: WeatherData = weatherService.getCurrentWeather()
        return wd
    }
}