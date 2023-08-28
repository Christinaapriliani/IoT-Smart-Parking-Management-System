# IoT Smart Parking Management System

This project implements an IoT-based Smart Parking Management System using Spring Boot and Kafka Streams.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Modules](#modules)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)



## Description

The IoT Smart Parking Management System is designed to manage parking slots, reservations, and provide real-time parking occupancy status using IoT devices and Kafka Streams for event processing.



## Features

- Manage parking slots and reservations.
- Real-time parking occupancy status updates.
- Kafka Streams for processing parking events.
- Spring Boot REST APIs for parking operations.



## Modules

The project is divided into two main modules:

1. **microservices/parking-service**: The main microservice for managing parking slots, reservations, and exposing REST APIs.
2. **kafka-streams**: Kafka Streams module for processing parking events and maintaining parking occupancy status.



## Installation

1. Clone this repository:

   ```bash
   git clone <repository_url>
   cd IoT-Smart-Parking-Management-System
   

## Usage
Start the Docker containers using docker-compose:

docker-compose up -d
Access the microservice APIs through http://localhost:8080/api/parking and Kafka Streams dashboard at http://localhost:8081.


## Contributing
Contributions are welcome! Please feel free to open issues or pull requests for any improvements or features you'd like to add.


##License
This project is licensed under the Christina License.


   
