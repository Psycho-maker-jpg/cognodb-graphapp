# CognoDB Graph Database Application

A Spring Boot REST API application using CognoDB as a graph database.

## Technologies

- Java
- Spring Boot
- CognoDB
- Neo4j Java Driver
- Cypher
- Maven
- Postman

## Features

### Movie APIs

- Create movie
- Get all movies
- Get movie by ID
- Update movie
- Delete movie

### User APIs

- Create user
- Get all users
- Get user by ID
- Update user
- Delete user

### Actor APIs

- Create actor
- Get all actors
- Get actor by ID
- Update actor
- Delete actor

### Graph Relationships

- User `WATCHED` Movie
- Actor `ACTED_IN` Movie

### Recommendation

The application provides movie recommendations based on actors shared with movies watched by a user.

## Example Recommendation

```text
User
  |
WATCHED
  |
Movie 1
  |
ACTED_IN
  |
Actor
  |
ACTED_IN
  |
Movie 2