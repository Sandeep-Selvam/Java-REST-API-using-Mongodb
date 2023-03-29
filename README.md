# Java-REST-API-using-Mongodb

This java application is created with endpoints for searching, creating and deleting from a mongodb database.

## Dependencies

1. Lombok
2. Spring Web
3. Spring Data MongoDB
4. Spring Boot DevTools
5. Spring Boot Actuator

## Mongodb Configuration

### application.yml

This is a easiest way to configure with minimal details, so, I tried with .yml file

    spring:
        data:
            mongodb:
                host: 127.0.0.1
                database: Sample
                port: 27017

## RestapiApplication file

This is a Java program for a RESTful API developed using the Spring Boot framework. The code is contained in the "RestapiApplication" class, which is located in the package "com.myrestapi.restapi".

The "@SpringBootApplication" annotation is used to indicate that this is the main Spring Boot application class. It enables the automatic configuration of Spring Boot and scans the classpath for components, configurations, and services.

The "main" method is the entry point of the application. It invokes the static "run" method of the SpringApplication class, passing in the class object and the command line arguments.

When the application runs, Spring Boot will start an embedded web server and deploy the RESTful API. The API can then be accessed using HTTP requests, and the server will respond with the appropriate data.

Overall, this program provides a simple and efficient way to develop and deploy RESTful APIs using the Spring Boot framework.


## Model class for Server

This is a Java class called "Server" which represents a server entity for a RESTful API. It is annotated with "@Document" from the Spring Data MongoDB library which indicates that this class is mapped to a MongoDB collection.

The class also uses Lombok annotations to automatically generate getters, setters, and constructors, thereby reducing boilerplate code. The "@Data" annotation generates all the getter and setter methods, "@NoArgsConstructor" generates a no-argument constructor, and "@AllArgsConstructor" generates a constructor with all arguments.

The class has four private fields:

- "id" of type long: this field is annotated with "@Id" from the Spring Data MongoDB library which indicates that this field is the primary key for the MongoDB collection.

- "name" of type String: this field represents the name of the server.

- "language" of type String: this field represents the language used to develop the server.

- "framework" of type String: this field represents the framework used to develop the server.

The class also has getter and setter methods for each field which allows access to these fields outside the class.

Overall, this class provides a simple and efficient way to represent a server entity in a RESTful API using Spring Data MongoDB and Lombok annotations.

## Server Repository file

This is a Java interface called "ServerRepository" which extends the Spring Data MongoDB "MongoRepository" interface, providing CRUD (Create, Read, Update, Delete) operations for the "Server" entity.

The "MongoRepository" interface already provides basic CRUD operations such as "save", "findById", "findAll", and "delete", and this interface extends it to provide a custom operation called "findByNameIgnoreCase".

This custom operation takes a String parameter "name" and returns an Optional List of Server entities whose "name" field matches the given name in a case-insensitive manner.

By using Spring Data MongoDB, this interface provides a simple and efficient way to perform database operations on the "Server" entity. It allows the developer to write minimal code and focus on the business logic of the application.

## Server Controller file

This REST API provides basic CRUD (Create, Read, Update, Delete) functionality for managing server data. It uses Spring Boot and MongoDB for data persistence.

## Base URL 

The base URL for this REST API is: http://localhost:8080/Servers

## Endpoints

### GET /allservers

http://localhost:8080/Servers/allservers

Returns a list of all servers in the database.

Response

    Status Code: 200 OK
    Content Type: application/json

