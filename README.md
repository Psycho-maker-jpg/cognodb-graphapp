# CognoDB Graph Database Application

A Spring Boot REST API for managing **users, movies, and actors** using a graph database powered by **CognoDB** and accessed through the Neo4j Java Driver.

The application demonstrates graph-based relationships such as users watching movies and actors appearing in movies, along with a recommendation query based on shared actors.

---

## Overview

This project was developed as a Full Stack Developer assessment project for the **CognoDB** use case.

The backend provides REST APIs to:

* Manage users
* Manage movies
* Manage actors
* Create relationships between actors and movies
* Test the CognoDB database connection
* Generate movie recommendations based on actors shared with movies watched by a user

The application uses **Cypher queries** to create and query graph nodes and relationships.

---

## Tech Stack

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java 25           | Backend programming language    |
| Spring Boot 4.1.1 | REST API framework              |
| Maven             | Build and dependency management |
| CognoDB           | Graph database                  |
| Neo4j Java Driver | Database connectivity           |
| Cypher            | Graph query language            |
| Postman           | API testing                     |

---

## Architecture

The application follows a simple layered architecture:

```text
Client / Postman
       |
       v
REST Controllers
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
Neo4j Java Driver
       |
       v
CognoDB Graph Database
```

### Project Structure

```text
src/
├── main/
│   ├── java/com/example/graphapp/
│   │   ├── config/
│   │   │   └── CognoDBConfig.java
│   │   │
│   │   ├── controller/
│   │   │   ├── ActorController.java
│   │   │   ├── MovieController.java
│   │   │   └── UserController.java
│   │   │
│   │   ├── model/
│   │   │   ├── Actor.java
│   │   │   ├── Movie.java
│   │   │   └── User.java
│   │   │
│   │   ├── repository/
│   │   │   ├── ActorRepository.java
│   │   │   ├── MovieRepository.java
│   │   │   └── UserRepository.java
│   │   │
│   │   ├── service/
│   │   │   ├── ActorService.java
│   │   │   ├── MovieService.java
│   │   │   └── UserService.java
│   │   │
│   │   └── GraphappApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/com/example/graphapp/
        └── GraphappApplicationTests.java
```

---

## Features

### User Management

The application supports CRUD operations for users.

A user contains:

```json
{
  "id": "u1",
  "name": "John Doe",
  "email": "john@example.com"
}
```

Available operations:

* Create user
* Get all users
* Get user by ID
* Update user
* Delete user
* Test database connection

---

### Movie Management

Movies contain:

```json
{
  "id": "m1",
  "title": "Inception",
  "releaseYear": 2010,
  "description": "A science-fiction thriller."
}
```

Available operations:

* Create movie
* Get all movies
* Get movie by ID
* Update movie
* Delete movie
* Add an actor to a movie
* Get movie recommendations

---

### Actor Management

Actors contain:

```json
{
  "id": "a1",
  "name": "Leonardo DiCaprio"
}
```

Available operations:

* Create actor
* Get all actors
* Get actor by ID
* Update actor
* Delete actor

---

## Graph Model

The application represents data as graph nodes and relationships.

### Nodes

```text
(User)
(Movie)
(Actor)
```

### Relationships

```text
(User)-[:WATCHED]->(Movie)

(Actor)-[:ACTED_IN]->(Movie)
```

The resulting graph can be represented as:

```text
          WATCHED
(User) -------------> (Movie)
                         ^
                         |
                      ACTED_IN
                         |
                      (Actor)
```

---

## Recommendation Logic

The recommendation endpoint uses the graph relationship between users, movies, and actors.

The query follows this path:

```text
User
 |
 | WATCHED
 v
Movie 1
 |
 | ACTED_IN
 v
Actor
 |
 | ACTED_IN
 v
Movie 2
```

If a user has watched a movie featuring an actor who also appears in another movie, that other movie can be returned as a recommendation.

The recommendation query returns:

* Recommended movie ID
* Recommended movie title
* Common actor

Recommendations are generated directly using a Cypher graph traversal.

---

# API Documentation

The default application URL is:

```text
http://localhost:8080
```

---

## Connection Test

### Test CognoDB Connection

```http
GET /api/test
```

Example:

```bash
curl http://localhost:8080/api/test
```

Expected response:

```text
CognoDB Connected!
```

A user connection test is also available:

```http
GET /users/test
```

---

# User APIs

## Create User

```http
POST /users
```

Request body:

