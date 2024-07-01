# Euphoria E-commerce

Euphoria E-commerce is a comprehensive e-commerce platform designed to provide a robust solution for managing products, user accounts, and interactions within an online shopping environment. Built using Java, Spring Boot, and PostgreSQL, it offers a range of features essential for a modern e-commerce application.

## Features

### Product Management
- **CRUD Operations**: Manage products including creation, retrieval, updating, and deletion.
- **Product Attributes**: Store details such as name, size, color, category, images, and pricing.
- **Price Management**: Track product prices over time with support for multiple currencies.

### User Management
- **Registration**: New users can register accounts with email verification.
- **Authentication**: Secure user authentication and authorization using JWT (JSON Web Tokens).
- **Password Recovery**: Enable users to reset passwords via email with OTP (One-Time Password) verification.

### Interaction and Engagement
- **Product Ratings**: Users can rate products on a scale of 0 to 10.
- **Recently Viewed Products**: Keep track of products users have recently viewed.
- **Wishlist**: Allow users to add products to their wishlist for future purchase consideration.

### Security and Authentication
- **JWT Token-Based Authentication**: Provides secure authentication mechanism using JWT tokens.
- **Role-Based Access Control**: Implement different levels of access based on user roles (admin, user).

### Additional Features
- **Email Notifications**: Integration with SMTP for sending registration and password recovery emails.
- **Audit Logging**: Track important actions and changes with auditing and logging features.
- **Responsive Design**: Designed with responsiveness in mind to ensure compatibility across devices.

## Getting Started

### Prerequisites

Ensure you have the following installed on your machine:

- Java 17
- Maven 3.8.5
- PostgreSQL

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/nihadamirov/Euphoria_Ecommerce.git
   cd Euphoria_Ecommerce
   ```

2. **Set up the database:**
    - Create a PostgreSQL database and update the configuration in `application.yml`:
      ```yaml
      spring:
        datasource:
          url: jdbc:postgresql://<database-url>:5432/<database-name>
          username: <database-username>
          password: <database-password>
      ```

3. **Configure email settings:**
    - Update SMTP settings in `application.yml` for sending emails:
      ```yaml
      mail:
        host: smtp.gmail.com
        port: 587
        username: <your-email>
        password: <your-email-password>
        properties:
          mail:
            smtp:
              auth: true
              starttls:
                enable: true
      ```

4. **Build and run the application:**
   ```bash
   mvn clean package -DskipTests
   java -jar target/Euphoria_Ecommerce-0.0.1-SNAPSHOT.jar
   ```

The application will start on `localhost:8080`.

### Docker

Alternatively, you can use Docker to run the application:

1. **Build Docker image:**
   ```bash
   docker build -t euphoria-ecommerce .
   ```

2. **Run Docker container:**
   ```bash
   docker run -p 8080:8080 euphoria-ecommerce
   ```

## API Documentation

The application provides RESTful API endpoints for interacting with various functionalities such as user management, product management, ratings, and wishlist. Explore the API documentation provided by Swagger UI:

- Swagger UI: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

## Security

Ensure to keep your `SECRET_KEY` for JWT token generation secure. It is defined in `application.yml`.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please feel free to fork the repository and submit pull requests.

