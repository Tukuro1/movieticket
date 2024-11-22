package com.example.movieticket.controller;

import com.example.movieticket.model.RowChair;
import com.example.movieticket.service.RoomService;
import com.example.movieticket.service.RowChairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/rowchair")
public class AdminRowChairController {
    @Autowired
    private RowChairService rowchairService;
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String showRowChair(Model model)
    {
        model.addAttribute("rowchair", rowchairService.getAllRowChair());
        return "admin/rowchair/list";
    }

    @GetMapping("/add")
    public String addRowChairForm(Model model){
        model.addAttribute("rowchair",new RowChair());
        model.addAttribute("room", roomService.getAllRoom());
        return "admin/rowchair/add";
    }
    @PostMapping("/add")
    public String addRowChair(@Valid RowChair rowChair, BindingResult result){
        if(result.hasErrors())
        {
            return "admin/rowchair/add";
        }
        rowchairService.addRowChair(rowChair);
        return "redirect:/admin/rowchair";
    }

    @GetMapping("/edit/{id}")
    public String editRowChairForm(@PathVariable("id") long id, Model model){
        Optional<RowChair> editRowChair =  rowchairService.getRowChairById(id);
        if(editRowChair != null){
            model.addAttribute("rowchair", editRowChair);
            model.addAttribute("room", roomService.getAllRoom());
            return "admin/rowchair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRowChair(@Valid @ModelAttribute("rowchair") RowChair updateRowChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("rowchair", rowchairService.getAllRowChair());
            model.addAttribute("room", roomService.getAllRoom());
            return "admin/rowchair/edit";
        }
        rowchairService.getAllRowChair().stream()
                .filter(rowChair -> rowChair.getClass() == updateRowChair.getClass())
                .findFirst()
                .ifPresent( rowChair -> {

                    rowchairService.updateRowChair(updateRowChair);
                });
        return "redirect:/admin/rowchair";
    }
    @PostMapping("/delete/{id}")
    public String deleteRowChair(@PathVariable("id") long id){
        rowchairService.deleteRowChair(id);
        return "redirect:/admin/rowchair";
    }
}
