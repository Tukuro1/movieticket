package com.example.movieticket.controller;


import com.example.movieticket.model.RoomSchedu_Day;
import com.example.movieticket.service.ChairService;

import com.example.movieticket.service.RoomSchedu_DayService;
import com.example.movieticket.service.RoomService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roomschedu_days")
public class RoomSchedu_DayController {
    @Autowired
    private RoomSchedu_DayService roomschedu_dayService;
    @Autowired
    private RoomService roomService;


    @GetMapping
    public String showAllRoomSchedu_Day(Model model)
    {
        List<RoomSchedu_Day> roomschedu_day =  roomschedu_dayService.getAllRoomSchedu_Days();
        model.addAttribute("roomschedu_days", roomschedu_day);
        model.addAttribute("dateshow","roomschedu_days");

        return "roomschedu_day/list";
    }
    @GetMapping("/add")
    public String addRoomSchedu_DayForm(Model model){
        model.addAttribute("roomschedu_day",new RoomSchedu_Day());
        model.addAttribute("rooms", roomService.getAllRooms());

        return "roomschedu_day/add";
    }


    @PostMapping("/add")
    public String addRoomSchedu_Day(@Valid @ModelAttribute("roomschedu_day") RoomSchedu_Day roomschedu_day , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("rooms", roomService.getAllRooms());

            return "roomschedu_day/add";
        }
        roomschedu_dayService.createRoomSchedu_Day(roomschedu_day);
        return "redirect:/roomschedu_days";
    }

    @GetMapping("/edit/{id}")
    public String editRoomSchedu_DayForm(@PathVariable("id") long id, Model model){
        RoomSchedu_Day editRoomSchedu_Day =  roomschedu_dayService.getRoomSchedu_DayById(id);
        if(editRoomSchedu_Day != null){
            model.addAttribute("roomschedu_day", editRoomSchedu_Day);
            model.addAttribute("rooms", roomService.getAllRooms());
            return "roomschedu_day/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRoomSchedu_Day(@Valid @ModelAttribute("roomschedu_day") RoomSchedu_Day updateRoomSchedu_Day, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("roomschedu_days", roomschedu_dayService.getAllRoomSchedu_Days());
            model.addAttribute("rooms", roomService.getAllRooms());
            return "roomschedu_day/edit";
        }
        roomschedu_dayService.getAllRoomSchedu_Days().stream()
                .filter(roomschedu_day -> roomschedu_day.getClass() == updateRoomSchedu_Day.getClass())
                .findFirst()
                .ifPresent( roomschedu_day -> {

                    roomschedu_dayService.updateRoomSchedu_Day(updateRoomSchedu_Day);
                });
        return "redirect:/roomschedu_days";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoomSchedu_Day(@PathVariable("id") long id){
        roomschedu_dayService.deleteRoomSchedu_Day(id);
        return "redirect:/roomschedu_days";
    }
}
