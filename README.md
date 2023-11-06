# WeatherStation

Work in Progress

## About
Work in progress

## Project Strucure

```mermaid
classDiagram
    WeatherController ..> Model
    WeatherController ..> WeatherService
    WeatherService ..> DataReader
    DataReader <|.. FileReader

    class WeatherController{
        +deliverHtml
    }

    class DataGatherService {
    }

    class WeatherService{
        +calcCurrentWeather(List~WeatherData~*) WeatherData
    }

    class DataReader{
        <<Interface>>
        +readData() List~WeatherData~*
    }

    class FileReader{
    }
```
## Control Flow
```mermaid
sequenceDiagram
actor User

User ->> WeatherController: GET 
activate WeatherController
WeatherController->>WeatherService: calcCurrentWeather()
activate WeatherService
WeatherService->>DataReader: readData()
activate DataReader
DataReader->>DATA_PERSISTENCE: read data from persistence
DATA_PERSISTENCE-->>DataReader: data
DataReader->>WeatherService: return data
deactivate DataReader
Note over WeatherService: Run calculation

WeatherService->>WeatherController: return data
deactivate WeatherService
WeatherController->>User: send HTML
deactivate WeatherController 


loop Every 5 minutes
activate DataGatherService
    DataGatherService->>gather_data.py: runPythonScript()
    activate gather_data.py
    gather_data.py->>DATA_PERSISTENCE: save_data_to_file()
    gather_data.py-->>DataGatherService: send log/errors
    deactivate gather_data.py
    deactivate DataGatherService
end



```


