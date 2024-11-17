package com.example.movieticket.controller;

import com.example.movieticket.model.Type;
import com.example.movieticket.service.MovieService;
import com.example.movieticket.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/type")
public class AdminTypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private MovieService movieService;
    @GetMapping
    public String listType(Model model) {
        List<Type> types = typeService.getAllTypes();
        model.addAttribute("types", types);
//        model.addAttribute("name", types);
        return "/admim/type/list";
    }
    @GetMapping("/add")
    public String addType(Model model) {
        model.addAttribute("type", new Type());
        return "/admim/type/add";
    }
    @PostMapping("/add")
    public String addType(@Valid @ModelAttribute Type type, BindingResult result) {
        if (result.hasErrors()) {
            return "/admim/type/add";
        }
        typeService.addType(type);
        return "redirect:/admin/type/list";
    }
    @GetMapping("/edit/{id}")
    public String editType(@PathVariable("id") Long id, Model model) {
        Type type = typeService.getTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type Id:" + id));
        model.addAttribute("type", type);
        model.addAttribute("movie", movieService.getMovieById(id));
        return "/admim/type/edit";
    }
    @PostMapping("/edit/{id}")
    public String editType(@PathVariable("id") Long id,@Valid Type type, BindingResult result, Model model) {
        if (result.hasErrors()) {
            type.setId(id);
            return "/admim/type/edit";
        }
        typeService.updateType(type);
        model.addAttribute("type", typeService.getAllTypes());
        return "redirect:/admin/type/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.getTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type Id:" + id));
        typeService.deleteTypeById(id);
        return "redirect:/admin/type/list";
    }
}
