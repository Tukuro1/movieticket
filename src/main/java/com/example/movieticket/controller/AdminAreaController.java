package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.service.AreaService;
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
@RequestMapping("/admin/area")
public class AdminAreaController {
    @Autowired
    private AreaService areaService;
    @GetMapping
    public String showArea(Model model)
    {
        List<Area> area =  areaService.getAllAreas();
        model.addAttribute("area", area);
        return "admim/area/list";
    }
    @GetMapping("/add")
    public String addAreaForm(Model model){
        model.addAttribute("area",new Area());
        return "admin/area/add";
    }
    @PostMapping("/add")
    public String addArea(@Valid @ModelAttribute Area area , BindingResult bindingResult){
        if(bindingResult.hasErrors()) {return "admin/area/add";}
        areaService.addArea(area);
        return "redirect:/admin/area";
    }
    @GetMapping("/edit/{id}")
    public String editAreaForm(@PathVariable("id") long id, Model model){
        Area area = areaService.getAreaById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid area Id:" + id));
        model.addAttribute("area", area);
        return "admin/area/edit";
    }
    @PostMapping("/edit")
    public String editArea(@PathVariable("id" ) Long id, @Valid Area area , BindingResult result, Model model){
        if(result.hasErrors()) {
            area.setId(id);
            return "admin/area/edit";
        }
        areaService.updateArea(area);
        model.addAttribute("area",areaService.getAreaById(id));
        return "redirect:/admin/area";
    }
    @PostMapping("/delete/{id}")
    public String deleteArea(@PathVariable("id") long id){
        areaService.deleteArea(id);
        return "redirect:/admin/area";
    }
}
