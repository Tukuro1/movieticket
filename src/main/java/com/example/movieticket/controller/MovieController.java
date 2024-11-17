package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    // Display a list of all products
    @GetMapping
    public String showMovieList(Model model) {
        model.addAttribute("movie", movieService.getAllMovies());
        return "movie/list";
    }
    // Display details of a single product
    @GetMapping("/{id}")
    public String showMovieDetail(@PathVariable Long id, Movie movie, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id)
                .orElseThrow(()-> new IllegalStateException("movie not found")));
                return "movie/detail";
    }

}
