package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;
    @GetMapping
    public String showArea(Model model)
    {
        List<Area> area =  areaService.getAllAreas();
        model.addAttribute("area", area);
        return "area/list";
    }
}