package com.example.movieticket.controller;

import com.example.movieticket.model.TypeChair;
import com.example.movieticket.service.TypeChairService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/typeChair")
public class TypeChairController {
    @Autowired
    private TypeChairService typeChairService;

    @GetMapping
    public String showTypeChair(Model model)
    {
        List<TypeChair> typeChair =  typeChairService.getAllTypeChair();
        model.addAttribute("typeChair", typeChair);
        return "typeChair/list";
    }
}
