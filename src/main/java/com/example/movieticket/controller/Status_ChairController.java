package com.example.movieticket.controller;


import com.example.movieticket.model.Status_Chair;

import com.example.movieticket.service.ChairService;
import com.example.movieticket.service.Chair_TypeService;
import com.example.movieticket.service.RoomSchedu_TimeService;
import com.example.movieticket.service.Status_ChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/status_chairs")
public class Status_ChairController {
    @Autowired
    private Status_ChairService status_chairService;
    @Autowired
    private Chair_TypeService chair_typeService;
    @Autowired
    private RoomSchedu_TimeService roomschedu_timeService;

    @GetMapping
    public String showAllStatus_Chair(Model model)
    {
        List<Status_Chair> status_chair =  status_chairService.getAllStatus_Chairs();
        model.addAttribute("status_chairs", status_chair);
        model.addAttribute("status","status_chairs");

        return "status_chair/list";
    }

    @GetMapping("/add")
    public String addStatus_ChairForm(Model model){
        model.addAttribute("status_chair",new Status_Chair());
        model.addAttribute("roomschedu_times", roomschedu_timeService.getAllRoomSchedu_Times());
        model.addAttribute("chair_types", chair_typeService.getAllChair_Types());
        return "status_chair/add";
    }


    @PostMapping("/add")
    public String addStatus_Chair(@Valid @ModelAttribute("status_chair") Status_Chair status_chair , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("roomschedu_times", roomschedu_timeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChair_Types());
            return "status_chair/add";
        }
        status_chairService.createStatus_Chair(status_chair);
        return "redirect:/status_chairs";
    }

    @GetMapping("/edit/{id}")
    public String editStatus_ChairForm(@PathVariable("id") long id, Model model){
        Status_Chair editStatus_Chair =  status_chairService.getStatus_ChairById(id);
        if(editStatus_Chair != null){
            model.addAttribute("status_chair", editStatus_Chair);
            model.addAttribute("roomschedu_times", roomschedu_timeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChair_Types());
            return "status_chair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editStatus_Chair(@Valid @ModelAttribute("status_chair") Status_Chair updateStatus_Chair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("status_chairs", status_chairService.getAllStatus_Chairs());
            model.addAttribute("roomschedu_times", roomschedu_timeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChair_Types());
            return "status_chair/edit";
        }
        status_chairService.getAllStatus_Chairs().stream()
                .filter(status_chair -> status_chair.getClass() == updateStatus_Chair.getClass())
                .findFirst()
                .ifPresent( status_chair -> {

                    status_chairService.updateStatus_Chair(updateStatus_Chair);
                });
        return "redirect:/status_chairs";
    }
    @PostMapping("/delete/{id}")
    public String deleteStatus_Chair(@PathVariable("id") long id){
        status_chairService.deleteStatus_Chair(id);
        return "redirect:/status_chairs";
    }
}
