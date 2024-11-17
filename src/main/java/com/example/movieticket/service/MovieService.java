package com.example.movieticket.service;

import com.example.movieticket.model.Movie;
import com.example.movieticket.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    // Retrieve all products from the database
    public List<Movie> getAllMovies() {return movieRepository.findAll();}
    //Retrieve a product by its id
    public Optional<Movie> getMovieById(Long id) {return movieRepository.findById(id);}
    // Add a new product to the database
    public Movie addMovie(Movie movie) {return movieRepository.save(movie);}
    // Update an existing product
    public Movie updateMovie(@NonNull Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId())
                .orElseThrow(()-> new IllegalArgumentException("Movie with id " + movie.getId() + " does not exist"));
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDetail(movie.getDetail());
        existingMovie.setImage(movie.getImage());
        existingMovie.setDatestart(movie.getDatestart());
        existingMovie.setTimeMovie(movie.getTimeMovie());
        existingMovie.setListmovie_schedus(movie.getListmovie_schedus());
        existingMovie.setListtype_movies(movie.getListtype_movies());
        existingMovie.setHuman_movie(movie.getHuman_movie());
        return movieRepository.save(existingMovie);
    }
    // Delete a product by its id
    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            throw new IllegalStateException("Movie with id " + id + " does not exist");
        }
        movieRepository.deleteById(id);
    }
}