```json
{
  "id": "u1",
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## Get All Users

```http
GET /users
```

---

## Get User By ID

```http
GET /users/{id}
```

Example:

```http
GET /users/u1
```

---

## Update User

```http
PUT /users/{id}
```

Example:

```http
PUT /users/u1
```

Request body:

```json
{
  "name": "John Updated",
  "email": "john.updated@example.com"
}
```

---

## Delete User

```http
DELETE /users/{id}
```

Example:

```http
DELETE /users/u1
```

---

# Movie APIs

## Create Movie

```http
POST /movies
```

Request body:

```json
{
  "id": "m1",
  "title": "Inception",
  "releaseYear": 2010,
  "description": "A science-fiction thriller."
}
```

---

## Get All Movies

```http
GET /movies
```

---

## Get Movie By ID

```http
GET /movies/{id}
```

Example:

```http
GET /movies/m1
```

---

## Update Movie

```http
PUT /movies/{id}
```

Example:

```http
PUT /movies/m1
```

Request body:

```json
{
  "title": "Inception Updated",
  "releaseYear": 2010,
  "description": "Updated movie description."
}
```

---

## Delete Movie

```http
DELETE /movies/{id}
```

Example:

```http
DELETE /movies/m1
```

---

## Add Actor To Movie

Creates an `ACTED_IN` relationship between an actor and a movie.

```http
POST /movies/{movieId}/actors/{actorId}
```

Example:

```http
POST /movies/m1/actors/a1
```

This creates:

```text
(Actor a1)-[:ACTED_IN]->(Movie m1)
```

---

## Get Movie Recommendations

```http
GET /users/{userId}/recommendations
```

Example:

```http
GET /users/u1/recommendations
```

The query finds movies connected through actors who appeared in movies already watched by the specified user.

---

# Actor APIs

## Create Actor

```http
POST /actors
```

Request body:

```json
{
  "id": "a1",
  "name": "Leonardo DiCaprio"
}
```

---

## Get All Actors

```http
GET /actors
```

---

## Get Actor By ID

```http
GET /actors/{id}
```

Example:

```http
GET /actors/a1
```

---

## Update Actor

```http
PUT /actors/{id}
```

Example:

```http
PUT /actors/a1
```

Request body:

```json
{
  "name": "Leonardo DiCaprio"
}
```

---

## Delete Actor

```http
DELETE /actors/{id}
```

Example:

```http
DELETE /actors/a1
```

---

# Configuration

Database configuration is supplied through environment variables rather than hardcoded credentials.

The application expects:

```text
COGNODB_URI
COGNODB_USERNAME
COGNODB_PASSWORD
```

The relevant Spring configuration is:

```properties
cognodb.uri=${COGNODB_URI}
cognodb.username=${COGNODB_USERNAME:cognodb}
cognodb.password=${COGNODB_PASSWORD}
```

## Environment Variables

Before starting the application, configure:

```bash
COGNODB_URI=<your-cognodb-uri>
COGNODB_USERNAME=<your-cognodb-username>
COGNODB_PASSWORD=<your-cognodb-password>
```

**Do not commit real database credentials, passwords, API keys, or other secrets to GitHub.**

---

# Running the Application

## Prerequisites

Make sure the following are installed:

* Java 25
* Maven, or use the included Maven Wrapper
* Access to a CognoDB instance
* Git

---

## 1. Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd graphapp
```

---

## 2. Configure CognoDB

Set the required environment variables.

### Windows PowerShell

```powershell
$env:COGNODB_URI="<your-cognodb-uri>"
$env:COGNODB_USERNAME="<your-cognodb-username>"
$env:COGNODB_PASSWORD="<your-cognodb-password>"
```

### Linux / macOS

```bash
export COGNODB_URI="<your-cognodb-uri>"
export COGNODB_USERNAME="<your-cognodb-username>"
export COGNODB_PASSWORD="<your-cognodb-password>"
```

---

## 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or using the Maven Wrapper:

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

---

# Testing

The project includes a Spring Boot application test under:

```text
src/test/java/com/example/graphapp/GraphappApplicationTests.java
```

Run the test suite with:

```bash
mvn test
```

Or:

```bash
./mvnw test
```

---

# Example Workflow

A typical workflow for creating and testing the graph is:

### 1. Create a user

```http
POST /users
```

```json
{
  "id": "u1",
  "name": "John Doe",
  "email": "john@example.com"
}
```

### 2. Create movies

```http
POST /movies
```

### 3. Create actors

```http
POST /actors
```

### 4. Connect an actor to a movie

```http
POST /movies/m1/actors/a1
```

This creates:

```text
(Actor a1)-[:ACTED_IN]->(Movie m1)
```

### 5. Create the `WATCHED` relationship

The recommendation logic expects a user-to-movie:

```text
(User)-[:WATCHED]->(Movie)
```

relationship in the CognoDB graph.

### 6. Request recommendations

```http
GET /users/u1/recommendations
```

The API then searches for other movies featuring actors from movies watched by the user.

---

# Error Handling

The repository layer catches database exceptions and returns descriptive messages such as:

```text
Failed to create movie: <error>
```

or:

```text
Database connection failed: <error>
```

This provides basic visibility into database and query failures during development and testing.

---

# Design Decisions

### Graph Database

CognoDB was used because the application's core data is relationship-oriented. Users, movies, and actors naturally form a graph, making relationship traversal suitable for recommendation queries.

### Cypher Queries

Cypher is used directly with the Neo4j Java Driver to create nodes, update data, create relationships, and perform graph traversals.

### Layered Structure

The application separates responsibilities into:

* **Controller** — exposes REST endpoints
* **Service** — handles application-level operations
* **Repository** — communicates with CognoDB
* **Model** — represents application entities
* **Configuration** — creates the database driver

This keeps the database access code separate from the REST API layer.

---

# Future Improvements

Potential improvements for a production-ready version include:

* Return structured JSON responses instead of string representations
* Add proper HTTP status codes using `ResponseEntity`
* Add request validation using Bean Validation
* Add centralized exception handling with `@ControllerAdvice`
* Add pagination for movie, actor, and user listing endpoints
* Prevent duplicate graph relationships
* Add an endpoint for explicitly creating `WATCHED` relationships
* Add authentication and authorization
* Add integration tests against CognoDB
* Add API documentation using OpenAPI / Swagger
* Add Docker support for easier deployment
* Add frontend UI for interacting with the graph data

---

# Security

Database credentials are supplied through environment variables and should never be committed to source control.

Before publishing the repository, verify that:

* No `.env` files containing secrets are committed
* No passwords or API keys are present in source code
* No private credentials appear in Git history
* `target/` and generated build files are excluded from Git

---

# Author

**Athram Mahesh**

Full Stack Developer Intern Assessment — CognoDB

---

## License

This project was created as part of a technical assessment.
