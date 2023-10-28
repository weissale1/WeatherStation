# WeatherStation

Work in Progress

## About
Work in progress

## Project Strucure

```mermaid
classDiagram
    classDiagram
    WeatherService ..> DataReader
    WeatherService ..> Model
    DataReader <|.. FileReader

    WeatherController ..> Model

    class WeatherService{
      -calcCurrentWeather(List~WeatherData~*) WeatherData
      -updateModel(WeatherData)
    }

    class WeatherController{
    }

    class DataReader{
        <<Interface>>
        +readData() List~WeatherData~*
    }

    class FileReader{
    }
```