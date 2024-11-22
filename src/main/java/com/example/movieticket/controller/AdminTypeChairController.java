package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Chair;
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
import java.util.Optional;

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
        Optional<TypeChair> editTypeChair =  typeChairService.getTypeChairById(id);
        if(editTypeChair != null){
            model.addAttribute("typeChair", editTypeChair);
            return "admin/typeChair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editTypeChair(@Valid @ModelAttribute("rowchair") TypeChair updateTypeChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("typeChair", typeChairService.getAllTypeChair());
            return "admin/typeChair/edit";
        }
        typeChairService.getAllTypeChair().stream()
                .filter(typeChair -> typeChair.getClass() == updateTypeChair.getClass())
                .findFirst()
                .ifPresent( typeChair -> {

                    typeChairService.updateTypeChair(updateTypeChair);
                });
        return "redirect:/admin/typeChair";
    }
    @PostMapping("/delete/{id}")
    public String deleteTypeChair(@PathVariable("id") long id){
        typeChairService.deleteTypeChair(id);
        return "redirect:/admin/typeChair";
    }
}
