package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.service.MovieService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                .orElseThrow(() -> new IllegalStateException("movie not found")));
        return "movie/detail";
    }

    @GetMapping("/get-movies")
    @ResponseBody
    public Page<Movie> getMovies(@RequestParam(defaultValue = "0") int page, // Trang hiện tại
            @RequestParam(defaultValue = "10") int size, // Kích thước trang
            @RequestParam(defaultValue = "") String keyword, // Từ khóa tìm kiếm (title)
            @RequestParam(defaultValue = "") Long type, // Loại phim (genre)
            @RequestParam(defaultValue = "") Boolean isHighlight) {
        return movieService.getMovies(page, size, keyword, type, isHighlight);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

}
