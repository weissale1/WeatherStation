package aw.weatherstation.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "WeatherData")
data class WeatherData(
    @Id @JsonIgnore var id: String?,
    val timestamp: String,
    val temp: Float,
    val humid: Float
)