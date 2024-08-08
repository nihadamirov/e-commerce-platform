# E-commerce Platform

Euphoria E-commerce is a comprehensive e-commerce platform designed to provide a seamless online shopping experience. The platform includes user authentication, product management, a shopping cart, order management, and payment integration using Stripe. Built with Java, Spring Boot, and PostgreSQL, it leverages Docker and Docker Compose for containerization.

## Table of Contents

1. [Features](#features)
2. [Installation](#installation)
3. [Configuration](#configuration)
4. [Usage](#usage)
5. [Testing](#testing)

## Features

- **Product Management**: CRUD operations for products, including attributes like name, size, color, category, and pricing. Multi-currency support and price management over time.
- **User Management**: User registration with email verification, secure authentication with JWT, and password recovery.
- **Shopping Cart**: Manage product selection and quantities, with the ability to add or remove items.
- **Order Management**: Full lifecycle management including order creation, tracking, and history.
- **Payment Integration**: Integrated with Stripe for handling payments.

## Installation

### Prerequisites

- Java 17 or later
- Maven 3.6 or later
- Docker
- Docker Compose

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/nihadamirov/Euphoria_Ecommerce.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Euphoria_Ecommerce
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Start the application using Docker Compose:
   ```bash
   docker-compose up
   ```

## Configuration

Update the `application.properties` file with your database and Stripe credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/euphoria
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

stripe.api.key=your_stripe_secret_key
jwt.SECRET_KEY=your_secret_key
```

## Usage

1. Open your browser and navigate to `http://localhost:8080` to access the application.
2. Register a new user and verify the email.
3. Log in and start exploring the product catalog.
4. Add products to your cart and proceed to checkout to complete a purchase.

## Testing

Run unit and integration tests using Maven:

```bash
mvn test
```

For more detailed test coverage and results, check the `target/site` directory.
