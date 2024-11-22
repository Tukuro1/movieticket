package com.example.movieticket.controller;

import com.example.movieticket.model.Chair;
import com.example.movieticket.service.ChairService;
import com.example.movieticket.service.RowChairService;
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
@RequestMapping("/admin/chair")
public class AdminChairController {
    @Autowired
    private ChairService chairService;
    @Autowired
    private RowChairService rowChairService;

    @GetMapping
    public String showChair(Model model)
    {
        List<Chair> chair =  chairService.getAllChair();
        model.addAttribute("chair", chair);
        return "admin/chair/list";
    }
    @GetMapping("/add")
    public String addChairForm(Model model){
        model.addAttribute("chair",new Chair());
        model.addAttribute("rowchar", rowChairService.getAllRowChair());
        return "admin/chair/add";
    }
    @PostMapping("/add")
    public String addChair(@Valid @ModelAttribute Chair chair , BindingResult bindingResult){
        if(bindingResult.hasErrors()) {return "admin/chair/add";}
        chairService.addChair(chair);
        return "redirect:/admin/chair";
    }
    @GetMapping("/edit/{id}")
    public String editChairForm(@PathVariable("id") long id, Model model){
        Chair chair = chairService.getChairById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid chair Id:" + id));
        model.addAttribute("chair", chair);
        model.addAttribute("rowchar", rowChairService.getAllRowChair());
        return "admin/chair/edit";
    }
    @PostMapping("/edit")
    public String editChair(@PathVariable("id" ) Long id, @Valid Chair chair , BindingResult result, Model model){
        if(result.hasErrors()) {
            chair.setId(id);
            return "admin/chair/edit";
        }
        chairService.updateChair(chair);
        model.addAttribute("chair",chairService.getChairById(id));
        return "redirect:/admin/chair";
    }
    @PostMapping("/delete/{id}")
    public String deleteChair(@PathVariable("id") long id){
        chairService.deleteChair(id);
        return "redirect:/admin/chair";
    }
}
