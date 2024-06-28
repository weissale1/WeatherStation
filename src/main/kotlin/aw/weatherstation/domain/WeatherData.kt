package aw.weatherstation.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "WeatherData")
data class WeatherData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore var id: Long? = null,
    val timestamp: String,
    val temp: Float,
    val humid: Float
)