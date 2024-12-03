package com.example.movieticket.controller;

import com.example.movieticket.model.Chair;
import com.example.movieticket.service.ChairService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chair")
public class ChairController {
    @Autowired
    private ChairService chairService;

    @GetMapping
    public String showChair(Model model)
    {
        List<Chair> chair =  chairService.getAllChair();
        model.addAttribute("chair", chair);
        return "chair/list";
    }
}
