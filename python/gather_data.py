import adafruit_dht # pip3 install adafruit-circuitpython-dht
import board
import json
import time

from datetime import datetime
from statistics import mean

def get_sensor_data(dhtDevice, no_of_measurements_needed):
    raw_data = []
    while len(raw_data) < no_of_measurements_needed:
        try:
            temp_c = dhtDevice.temperature
            humidity = dhtDevice.humidity
            raw_data.append((temp_c, humidity))
        except RuntimeError:
            # Errors reading from DHT22 happen regularly. Simply retry.
            time.sleep(2.0)
            continue
    return raw_data

def format_data(timestamp, raw_data):
    temps = []
    humids = []
    for d in raw_data:
        temps.append(d[0])
        humids.append(d[1])
    wd = {
        "timestamp": timestamp.isoformat(timespec="seconds"),
        "temp": mean(temps),
        "humid": mean(humids)
    }
    return wd

def save_data_to_file(file_path, wd):
    try:
        # Read the existing data from JSON file
        with open(file_path, 'r') as file:
            data = json.load(file)
    except FileNotFoundError:
        # If the file doesn't exist, create an empty list
        data = []

    # Add a new element to the list
    data.append(wd)

    # Save the updated list back to the JSON file
    with open(file_path, 'w') as file:
        json.dump(data, file, indent=4)

# get sensor data
dhtDevice = adafruit_dht.DHT22(board.D4, use_pulseio=False)
no_of_measurements_needed = 5
raw_data = get_sensor_data(dhtDevice, no_of_measurements_needed)
dhtDevice.exit()

# format and save data
timestamp = datetime.now()
path = f"../data/{timestamp.strftime('%Y-%m-%d')}.json"
save_data_to_file(path, format_data(timestamp, raw_data))
