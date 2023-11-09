# WeatherStation

WeatherStation is a small server application for home temperature and humidity monitoring.

The server
- displays the current temperature and humidity via a HTML file
- collects temperature and humidity data every 5 minutes
- stores this data for future analytics

> This project is used as a playground to try and practice stuff.

## Running and using WeatherStation
### As JAR 
1. Download the latest release zip from `./releases`
2. Unpack the zip file to desired location. The Folder structure **must** be maintained as:
```
Folder
|-- WeatherStation_vX.X.X.jar
|-- python
|   └-- gather_data.py
└-- data [empty]
 ``` 
3. [Optional] Adjust the gather_data.py file. See comments in file.\
*This python file contains the code to gather environment data and save this data in the ./data folder.
The standard implementation uses a DHT22 sensor and assumes to run on a rasberry pi.*
4. Run the JAR file.
5. Go to `http\\localhost:8080` to see the output.

## Project Structure

### Class diagram

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
## Sequence diagram
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


