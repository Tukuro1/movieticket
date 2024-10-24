package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Controller
@RequestMapping("/areas")
public class AreaController {


        @Autowired
        private AreaService areaService;

        @GetMapping
        public String showAllArea(Model model)
        {
            List<Area> area =  areaService.getAllAreas();
            model.addAttribute("areas", area);
            model.addAttribute("name","areas");
            return "area/list";
        }

        @GetMapping("/add")
        public String addAreaForm(Model model){
            model.addAttribute("area",new Area());
            return "area/add";
        }


        @PostMapping("/add")
        public String addArea(@Valid @ModelAttribute("area") Area area , BindingResult bindingResult , Model model){
            if(bindingResult.hasErrors())
            {

                return "area/add";
            }
            areaService.createArea(area);
            return "redirect:/areas";
        }

        @GetMapping("/edit/{id}")
        public String editAreaForm(@PathVariable("id") long id, Model model){
            Area editCate =  areaService.getAreaById(id);
            if(editCate != null){
                model.addAttribute("area", editCate);
                return "area/edit";
            }else {
                return "not-found";
            }
        }
        @PostMapping("/edit")
        public String editArea(@Valid @ModelAttribute("area") Area updateCate, BindingResult bindingResult, Model model ){
            if (bindingResult.hasErrors()){
                model.addAttribute("areas", areaService.getAllAreas());
                return "area/edit";
            }
            areaService.getAllAreas().stream()
                    .filter(area -> area.getClass() == updateCate.getClass())
                    .findFirst()
                    .ifPresent( area -> {

                        areaService.updateArea(updateCate);
                    });
            return "redirect:/areas";
        }
        @PostMapping("/delete/{id}")
        public String deleteArea(@PathVariable("id") long id){
            areaService.deleteArea(id);
            return "redirect:/areas";
        }

}
