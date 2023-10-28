package aw.weatherstation.controllers

import aw.weatherstation.domain.WeatherData
import aw.weatherstation.services.WeatherService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/")
    fun deliverHtml(model: Model):String {
        val wd: WeatherData? = weatherService.calcCurrentWeather()
        if(wd != null) {
            model.addAttribute("temp", wd.temp)
            model.addAttribute("humid", wd.humid)
            model.addAttribute("timestamp", wd.timestamp)
        }
        return "current-weather"
    }

}