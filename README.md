# CognoDB Graph Database Application

A Spring Boot application that uses **CognoDB**, a graph database, to store and query movie and user information using **openCypher**.

## Technologies Used

* Java 25
* Spring Boot
* Maven
* CognoDB Graph Database
* openCypher
* Bolt Protocol
* Eclipse IDE

## Project Structure

```text
graphapp/
├── src/
│   └── main/
│       ├── java/
│       └── resources/
├── pom.xml
├── README.md
└── .gitignore
```

## Features

* Connects Spring Boot with CognoDB
* Creates and stores movie data
* Retrieves movie information
* Supports movie recommendations using graph relationships
* Uses Cypher queries to work with graph data
* Provides REST API endpoints

## Configuration

The application uses environment variables for database credentials.

Create a `.env` file locally with:

```text
COGNODB_URI=your_cognodb_uri
COGNODB_USERNAME=your_username
COGNODB_PASSWORD=your_password
```

**Do not upload the `.env` file or database credentials to GitHub.**

## Running the Application

1. Clone the repository.
2. Open the project in Eclipse or another Java IDE.
3. Configure the required environment variables.
4. Make sure Maven dependencies are downloaded.
5. Run the Spring Boot application.
6. Test the REST APIs using Postman or a web browser.

## Example API

### Create a Movie

```text
POST /movies
```

Example request:

```json
{
  "movieId": "M001",
  "title": "Baahubali"
}
```

### Test Connection

```text
GET /api/test
```

## Graph Database

The application uses CognoDB to represent relationships between movies, actors, and recommendations.

Example Cypher query:

```cypher
MATCH (m:Movie)
RETURN m
```

## Movie Recommendation Example

The graph can be queried to find recommended movies based on common actors or relationships.

Example result:

```text
movieId: M002
recommendedMovie: Salaar
commonActor: Prabhas
```

## Security

Sensitive credentials are stored using environment variables and are excluded from Git using `.gitignore`.

## Author

Psycho-maker-jpg
