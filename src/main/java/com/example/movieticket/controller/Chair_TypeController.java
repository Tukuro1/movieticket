package com.example.movieticket.controller;


import com.example.movieticket.model.Chair_Type;

import com.example.movieticket.service.ChairService;
import com.example.movieticket.service.ChairTypeService;
import com.example.movieticket.service.TypeChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/chair_type")
public class Chair_TypeController {
    @Autowired
    private ChairTypeService chair_typeService;

    @GetMapping
    public String showChair_Type(Model model)
    {
        List<Chair_Type> chairType =  chair_typeService.getAllChairType();
        model.addAttribute("chair_type", chairType);
        return "chair_type/list";
    }
}
