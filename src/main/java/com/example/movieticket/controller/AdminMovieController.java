package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/movie")
public class AdminMovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String showMovieList(Model model) {
        List<Movie> movie = movieService.getAllMovies();
        model.addAttribute("movie", movieService.getAllMovies());
        return "movie/list";
    }
    @GetMapping("/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        return "/admin/movie/add";
    }
    @PostMapping("/add")
    public String addMovie(@Valid Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin/movie/add";
        }
        movieService.addMovie(movie);
        return "redirect:/admin/movie/list";
    }
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable("id") Long id, Model model) {
        Optional<Movie> editMovie=  movieService.getMovieById(id);
        if(editMovie != null){
            model.addAttribute("movie", editMovie);
            return "admin/movie/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String updateProduct(@Valid @ModelAttribute("movie") Movie updateMovie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movieService.getAllMovies());
            return "admin/movie/edit";
        }
        movieService.getAllMovies().stream()
                .filter(movie -> movie.getClass() == updateMovie.getClass())
                .findFirst()
                .ifPresent( movie -> {

                    movieService.updateMovie(updateMovie);
                });
        return "redirect:/admin/movie";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/admin/movie/list";
    }


}
