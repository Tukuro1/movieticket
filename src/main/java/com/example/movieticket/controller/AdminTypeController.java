package com.example.movieticket.controller;

import com.example.movieticket.model.Type;
import com.example.movieticket.service.TypeService;
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
@RequestMapping("/admin/type")
public class AdminTypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping
    public String listType(Model model) {
        List<Type> type = typeService.getAllTypes();
        model.addAttribute("type", type);
        return "/admim/type/list";
    }
    @GetMapping("/add")
    public String addType(Model model) {
        model.addAttribute("type", new Type());
        return "/admim/type/add";
    }
    @PostMapping("/add")
    public String addType(@Valid @ModelAttribute Type type, BindingResult result) {
        if (result.hasErrors()) {
            return "/admim/type/add";
        }
        typeService.addType(type);
        return "redirect:/admin/type/list";
    }
    @GetMapping("/edit/{id}")
    public String editType(@PathVariable("id") Long id, Model model) {
        Optional<Type> editType =  typeService.getTypeById(id);
        if(editType != null){
            model.addAttribute("type", editType);

            return "admin/type/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit/{id}")
    public String editType(@Valid @ModelAttribute("rowchair") Type updateType, BindingResult bindingResult, Model model ) {
        if (bindingResult.hasErrors()){
            model.addAttribute("type", typeService.getAllTypes());
            return "admin/type/edit";
        }
        typeService.getAllTypes().stream()
                .filter(type -> type.getClass() == updateType.getClass())
                .findFirst()
                .ifPresent( type -> {

                    typeService.updateType(updateType);
                });
        return "redirect:/admin/type";
    }
    @GetMapping("/delete/{id}")
    public String deleteType(@PathVariable("id") Long id) {
        typeService.getTypeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid type Id:" + id));
        typeService.deleteTypeById(id);
        return "redirect:/admin/type/list";
    }
}
