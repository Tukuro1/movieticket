package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.service.DirectorActorService;
import com.example.movieticket.service.MovieService;

import com.example.movieticket.service.RoomScheduDayService;
import com.example.movieticket.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/movie")
public class AdminMovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private RoomScheduDayService roomScheduDayService;
    @Autowired
    private DirectorActorService directorActorService;

    @GetMapping
    public String showMovieList(Model model) {
        model.addAttribute("movie", movieService.getAllMovies());
        return "movie/list";
    }
    @GetMapping("/add")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("type", typeService.getAllTypes());
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
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid movie Id:" + id));
        model.addAttribute("movie", movie);
        model.addAttribute("type", typeService.getAllTypes());
        model.addAttribute("schedu", roomScheduDayService.getAllRoomScheduDay());
        model.addAttribute("director", directorActorService.getAllDirectorActor());
        return "/admin/movie/edit";
    }
    @PostMapping("/edit")
    public String updateProduct(@PathVariable Long id, @Valid Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            movie.setId(id); // set id to keep it in the form in case of errors
            return "redirect:/admin/movie/edit";
        }
        movieService.updateMovie(movie);
        return "redirect:/admin/movie/list";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/admin/movie/list";
    }


}
