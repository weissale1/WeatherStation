package aw.weatherstation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class WeatherStationApplication

fun main(args: Array<String>) {
    runApplication<WeatherStationApplication>(*args)
}
