package aw.weatherstation.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "WeatherData")
data class WeatherData(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @JsonIgnore var id: Long? = null,
    @Column(name = "timestamp_date") val timestamp: LocalDateTime,
    val temp: Float,
    val humid: Float
)