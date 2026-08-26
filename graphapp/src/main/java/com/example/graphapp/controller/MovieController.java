package com.example.graphapp.controller;

import com.example.graphapp.model.Movie;


import com.example.graphapp.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/test")
    public String testDatabaseConnection() {
        return movieService.testConnection();
    }

    @PostMapping("/movies")
    public String createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }
    @GetMapping("/movies")
    public String getAllMovies() {
        return movieService.getAllMovies();
    }
    @GetMapping("/movies/{id}")
    public String getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
    @DeleteMapping("/movies/{id}")
    public String deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id);
    }
    @PutMapping("/movies/{id}")
    public String updateMovie(
            @PathVariable String id,
            @RequestBody Movie movie) {

        movie.setId(id);

        return movieService.updateMovie(movie);
    }
    @PostMapping("/movies/{movieId}/actors/{actorId}")
    public String addActorToMovie(
            @PathVariable String movieId,
            @PathVariable String actorId) {

        return movieService.addActorToMovie(movieId, actorId);
    }
    @GetMapping("/users/{userId}/recommendations")
    public String getRecommendations(@PathVariable String userId) {
        return movieService.getRecommendations(userId);
    }
}