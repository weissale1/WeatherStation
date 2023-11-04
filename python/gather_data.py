from datetime import datetime
import json

def get_sensor_data(timestamp):
    # TODO: Replace with sensor input
    wd = {
        "timestamp": timestamp.isoformat(timespec="seconds"),
        "temp": 20.0,
        "humid": 50.0
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

# get sensor data and save
timestamp = datetime.now()
path = f"./data/{timestamp.strftime('%Y-%m-%d')}.json"
save_data_to_file(path, get_sensor_data(timestamp))