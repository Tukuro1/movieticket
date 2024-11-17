package com.example.movieticket.controller;

import com.example.movieticket.service.RoomScheduDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/roomschedu_day")
public class RoomSchedu_DayController {
    @Autowired
    private RoomScheduDayService roomScheduDayService;

    @GetMapping
    public String showRoomSchedu_Day(Model model)
    {
        model.addAttribute("roomschedu_day", roomScheduDayService.getAllRoomScheduDay());
        return "roomschedu_day/list";
    }
}
