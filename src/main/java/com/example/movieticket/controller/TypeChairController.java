package com.example.movieticket.controller;

import com.example.movieticket.model.Chair;
import com.example.movieticket.model.TypeChair;
import com.example.movieticket.service.ChairService;
import com.example.movieticket.service.RowChairService;
import com.example.movieticket.service.TypeChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/typeChairs")
public class TypeChairController {
    @Autowired
    private TypeChairService typeChairService;


    @GetMapping
    public String showAllTypeChair(Model model)
    {
        List<TypeChair> typeChair =  typeChairService.getAllTypeChairs();
        model.addAttribute("typeChairs", typeChair);
        model.addAttribute("type_chair","typeChairs");
        return "typeChair/list";
    }

    @GetMapping("/add")
    public String addTypeTypeChairForm(Model model){
        model.addAttribute("typeChair",new TypeChair());

        return "typeChair/add";
    }


    @PostMapping("/add")
    public String addTypeChair(@Valid @ModelAttribute("typeChair") TypeChair typeChair , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {

            return "typeChair/add";
        }
        typeChairService.createTypeChair(typeChair);
        return "redirect:/typeChairs";
    }

    @GetMapping("/edit/{id}")
    public String editTypeChairForm(@PathVariable("id") long id, Model model){
        TypeChair editTypeChair =  typeChairService.getTypeChairById(id);
        if(editTypeChair != null){
            model.addAttribute("typeChair", editTypeChair);

            return "typeChair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editTypeChair(@Valid @ModelAttribute("typeChair") TypeChair updateTypeChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("typeChairs", typeChairService.getAllTypeChairs());

            return "typeChair/edit";
        }
        typeChairService.getAllTypeChairs().stream()
                .filter(typechair -> typechair.getClass() == updateTypeChair.getClass())
                .findFirst()
                .ifPresent( typechair -> {

                    typeChairService.updateTypeChair(updateTypeChair);
                });
        return "redirect:/typeChairs";
    }
    @PostMapping("/delete/{id}")
    public String deleteTypeChair(@PathVariable("id") long id){
        typeChairService.deleteTypeChair(id);
        return "redirect:/typeChairs";
    }
}
