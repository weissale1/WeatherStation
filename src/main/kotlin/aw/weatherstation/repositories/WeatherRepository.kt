package aw.weatherstation.repositories;

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

import aw.weatherstation.domain.WeatherData

@Repository
interface WeatherRepository : CrudRepository<WeatherData, String> {

    @Query("SELECT * FROM weather_data :whereCondition", nativeQuery = true)
    fun queryWeatherData(@Param("whereCondition") whereCondition: String): List<WeatherData>
}
