# Food Ordering App - REST API

A backend REST API for a food ordering system built with Spring Boot. Supports restaurant & menu management, customer registration, and order placement with automatic total calculation.

## Tech Stack
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA + Hibernate
- H2 (in-memory, for local dev) / MySQL (production)
- Lombok
- JUnit 5 + Mockito
- Swagger / OpenAPI

## Features
- CRUD APIs for Restaurants and Menu Items
- Customer registration
- Order placement with automatic total calculation
- Order status tracking (PLACED → CONFIRMED → PREPARING → OUT_FOR_DELIVERY → DELIVERED)
- Global exception handling
- Unit tests for order service logic

## Project Structure
```
src/main/java/com/deepika/foodorder/
├── model/          # JPA entities
├── repository/      # Spring Data JPA repositories
├── service/         # Business logic
├── controller/       # REST endpoints
└── exception/        # Global exception handling
```

## How to Run
1. Clone the repo
2. `mvn clean install`
3. `mvn spring-boot:run`
4. App runs on `http://localhost:8080`
5. H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:fooddb`)
6. Swagger UI: `http://localhost:8080/swagger-ui.html`

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/restaurants` | Add a restaurant |
| GET | `/api/restaurants` | List all restaurants |
| GET | `/api/restaurants/{id}` | Get restaurant by id |
| POST | `/api/restaurants/{id}/menu` | Add menu item |
| GET | `/api/restaurants/{id}/menu` | Get menu of a restaurant |
| POST | `/api/customers` | Register customer |
| POST | `/api/orders/place?customerId=&restaurantId=` | Place an order |
| GET | `/api/orders/{id}` | Get order details |
| PUT | `/api/orders/{id}/status?status=` | Update order status |

## Next Steps / Improvements
- Add JWT-based authentication (Admin vs Customer roles)
- Dockerize the app
- Add payment gateway integration (mock)
- Add pagination & search filters on restaurant/menu listing
- Deploy on Render/Railway with MySQL

## Author
Deepika
