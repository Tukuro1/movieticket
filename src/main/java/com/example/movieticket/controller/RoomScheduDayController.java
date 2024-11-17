package com.example.movieticket.controller;

import com.example.movieticket.service.RoomScheduDayService;
import com.example.movieticket.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roomscheduday")
public class RoomScheduDayController {
    @Autowired
    private RoomScheduDayService roomScheduDayService;
    @Autowired
    private RoomService roomService;
    @GetMapping
    public String listroomScheduDay(Model model) {
        model.addAttribute("roomScheduDays", roomScheduDayService.getAllRoomScheduDay());
        return "/roomscheday/list";
    }
    @GetMapping("/{id}")
    public String listroomScheduDaydetail(@PathVariable Long id, Model model) {
        model.addAttribute("roomScheduDays", roomScheduDayService.getRoomScheduDayById(id)
                .orElseThrow(()-> new IllegalStateException("roomscheduday not found")));
        return "/roomscheday/detail";
    }
}
