# EventHub API – Assignment 1

Spring Boot REST API for managing events and categories with filtering, pagination, caching, and Swagger documentation.

---

# Project Description

EventHub API is a Spring Boot REST application designed to manage events and categories. The API provides endpoints for creating, retrieving, updating, and deleting events while supporting advanced features such as filtering, pagination, caching, and API documentation.

The application follows a layered architecture separating responsibilities into controllers, services, repositories, and DTOs.

## Key Features

- CRUD operations for events
- Category management
- Filtering by category, price, and date
- Pagination of results
- Sorting of events
- Request validation using Jakarta Validation
- Global exception handling
- Swagger / OpenAPI documentation
- CORS configuration
- Caffeine caching
- Automatic test data loading at application startup

## Technologies Used

- Java
- Spring Boot
- Maven
- Swagger / OpenAPI
- Caffeine Cache
- Postman (API testing)

---

# Setup Instructions

# Setup Instructions

## Option 1 – Clone from GitHub

Clone the repository from GitHub:

git clone https://github.com/Jonathan-legault/eventhub-api-lab1.git

Navigate to the project folder:

cd eventhub-api

---

## Option 2 – Download ZIP

Download the project ZIP file from the course portal or GitHub and extract it.

Navigate to the extracted folder:

cd eventhub-api

## 2. Build the Project

mvn clean install

## 3. Run the Application

mvn spring-boot:run

Alternatively run the **EventHubApplication** class directly from IntelliJ.

The application will start at:

http://localhost:8080

## 4. Access Swagger Documentation

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

Swagger provides interactive documentation and allows testing API endpoints directly from the browser.

---

# API Documentation

## Base URL

http://localhost:8080/api/v1

---

# Event Endpoints

## Create Event

POST /events

Example request body:

{
"name": "AI Developer Summit",
"description": "Artificial intelligence conference for developers.",
"ticketPrice": 220,
"category": "Conference",
"active": true,
"eventDate": "2026-09-15T09:00:00"
}

---

## Get All Events

GET /events

Supports pagination:

GET /events?page=0&size=10

---

## Get Event by ID

GET /events/{id}

Example:

GET /events/1

---

## Update Event

PUT /events/{id}

Example request:

{
"name": "Updated Event",
"description": "Updated description",
"ticketPrice": 180,
"category": "Conference",
"active": true,
"eventDate": "2026-06-01T10:00:00"
}

---

## Delete Event

DELETE /events/{id}

---

# Filtering Events

Filter by category:

GET /events?category=Workshop

Filter by price range:

GET /events?minPrice=50&maxPrice=200

Filter by date:

GET /events?startDate=2026-05-01T00:00:00

---

# Pagination

GET /events?page=1&size=3

Returns the second page of results with three events per page.

---

# Category Endpoints

## Get All Categories

GET /categories

## Create Category

POST /categories

Example request:

{
"name": "Conference"
}

---

# Additional Features

Validation ensures incoming requests contain valid data.

Global exception handling provides structured error responses.

CORS configuration allows external applications to access the API.

Caffeine caching improves performance by caching frequently requested data.

Startup data loader automatically inserts sample events and categories when the application starts.

---

# Testing

The API was tested using:

- Postman for endpoint testing
- Swagger UI for interactive API testing

---

# Conclusion

The EventHub API implements a structured Spring Boot REST service for managing events and categories. The application demonstrates layered architecture, validation, filtering, pagination, caching, and API documentation.

This project provides a strong foundation for a scalable event management platform.