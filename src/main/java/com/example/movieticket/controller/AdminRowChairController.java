package com.example.movieticket.controller;

import com.example.movieticket.model.Branch;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/rowchair")
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
        RowChair rowChair = rowchairService.getRowChairById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rowchair Id:" + id));
        model.addAttribute("rowchair",rowChair);
        model.addAttribute("room", roomService.getAllRoom());
        return "admin/rowchair/edit";
    }
    @PostMapping("/edit")
    public String editRowChair(@PathVariable("id" ) Long id, @Valid RowChair rowChair , BindingResult result, Model model ){
        if (result.hasErrors()){
            rowChair.setId(id);
            return "admin/rowchair/edit";
        }
        rowchairService.updateRowChair(rowChair);
        model.addAttribute("rowchair", rowChair);
        return "redirect:/admin/rowchair";
    }
    @PostMapping("/delete/{id}")
    public String deleteRowChair(@PathVariable("id") long id){
        rowchairService.deleteRowChair(id);
        return "redirect:/admin/rowchair";
    }
}
