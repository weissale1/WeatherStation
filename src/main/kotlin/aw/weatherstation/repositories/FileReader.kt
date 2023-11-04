package aw.weatherstation.repositories

import aw.weatherstation.domain.WeatherData
import org.springframework.stereotype.Component
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDate

@Component
class FileReader: DataReader {
    override fun readData(): List<WeatherData>? {
        val filePath = "./data/${LocalDate.now()}.json"
        try {
            // Read the JSON content from the file
            val jsonContent = File(filePath).readText()

            // Use kotlinx.serialization to parse the JSON data into an object
            return Json.decodeFromString(jsonContent)
        } catch (e: FileNotFoundException) {
            println("File not found: $filePath")
        } catch (e: Exception) {
            println("Error reading JSON data: ${e.message}")
        }
        return null
    }
}