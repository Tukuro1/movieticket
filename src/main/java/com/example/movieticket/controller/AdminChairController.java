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
import java.util.Optional;

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
        Optional<Chair> editChair =  chairService.getChairById(id);
        if(editChair != null){
            model.addAttribute("chair", editChair);
            model.addAttribute("rowchar", rowChairService.getAllRowChair());
            return "admin/chair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editChair(@Valid @ModelAttribute("rowchair") Chair updateChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("chair", chairService.getAllChair());
            model.addAttribute("rowchar", rowChairService.getAllRowChair());
            return "admin/chair/edit";
        }
        chairService.getAllChair().stream()
                .filter(chair -> chair.getClass() == updateChair.getClass())
                .findFirst()
                .ifPresent( chair -> {

                    chairService.updateChair(updateChair);
                });
        return "redirect:/admin/chair";
    }
    @PostMapping("/delete/{id}")
    public String deleteChair(@PathVariable("id") long id){
        chairService.deleteChair(id);
        return "redirect:/admin/chair";
    }
}
