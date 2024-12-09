package com.example.movieticket.service;

import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.repository.TypeMovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeMovieService {
    private final TypeMovieRepository typeMovieRepository;
    public List<Type_Movie> getAllTypeMovie() {
        return typeMovieRepository.findAll();
    }
    public Type_Movie getTypeMovieById(Long id) {
        return typeMovieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChairType not found with id: " + id));
    }
    public Type_Movie addTypeMovie(Type_Movie type_movie) {
        return typeMovieRepository.save(type_movie);
    }
    public Type_Movie updateTypeMovie(Long id, Type_Movie typeMovie) {
        Type_Movie existingTypeMovie = getTypeMovieById(id);
        existingTypeMovie.setMovie(typeMovie.getMovie());
        existingTypeMovie.setType(typeMovie.getType());
        return typeMovieRepository.save(existingTypeMovie);
    }
    public void deleteTypeMovieById(Long id) {
        Type_Movie typeMovie = getTypeMovieById(id);
        typeMovieRepository.delete(typeMovie);
    }
    

    public void deleteTypeMovie(Type_Movie typeMovie) {
        typeMovieRepository.delete(typeMovie);
    }

    public List<Type_Movie> getTypeMovieByIds(List<Long> ids) {
        return typeMovieRepository.findByIdIn(ids);
    }
}
