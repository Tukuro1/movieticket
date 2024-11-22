package com.example.movieticket.controller;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.service.TypeMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/type_movie")
public class TypeMovieController {
    @Autowired
    private TypeMovieService typeMovieService;

    @GetMapping
    public String showTypeMovie(Model model)
    {
        List<Type_Movie> typeMovie =  typeMovieService.getAllTypeMovie();
        model.addAttribute("typeMovie", typeMovie);
        return "type_movie/list";
    }
}
