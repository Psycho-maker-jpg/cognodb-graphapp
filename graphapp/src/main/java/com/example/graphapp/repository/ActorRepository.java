package com.example.graphapp.repository;

import com.example.graphapp.model.Actor;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Repository;

@Repository
public class ActorRepository {

    private final Driver driver;

    public ActorRepository(Driver driver) {
        this.driver = driver;
    }

    public String createActor(Actor actor) {

        try (Session session = driver.session()) {

            session.run(
                    "CREATE (a:Actor {id: $id, name: $name})",
                    org.neo4j.driver.Values.parameters(
                            "id", actor.getId(),
                            "name", actor.getName()
                    )
            );

            return "Actor created successfully";

        } catch (Exception e) {
            return "Failed to create actor: " + e.getMessage();
        }
    }

    public String getAllActors() {

        try (Session session = driver.session()) {

            return session.run(
                    "MATCH (a:Actor) " +
                    "RETURN a.id AS id, a.name AS name"
            ).list().toString();

        } catch (Exception e) {
            return "Failed to get actors: " + e.getMessage();
        }
    }

    public String getActorById(String id) {

        try (Session session = driver.session()) {

            return session.run(
                    "MATCH (a:Actor {id: $id}) " +
                    "RETURN a.id AS id, a.name AS name",
                    org.neo4j.driver.Values.parameters("id", id)
            ).list().toString();

        } catch (Exception e) {
            return "Failed to get actor: " + e.getMessage();
        }
    }

    public String updateActor(Actor actor) {

        try (Session session = driver.session()) {

            session.run(
                    "MATCH (a:Actor {id: $id}) " +
                    "SET a.name = $name",
                    org.neo4j.driver.Values.parameters(
                            "id", actor.getId(),
                            "name", actor.getName()
                    )
            );

            return "Actor updated successfully";

        } catch (Exception e) {
            return "Failed to update actor: " + e.getMessage();
        }
    }

    public String deleteActor(String id) {

        try (Session session = driver.session()) {

            session.run(
                    "MATCH (a:Actor {id: $id}) DETACH DELETE a",
                    org.neo4j.driver.Values.parameters("id", id)
            );

            return "Actor deleted successfully";

        } catch (Exception e) {
            return "Failed to delete actor: " + e.getMessage();
        }
    }
}