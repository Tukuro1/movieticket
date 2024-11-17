package com.example.movieticket.controller;

import com.example.movieticket.model.RowChair;
import com.example.movieticket.service.RowChairService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rowchair")
public class RowChairController {
    @Autowired
    private RowChairService rowchairService;

    @GetMapping
    public String showRowChair(Model model)
    {
        model.addAttribute("rowChair", rowchairService.getAllRowChair());
        return "rowchair/list";
    }
}