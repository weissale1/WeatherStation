package aw.weatherstation.services

import aw.weatherstation.domain.WeatherData
import org.springframework.stereotype.Service

import aw.weatherstation.repositories.WeatherRepository

@Service
class QueryWeatherService(
    private val db: WeatherRepository
) {
    fun query(params: Map<String, String>): List<WeatherData> {
        val whereCondition = constructWhereCon(params)
        return db.queryWeatherData(whereCondition)
    }

    private fun constructWhereCon(params: Map<String, String>): String {
        val conditions: String = params.entries.mapNotNull { (key, value) ->
            when (key) {
                "minTime" -> "timestamp_date >= '$value'"
                "maxTime" -> "timestamp_date <= '$value'"
                "minTemp" -> "temperature >= $value"
                "maxTemp" -> "temperature <= $value"
                "minHumid" -> "humidity >= $value"
                "maxHumid" -> "humidity <= $value"
                else -> null
            }
        }.joinToString(" AND ")

        return if (conditions.isNotEmpty()) {
            "WHERE $conditions"
        } else {
            "WHERE 1=1 LIMIT 1000"
        }
    }
}