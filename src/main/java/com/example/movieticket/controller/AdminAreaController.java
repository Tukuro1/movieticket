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
import java.util.Optional;

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
        return "admin/area/list";
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
        Optional<Area> editArea =  areaService.getAreaById(id);
        if(editArea != null){
            model.addAttribute("area", editArea);
            return "admin/area/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editArea(@Valid @ModelAttribute("area") Area updateCate, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("area", areaService.getAllAreas());
            return "admin/area/edit";
        }
        areaService.getAllAreas().stream()
                .filter(area -> area.getClass() == updateCate.getClass())
                .findFirst()
                .ifPresent( area -> {

                    areaService.updateArea(updateCate);
                });
        return "redirect:/admin/area";
    }
    @PostMapping("/delete/{id}")
    public String deleteArea(@PathVariable("id") long id){
        areaService.deleteArea(id);
        return "redirect:/admin/area";
    }
}
