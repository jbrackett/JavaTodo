# JavaTodo

A simple Todo application that allows users to create, read, update, and delete todos.

## Technologies
- Java 21
- Spring Boot
- Spring Data JDBC
- FlywayDB
- PostgreSQL
- H2 Database
- Mockito
- JUnit 5
- Docker
- Testcontainers

## Architecture

The application is divided into two layers:
- Controller Layer: This layer is responsible for handling incoming HTTP requests and returning responses.
- Repository Layer: This layer is responsible for interacting with the database.

Typically, the service layer would be added between the controller and repository layers, but for this simple application, the service layer was omitted.

Other packages include:
- domain: Contains the Todo entity.
- config: Contains the configuration classes.
- exceptions: Contains the exception classes.

### Database
The application uses PostgreSQL for the production database and H2 for the test database. FlywayDB is used to manage the database schema.

## Running the application
```bash
./gradlew clean build
./run.sh
```

See the [Insomnia Collection](./dev/Todos_Insomnia.json) for collection to import.

View the [API Documentation](http://localhost:8099/swagger-ui/index.html).

