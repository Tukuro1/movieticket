package com.example.movieticket.controller;

import com.example.movieticket.model.Room;

import com.example.movieticket.model.RowChair;
import com.example.movieticket.service.RoomService;
import com.example.movieticket.service.RowChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rowchairs")
public class RowChairController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RowChairService rowchairService;

    @GetMapping
    public String showAllRowChair(Model model)
    {
        List<RowChair> rowchair =  rowchairService.getAllRowChairs();
        model.addAttribute("rowchairs", rowchair);
        model.addAttribute("row_name","rowchairs");
        model.addAttribute("chair_count","rowchairs");
        return "rowchair/list";
    }

    @GetMapping("/add")
    public String addRowChairForm(Model model){
        model.addAttribute("rowchair",new RowChair());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rowchair/add";
    }


    @PostMapping("/add")
    public String addRowChair(@Valid @ModelAttribute("rowchair") RowChair rowchair , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("rooms", roomService.getAllRooms());
            return "rowchair/add";
        }
        rowchairService.createRowChair(rowchair);
        return "redirect:/rowchairs";
    }

    @GetMapping("/edit/{id}")
    public String editRowChairForm(@PathVariable("id") long id, Model model){
        RowChair editRowChair =  rowchairService.getRowChairById(id);
        if(editRowChair != null){
            model.addAttribute("rowchair", editRowChair);
            model.addAttribute("rooms", roomService.getAllRooms());
            return "rowchair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRowChair(@Valid @ModelAttribute("rowchair") RowChair updateRowChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("rowchairs", rowchairService.getAllRowChairs());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "rowchair/edit";
        }
        rowchairService.getAllRowChairs().stream()
                .filter(rowchair -> rowchair.getClass() == updateRowChair.getClass())
                .findFirst()
                .ifPresent( rowchair -> {

                    rowchairService.updateRowChair(updateRowChair);
                });
        return "redirect:/rowchairs";
    }
    @PostMapping("/delete/{id}")
    public String deleteRowChair(@PathVariable("id") long id){
        rowchairService.deleteRowChair(id);
        return "redirect:/rowchairs";
    }
}