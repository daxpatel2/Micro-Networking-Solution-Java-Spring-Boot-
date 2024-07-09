# Microservice-Application

## Overview

This repository contains a microservices application built using Java Spring Boot. The application is structured into several microservices, each responsible for a specific domain. It utilizes Docker and Kubernetes for containerization and orchestration.

## Microservices

1. **CompanyMS**: Manages company-related operations.
2. **JobMS**: Handles job-related functionalities.
3. **ReviewMS**: Manages reviews and feedback.
4. **ServiceRegistry**: Acts as the service registry for the microservices.

## Getting Started

### Prerequisites

- Java 11
- Docker
- Kubernetes
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/daxpatel2/Microservice-Application.git
   cd Microservice-Application
   ```

2. Build the project using Maven:
   ```sh
   mvn clean install
   ```

3. Run Docker Compose to start the services:
   ```sh
   docker-compose up
   ```

## Usage

Access the services via the respective endpoints:
- CompanyMS: `http://localhost:8081`
- JobMS: `http://localhost:8082`
- ReviewMS: `http://localhost:8083`

## Contributing

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a pull request.

## License

This project is licensed under the MIT License.

---

For more details, visit the [repository](https://github.com/daxpatel2/Microservice-Application).
