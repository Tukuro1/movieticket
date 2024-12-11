package com.example.movieticket.controller;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.service.TypeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/admin/type_movie")
public class AdminTypeMovieController {
    @Autowired
    private TypeMovieService typeMovieService;

    // Create
    @PostMapping
    public ResponseEntity<Type_Movie> createTypeMovie(@RequestBody Type_Movie typeMovie) {
        Type_Movie createdTypeMovie = typeMovieService.addTypeMovie(typeMovie);
        return ResponseEntity.ok(createdTypeMovie);
    }

    // Read (Get by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Type_Movie> getChairTypeById(@PathVariable Long id) {
        Type_Movie typeMovie = typeMovieService.getTypeMovieById(id);
        return ResponseEntity.ok(typeMovie);
    }

    // Read (Get all)
    @GetMapping
    public ResponseEntity<List<Type_Movie>> getAllTypeMovie() {
        List<Type_Movie> typeMovie = typeMovieService.getAllTypeMovie();
        return ResponseEntity.ok(typeMovie);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Type_Movie> updateTypeMovie(@PathVariable Long id, @RequestBody Type_Movie typeMovie) {
        Type_Movie updatedTypeMovie = typeMovieService.updateTypeMovie(id, typeMovie);
        return ResponseEntity.ok(updatedTypeMovie);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeMovie(@PathVariable Long id) {
        typeMovieService.deleteTypeMovieById(id);
        return ResponseEntity.noContent().build();
    }
}
