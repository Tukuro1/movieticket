package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.TypeChair;
import com.example.movieticket.service.TypeChairService;
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
@RequestMapping("/admin/typeChair")
public class AdminTypeChairController {
    @Autowired
    private TypeChairService typeChairService;

    @GetMapping
    public String showTypeChair(Model model)
    {
        List<TypeChair> typeChair =  typeChairService.getAllTypeChair();
        model.addAttribute("typeChair", typeChair);
        return "admin/typeChair/list";
    }
    @GetMapping("/add")
    public String addTypeChairForm(Model model){
        model.addAttribute("typeChair",new TypeChair());
        return "admin/typeChair/add";
    }
    @PostMapping("/add")
    public String addTypeChair(@Valid @ModelAttribute TypeChair typeChair , BindingResult bindingResult){
        if(bindingResult.hasErrors()) {return "admin/typeChair/add";}
        typeChairService.addTypeChair(typeChair);
        return "redirect:/admin/typeChair";
    }
    @GetMapping("/edit/{id}")
    public String editTypeChairForm(@PathVariable("id") long id, Model model){
        TypeChair typeChair = typeChairService.getTypeChairById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid TypeChair Id:" + id));
        model.addAttribute("typeChair", typeChair);
        return "admin/typeChair/edit";
    }
    @PostMapping("/edit")
    public String editTypeChair(@PathVariable("id" ) Long id, @Valid TypeChair typeChair , BindingResult result, Model model){
        if(result.hasErrors()) {
            typeChair.setId(id);
            return "admin/typeChair/edit";
        }
        typeChairService.updateTypeChair(typeChair);
        model.addAttribute("typeChair",typeChairService.getTypeChairById(id));
        return "redirect:/admin/typeChair";
    }
    @PostMapping("/delete/{id}")
    public String deleteTypeChair(@PathVariable("id") long id){
        typeChairService.deleteTypeChair(id);
        return "redirect:/admin/typeChair";
    }
}
