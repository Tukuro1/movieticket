package com.example.movieticket.controller;

import com.example.movieticket.model.Director_Actor;
import com.example.movieticket.service.DirectorActorService;
import com.example.movieticket.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/directoractor")
public class AdminDirectorActorController {
    @Autowired
    private DirectorActorService directorActorService;
    @Autowired
    private MovieService movieService;
    @GetMapping
    public String showDirectoractor(Model model) {
        model.addAttribute("director_actor", directorActorService.getAllDirectorActor());
        return "admin/directoractor";
    }
    @GetMapping("/add")
    public String addDirectoractor(Model model) {
        model.addAttribute("director_actor",new Director_Actor());
        model.addAttribute("Movie", movieService.getAllMovies());
        return "admin/directoractor/add";
    }
    @PostMapping("/add")
    public String addDirectoractor(@Valid Director_Actor director_actor, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin/directoractor/add";
        }
        directorActorService.addDirectorActor(director_actor);
        return "redirect:/admin/directoractor";
    }
    @GetMapping("/edit/{id}")
    public String editDirectoractor(@PathVariable Long id, Model model) {
        Director_Actor director_actor = directorActorService.getDirectorActorById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid director actor id:" + id));
        model.addAttribute("director_actor", director_actor);
        model.addAttribute("Movie", movieService.getAllMovies());
        return "admin/directoractor/edit";
    }
    @PostMapping("/edit")
    public String editDirectoractor(@PathVariable Long id, @Valid Director_Actor director_actor, BindingResult result) {
        if (result.hasErrors()) {
            director_actor.setId(id);
            return "redirect:/admin/directoractor/edit";
        }
        directorActorService.updateDirectorActor(director_actor);
        return "redirect:/admin/directoractor";
    }
    @GetMapping("/delete/{id}")
    public String deleteDirectoractor(@PathVariable Long id) {
        directorActorService.deleteDirectorActorById(id);
        return "redirect:/admin/directoractor";
    }
}
