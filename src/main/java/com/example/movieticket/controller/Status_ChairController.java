package com.example.movieticket.controller;


import com.example.movieticket.model.Status_Chair;

import com.example.movieticket.service.ChairTypeService;
import com.example.movieticket.service.RoomScheduTimeService;
import com.example.movieticket.service.StatusChairService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/status_chairs")
public class Status_ChairController {
    @Autowired
    private StatusChairService status_chairService;
    @Autowired
    private ChairTypeService chair_typeService;
    @Autowired
    private RoomScheduTimeService roomScheduTimeService;

    @GetMapping
    public String showAllStatus_Chair(Model model)
    {
        List<Status_Chair> status_chair =  status_chairService.getAllStatusChair();
        model.addAttribute("status_chairs", status_chair);
        model.addAttribute("status","status_chairs");

        return "status_chair/list";
    }

    @GetMapping("/add")
    public String addStatus_ChairForm(Model model){
        model.addAttribute("status_chair",new Status_Chair());
        model.addAttribute("roomschedu_times", roomScheduTimeService.getAllRoomSchedu_Times());
        model.addAttribute("chair_types", chair_typeService.getAllChairType());
        return "status_chair/add";
    }


    @PostMapping("/add")
    public String addStatus_Chair(@Valid @ModelAttribute("status_chair") Status_Chair status_chair , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("roomschedu_times", roomScheduTimeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChairType());
            return "status_chair/add";
        }
        status_chairService.addStatusChair(status_chair);
        return "redirect:/status_chairs";
    }

    @GetMapping("/edit/{id}")
    public String editStatus_ChairForm(@PathVariable("id") long id, Model model){
        Optional<Status_Chair> editStatus_Chair =  status_chairService.getStatusChairById(id);
        if(editStatus_Chair != null){
            model.addAttribute("status_chair", editStatus_Chair);
            model.addAttribute("roomschedu_times", roomScheduTimeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChairType());
            return "status_chair/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editStatus_Chair(@Valid @ModelAttribute("status_chair") Status_Chair updateStatusChair, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("status_chairs", status_chairService.getAllStatusChair());
            model.addAttribute("roomschedu_times", roomScheduTimeService.getAllRoomSchedu_Times());
            model.addAttribute("chair_types", chair_typeService.getAllChairType());
            return "status_chair/edit";
        }
        status_chairService.getAllStatusChair().stream()
                .filter(status_chair -> status_chair.getClass() == updateStatusChair.getClass())
                .findFirst()
                .ifPresent( status_chair -> {

                    status_chairService.updateStatusChair(updateStatusChair);
                });
        return "redirect:/status_chairs";
    }
    @PostMapping("/delete/{id}")
    public String deleteStatus_Chair(@PathVariable("id") long id){
        status_chairService.deleteStatusChair(id);
        return "redirect:/status_chairs";
    }
}
