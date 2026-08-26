package com.example.graphapp.repository;

import com.example.graphapp.model.User;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Driver driver;

    public UserRepository(Driver driver) {
        this.driver = driver;
    }

    public String testConnection() {

        try (Session session = driver.session()) {

            return session.run(
                    "RETURN 'CognoDB Connected!' AS message"
            ).single().get("message").asString();

        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
    public String createUser(User user) {

        try (Session session = driver.session()) {

            session.run(
                    "CREATE (u:User {id: $id, name: $name, email: $email})",
                    org.neo4j.driver.Values.parameters(
                            "id", user.getId(),
                            "name", user.getName(),
                            "email", user.getEmail()
                    )
            );

            return "User created successfully";

        } catch (Exception e) {
            return "Failed to create user: " + e.getMessage();
        }
    }
    public String getAllUsers() {

        try (Session session = driver.session()) {

            return session.run(
                    "MATCH (u:User) " +
                    "RETURN u.id AS id, " +
                    "u.name AS name, " +
                    "u.email AS email"
            ).list().toString();

        } catch (Exception e) {
            return "Failed to get users: " + e.getMessage();
        }
    }
    public String getUserById(String id) {

        try (Session session = driver.session()) {

            return session.run(
                    "MATCH (u:User {id: $id}) " +
                    "RETURN u.id AS id, " +
                    "u.name AS name, " +
                    "u.email AS email",
                    org.neo4j.driver.Values.parameters("id", id)
            ).list().toString();

        } catch (Exception e) {
            return "Failed to get user: " + e.getMessage();
        }
    }
    public String updateUser(User user) {

        try (Session session = driver.session()) {

            session.run(
                    "MATCH (u:User {id: $id}) " +
                    "SET u.name = $name, u.email = $email",
                    org.neo4j.driver.Values.parameters(
                            "id", user.getId(),
                            "name", user.getName(),
                            "email", user.getEmail()
                    )
            );

            return "User updated successfully";

        } catch (Exception e) {
            return "Failed to update user: " + e.getMessage();
        }
    }

    public String deleteUser(String id) {

        try (Session session = driver.session()) {

            session.run(
                    "MATCH (u:User {id: $id}) DELETE u",
                    org.neo4j.driver.Values.parameters("id", id)
            );

            return "User deleted successfully";

        } catch (Exception e) {
            return "Failed to delete user: " + e.getMessage();
        }
    }
   
}