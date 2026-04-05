# RideApp

A comprehensive ride booking application built with Spring Boot, designed to facilitate seamless ride requests, driver matching, and real-time tracking.

## Features

- **User Management**: Registration and authentication for riders and drivers using JWT.
- **Ride Booking**: Request rides with location-based services using Hibernate Spatial.
- **Driver Matching**: Intelligent matching strategies for optimal ride assignments.
- **Real-time Tracking**: Integration with Zipkin for distributed tracing.
- **Logging**: ELK-based logging for centralized log management and analysis.
- **Caching**: Redis-based caching for improved performance.
- **API Documentation**: Swagger/OpenAPI integration for easy API exploration.
- **Email Notifications**: SMTP support for sending ride confirmations and updates.
- **Security**: Spring Security with JWT for secure endpoints.
- **Monitoring**: Spring Boot Actuator for health checks and metrics.

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.5.7
- **Database**: PostgreSQL with Hibernate Spatial for geospatial data
- **Caching**: Redis
- **Tracing**: Zipkin with Micrometer
- **Logging**: Logback with ELK stack integration
- **Authentication**: JWT (JSON Web Tokens)
- **API**: RESTful APIs with Spring Web
- **Documentation**: SpringDoc OpenAPI
- **Testing**: JUnit with Testcontainers
- **Build Tool**: Maven

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- PostgreSQL database
- Redis server
- Zipkin server (for tracing)
- ELK stack (Elasticsearch, Logstash, Kibana) for logging

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd rideApp
   ```

2. **Configure Environment Variables**:
   Create a `local.env` file or set environment variables for:
   - `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
   - `JWT_SECRET_KEY`
   - `REDIS_HOST`, `REDIS_PORT`, `REDIS_PASSWORD`

   Example `local.env`:
   ```
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=rideapp
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   JWT_SECRET_KEY=your_secret_key
   REDIS_HOST=localhost
   REDIS_PORT=6379
   REDIS_PASSWORD=your_redis_password
   ```

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR:
   ```bash
   java -jar target/rideApp-0.0.1-SNAPSHOT.jar
   ```

## Usage

- **API Base URL**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Actuator Endpoints**: `http://localhost:8080/actuator`

### Sample API Endpoints

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and get JWT token
- `POST /api/rides` - Request a ride
- `GET /api/rides/{id}` - Get ride details

## Configuration Profiles

- **dev**: Development profile with detailed logging
- **prod**: Production profile with optimized settings

Run with a specific profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Logging and Monitoring

- Logs are written to `logs/rideApp/` with rolling file appender.
- For ELK integration, configure Logstash to ingest log files from the logs directory.
- Zipkin traces can be viewed at `http://localhost:9411`.

## Contributing

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/AmazingFeature`).
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
4. Push to the branch (`git push origin feature/AmazingFeature`).
5. Open a Pull Request.
