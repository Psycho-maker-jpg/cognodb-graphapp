package com.example.graphapp.repository;

import com.example.graphapp.model.Movie;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

	private final Driver driver;

	public MovieRepository(Driver driver) {
		this.driver = driver;
	}

	public String testConnection() {

		try (Session session = driver.session()) {

			return session.run("RETURN 'CognoDB Connected!' AS message").single().get("message").asString();

		} catch (Exception e) {
			return "Database connection failed: " + e.getMessage();
		}
	}

	public String createMovie(Movie movie) {

		try (Session session = driver.session()) {

			session.run(
				    "MERGE (m:Movie {id: $id}) " +
				    "SET m.title = $title, " +
				    "m.releaseYear = $releaseYear, " +
				    "m.description = $description",
				    org.neo4j.driver.Values.parameters(
				        "id", movie.getId(),
				        "title", movie.getTitle(),
				        "releaseYear", movie.getReleaseYear(),
				        "description", movie.getDescription()
				    )
				);

			return "Movie created successfully";

		} catch (Exception e) {
			return "Failed to create movie: " + e.getMessage();
		}
	}

	public String getAllMovies() {

		try (Session session = driver.session()) {

			return session.run("MATCH (m:Movie) " + "RETURN m.id AS id, " + "m.title AS title, "
					+ "m.releaseYear AS releaseYear, " + "m.description AS description").list().toString();

		} catch (Exception e) {
			return "Failed to get movies: " + e.getMessage();
		}
	}

	public String getMovieById(String id) {

		try (Session session = driver.session()) {

			return session.run(
					"MATCH (m:Movie {id: $id}) " + "RETURN m.id AS id, " + "m.title AS title, "
							+ "m.releaseYear AS releaseYear, " + "m.description AS description",
					org.neo4j.driver.Values.parameters("id", id)).list().toString();

		} catch (Exception e) {
			return "Failed to get movie: " + e.getMessage();
		}
	}
	public String deleteMovie(String id) {

	    try (Session session = driver.session()) {

	    	session.run(
	    		    "MATCH (m:Movie {id: $id}) DETACH DELETE m",
	    		    org.neo4j.driver.Values.parameters("id", id)
	    		);
	        return "Movie deleted successfully";

	    } catch (Exception e) {
	        return "Failed to delete movie: " + e.getMessage();
	    }
	}
	public String updateMovie(Movie movie) {

	    try (Session session = driver.session()) {

	        session.run(
	                "MATCH (m:Movie {id: $id}) " +
	                "SET m.title = $title, " +
	                "m.releaseYear = $releaseYear, " +
	                "m.description = $description",
	                org.neo4j.driver.Values.parameters(
	                        "id", movie.getId(),
	                        "title", movie.getTitle(),
	                        "releaseYear", movie.getReleaseYear(),
	                        "description", movie.getDescription()
	                )
	        );

	        return "Movie updated successfully";

	    } catch (Exception e) {
	        return "Failed to update movie: " + e.getMessage();
	    }
	}
	public String addActorToMovie(String movieId, String actorId) {

	    try (Session session = driver.session()) {

	        session.run(
	                "MATCH (m:Movie {id: $movieId}), " +
	                "(a:Actor {id: $actorId}) " +
	                "CREATE (a)-[:ACTED_IN]->(m)",
	                org.neo4j.driver.Values.parameters(
	                        "movieId", movieId,
	                        "actorId", actorId
	                )
	        );

	        return "Actor added to movie successfully";

	    } catch (Exception e) {
	        return "Failed to add actor to movie: " + e.getMessage();
	    }
	}
	public String getRecommendations(String userId) {

	    try (Session session = driver.session()) {

	        return session.run(
	                "MATCH (u:User {id: $userId})-[:WATCHED]->(m1:Movie) " +
	                "MATCH (a:Actor)-[:ACTED_IN]->(m1) " +
	                "MATCH (a)-[:ACTED_IN]->(m2:Movie) " +
	                "WHERE m1.id <> m2.id " +
	                "RETURN DISTINCT " +
	                "m2.id AS movieId, " +
	                "m2.title AS recommendedMovie, " +
	                "a.name AS commonActor",
	                org.neo4j.driver.Values.parameters("userId", userId)
	        ).list().toString();

	    } catch (Exception e) {
	        return "Failed to get recommendations: " + e.getMessage();
	    }
	}
}
