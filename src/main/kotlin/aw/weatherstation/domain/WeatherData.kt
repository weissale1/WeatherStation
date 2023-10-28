package aw.weatherstation.domain

import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    val timestamp: String,
    val temp: Float,
    val humid: Float
)