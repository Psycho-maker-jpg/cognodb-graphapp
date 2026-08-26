package com.example.graphapp.service;

import com.example.graphapp.model.Movie;
import com.example.graphapp.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String testConnection() {
        return movieRepository.testConnection();
    }

    public String createMovie(Movie movie) {
        return movieRepository.createMovie(movie);
    }

    public String getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public String getMovieById(String id) {
        return movieRepository.getMovieById(id);
    }

    public String deleteMovie(String id) {
        return movieRepository.deleteMovie(id);
    }

    public String updateMovie(Movie movie) {
        return movieRepository.updateMovie(movie);
    }
    public String addActorToMovie(String movieId, String actorId) {
        return movieRepository.addActorToMovie(movieId, actorId);
    }
    public String getRecommendations(String userId) {
        return movieRepository.getRecommendations(userId);
    }
}