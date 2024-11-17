package com.example.movieticket.controller;


import com.example.movieticket.model.Chair_Type;

import com.example.movieticket.service.ChairService;
import com.example.movieticket.service.Chair_TypeService;
import com.example.movieticket.service.TypeChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chair_types")
public class Chair_TypeController {
    @Autowired
    private Chair_TypeService chair_typeService;
    @Autowired
    private TypeChairService typeChairService;
    @Autowired
    private ChairService chairService;

    @GetMapping
    public String showAllChair_Type(Model model)
    {
        List<Chair_Type> chair_type =  chair_typeService.getAllChair_Types();
        model.addAttribute("chair_types", chair_type);

        return "chair_type/list";
    }
    @GetMapping("/add")
    public String addChair_TypeForm(Model model){
        model.addAttribute("chair_type",new Chair_Type());
        model.addAttribute("typeChairs", typeChairService.getAllTypeChairs());
        model.addAttribute("chairs", chairService.getAllChairs());
        return "chair_type/add";
    }


    @PostMapping("/add")
    public String addChair_Type(@Valid @ModelAttribute("chair_type") Chair_Type chair_type , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("typeChairs", typeChairService.getAllTypeChairs());
            model.addAttribute("chairs", chairService.getAllChairs());

            return "chair_type/add";
        }
        chair_typeService.createChair_Type(chair_type);
        return "redirect:/chair_types";
    }

    @GetMapping("/edit/{id}")
    public String editChair_TypeForm(@PathVariable("id") long id, Model model){
        Chair_Type editChair_Type =  chair_typeService.getChair_TypeById(id);
        if(editChair_Type != null){
            model.addAttribute("chair_type", editChair_Type);
            model.addAttribute("typeChairs", typeChairService.getAllTypeChairs());
            model.addAttribute("chairs", chairService.getAllChairs());
            return "chair_type/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editChair_Type(@Valid @ModelAttribute("chair_type") Chair_Type updateChair_Type, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("chair_types", chair_typeService.getAllChair_Types());
            model.addAttribute("typeChairs", typeChairService.getAllTypeChairs());
            model.addAttribute("chairs", chairService.getAllChairs());
            return "chair_type/edit";
        }
        chair_typeService.getAllChair_Types().stream()
                .filter(chair_type -> chair_type.getClass() == updateChair_Type.getClass())
                .findFirst()
                .ifPresent( chair_type -> {

                    chair_typeService.updateChair_Type(updateChair_Type);
                });
        return "redirect:/chair_types";
    }
    @PostMapping("/delete/{id}")
    public String deleteChair_Type(@PathVariable("id") long id){
        chair_typeService.deleteChair_Type(id);
        return "redirect:/chair_types";
    }
}
