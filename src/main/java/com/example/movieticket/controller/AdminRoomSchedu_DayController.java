package com.example.movieticket.controller;

import com.example.movieticket.model.Branch;
import com.example.movieticket.model.RoomSchedu_Day;
import com.example.movieticket.service.RoomScheduDayService;
import com.example.movieticket.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/roomschedu_day")
public class AdminRoomSchedu_DayController {
    @Autowired
    private RoomScheduDayService roomScheduDayService;
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String showRoomSchedu_Day(Model model)
    {
        model.addAttribute("roomschedu_day", roomScheduDayService.getAllRoomScheduDay());
        return "admin/roomschedu_day/list";
    }
    @GetMapping("/add")
    public String addBranchForm(Model model){
        model.addAttribute("roomschedu_day",new Branch());
        model.addAttribute("room", roomService.getAllRoom());
        return "admin/roomschedu_day/add";
    }
    @PostMapping("/add")
    public String addRoomSchedu_Day(@Valid RoomSchedu_Day roomschedu_day, BindingResult result){
        if(result.hasErrors())
        {
            return "admin/roomschedu_day/add";
        }
        roomScheduDayService.addRoomScheduDay(roomschedu_day);
        return "redirect:/admin/roomschedu_day";
    }

    @GetMapping("/edit/{id}")
    public String editRoomSchedu_DayForm(@PathVariable("id") long id, Model model){
        RoomSchedu_Day roomschedu_day = roomScheduDayService.getRoomScheduDayById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid roomschedu_day Id:" + id));
        model.addAttribute("roomschedu_day",roomschedu_day);
        model.addAttribute("room", roomService.getAllRoom());
        return "admin/roomschedu_day/edit";
    }
    @PostMapping("/edit")
    public String editRoomSchedu_Day(@PathVariable("id" ) Long id, @Valid RoomSchedu_Day roomschedu_day , BindingResult result, Model model ){
        if (result.hasErrors()){
            roomschedu_day.setId(id);
            return "admin/roomschedu_day/edit";
        }
        roomScheduDayService.updateRoomScheduDay(roomschedu_day);
        model.addAttribute("roomschedu_day",roomschedu_day);
        return "redirect:/admin/roomschedu_day";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoomSchedu_Day(@PathVariable("id") long id){
        roomScheduDayService.deleteRoomScheduDayById(id);
        return "redirect:/admin/roomschedu_day";
    }
}