Response Body:

    [
    {
    {
        "id": 2,
        "name": "sandeep",
        "language": "Java",
        "framework": "Spring Integration"
    },
    {
        "id": 3,
        "name": "Sandeep",
        "language": "Ruby",
        "framework": "Next.js"
    },
    {
        "id": 5,
        "name": "Samuel",
        "language": "Go",
        "framework": "Pyramid"
    }
    ...
    ...

    ]
and so on......

### GET /serverbyId

http://localhost:8080/Servers/serverbyId?id=5

Returns the server with the specified ID.

Request Parameters

    id: 5

Response

    Status Code: 200 OK
    Content Type: application/json

Response Body:

    {
    "id": 5,
    "name": "Samuel",
    "language": "Go",
    "framework": "Pyramid"
    }

### GET /serverbyName

http://localhost:8080/Servers/serverbyName?name=sandeep

Returns a list of servers with the specified name (case-insensitive).

Request Parameters

    name: sandeep

Response

    Status Code: 200 OK
    Content Type: application/json

Response Body:

    [
    {
        "id": 2,
        "name": "sandeep",
        "language": "Java",
        "framework": "Spring Integration"
    },
    {
        "id": 3,
        "name": "Sandeep",
        "language": "Ruby",
        "framework": "Next.js"
    }
    ]

### POST /addserver

http://localhost:8080/Servers/addserver

Adds a new server to the database.

Request Body

    {
        "id": 11,
        "name": "Paul",
        "language": "PHP",
        "framework": "Cypress"
    }

Response

    Status Code: 200 OK

Response Body:

     Server is added successfully

### PUT /updateserver/{id}

http://localhost:8080/Servers/updateserver/6

Updates the server with the specified ID.

Path Parameters

    http://localhost:8080/Servers/updateserver/6

Request Body

    name: The updated name of the server.
    language: The updated programming language used by the server.
    framework: The updated framework used by the server.
Response

    Status Code: 200 OK
    Content Type: application/json
Response Body:

    {
    "id": 6,
    "name": "Sam",
    "language": "C++",
    "framework": "jQuery Mobile"
    }

### DELETE /

http://localhost:8080/Servers/?id=7

Deletes the server with the specified ID.

Request Parameters

    id: The ID of the server to delete.

Response
    
    Status Code: 200 OK

Response Body: 

    Server is deleted successfully

Error Responses

If an error occurs while processing a request, the server will return an error response in the following format:


    {
        "status": 404,
        "message": "Server not found"
    }

## Exception file

This code contains a custom exception called 
ServerNotFoundException. It extends the RuntimeException class and is used to throw an exception when a requested server is not found in the database.

The class has a constructor that takes a message as a parameter and calls the constructor of the parent RuntimeException class with the message parameter. This message will be displayed to the user when the exception is thrown.

In summary, this code provides a reusable exception class that can be used to handle cases when a server is not found in the database.

## Global Exception Handle file

This code defines a global exception handler for the Spring REST API. It uses the @ControllerAdvice annotation to define a class that will handle exceptions thrown by controllers across the application. The @ExceptionHandler annotation is used to define a method that will handle the ServerNotFoundException exception, which is a custom exception class defined in another file.

The ServerNotFoundExceptionHandler method takes an instance of the ServerNotFoundException class as a parameter and returns a ResponseEntity object. This object contains information about the HTTP response, including the status code and any response body data. In this case, it returns a JSON object that contains the status code 404 and an error message that describes the exception that was thrown.

The @ResponseStatus annotation is used to specify the HTTP response status code that will be returned when the exception is thrown. In this case, it sets the status code to 404 Not Found.

Overall, this code provides a centralized way to handle exceptions that may be thrown by any controller in the Spring REST API. It ensures that a consistent error response is returned to the client in the event of an exception, making it easier to debug and troubleshoot issues in the application.

## Screenshot

## Get all server
![Get all server](https://user-images.githubusercontent.com/66906785/228660178-0dd73822-d92a-4ac6-b662-a7346d5add49.png)


## Get by ID
![Get by ID](https://user-images.githubusercontent.com/66906785/228660304-2182accf-9bfa-425d-9060-269a9e81acc8.png)


## Get by name
![Get by name](https://user-images.githubusercontent.com/66906785/228660363-69c3b85c-48e4-4135-aef1-029bd7496e79.png)


## Post (add new)
![Post (adding new)](https://user-images.githubusercontent.com/66906785/228660482-c434a21d-0b91-4b3e-ac45-5104c3be0fe5.png)


## Put (update exixt)
![Put (update)](https://user-images.githubusercontent.com/66906785/228660585-dd8a1ddc-6946-41d1-b6a7-1a4f3ab3c627.png)


## Delete
![Delete](https://user-images.githubusercontent.com/66906785/228660645-6586e8a0-e504-4874-9e21-db6190f8dcf6.png)


# Thank You
