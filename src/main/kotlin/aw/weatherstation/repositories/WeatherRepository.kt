package aw.weatherstation.repositories;

import aw.weatherstation.domain.WeatherData;
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WeatherRepository : CrudRepository<WeatherData, String>
