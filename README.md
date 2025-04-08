# Kitchensink Java 21

A comprehensive web application demonstrating the integration of modern Java 21 features with Spring Boot, MongoDB, and Spring Security.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Security Configuration](#security-configuration)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Features

- ✅ User Registration and Authentication
- ✅ Member Management with Validation
- ✅ Secure Endpoints via Spring Security
- ✅ MongoDB Integration
- ✅ Server-side rendering with Thymeleaf
- ✅ Java 21 Records & Functional Style

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data MongoDB
- Thymeleaf
- MongoDB
- Maven

## Prerequisites

- **Java 21**
- **Maven 3.8+**
- **MongoDB (running locally on default port)**

## Setup and Installation

1. **Clone the Repository**

```bash
git clone https://github.com/jatinth96/kitchensink-java21.git
cd kitchensink-java21
```

2. **Configure MongoDB Connection (if needed)**

In `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/kitchensink
```

3. **Build the Application**

```bash
mvn clean install
```

4. **Run the Application**

```bash
mvn spring-boot:run
```

Visit [http://localhost:8080](http://localhost:8080) in your browser.

## Usage

- Register a user (visit `/register`)
- Login using your credentials
- Access `/members` to view and manage member records
- Logout via the logout button

## Security Configuration

- `/`, `/register`, `/login`, `/css/**` → Public routes
- All other routes → Require authentication
- Stateless form-based login
- Spring Security backed with custom `UserDetailsService`

## API Endpoints

| Method | Endpoint      | Description                       |
|--------|---------------|-----------------------------------|
| GET    | `/`           | Home page                         |
| GET    | `/register`   | Show registration form            |
| POST   | `/register`   | Register a new user               |
| GET    | `/login`      | Login form                        |
| POST   | `/login`      | Spring Security handles login     |
| GET    | `/members`    | List all members (secure)         |
| POST   | `/members`    | Add a member (secure)             |
| POST   | `/logout`     | Logout and redirect to login      |

## Testing

```bash
mvn test
```

> Note: MongoDB must be running locally for some integration tests.

## Contributing

1. Fork the repo
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License.