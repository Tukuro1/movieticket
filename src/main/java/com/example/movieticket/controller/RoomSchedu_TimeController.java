package com.example.movieticket.controller;


import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.service.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roomschedu_times")
public class RoomSchedu_TimeController {
    @Autowired
    private RoomSchedu_TimeService roomschedu_timeService;
    @Autowired
    private RoomSchedu_DayService roomschedu_dayService;
    @Autowired
    private TimeFrameService timeFrameService;

    @GetMapping
    public String showAllRoomSchedu_Time(Model model)
    {
        List<RoomSchedu_Time> roomschedu_time =  roomschedu_timeService.getAllRoomSchedu_Times();
        model.addAttribute("roomschedu_times", roomschedu_time);

        return "roomschedu_time/list";
    }
    @GetMapping("/add")
    public String addRoomSchedu_TimeForm(Model model){
        model.addAttribute("roomschedu_time",new RoomSchedu_Time());
        model.addAttribute("roomschedu_days", roomschedu_dayService.getAllRoomSchedu_Days());
        model.addAttribute("timeFrames", timeFrameService.getAllTimeFrames());
        return "roomschedu_time/add";
    }


    @PostMapping("/add")
    public String addRoomSchedu_Time(@Valid @ModelAttribute("roomschedu_time") RoomSchedu_Time roomschedu_time , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("roomschedu_days", roomschedu_dayService.getAllRoomSchedu_Days());
            model.addAttribute("timeFrames", timeFrameService.getAllTimeFrames());

            return "roomschedu_time/add";
        }
        roomschedu_timeService.createRoomSchedu_Time(roomschedu_time);
        return "redirect:/roomschedu_times";
    }

    @GetMapping("/edit/{id}")
    public String editRoomSchedu_TimeForm(@PathVariable("id") long id, Model model){
        RoomSchedu_Time editRoomSchedu_Time =  roomschedu_timeService.getRoomSchedu_TimeById(id);
        if(editRoomSchedu_Time != null){
            model.addAttribute("roomschedu_time", editRoomSchedu_Time);
            model.addAttribute("roomschedu_days", roomschedu_dayService.getAllRoomSchedu_Days());
            model.addAttribute("timeFrames", timeFrameService.getAllTimeFrames());
            return "roomschedu_time/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRoomSchedu_Time(@Valid @ModelAttribute("roomschedu_time") RoomSchedu_Time updateRoomSchedu_Time, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("roomschedu_times", roomschedu_timeService.getAllRoomSchedu_Times());
            model.addAttribute("roomschedu_days", roomschedu_dayService.getAllRoomSchedu_Days());
            model.addAttribute("timeFrames", timeFrameService.getAllTimeFrames());
            return "roomschedu_time/edit";
        }
        roomschedu_timeService.getAllRoomSchedu_Times().stream()
                .filter(roomschedu_time -> roomschedu_time.getClass() == updateRoomSchedu_Time.getClass())
                .findFirst()
                .ifPresent( roomschedu_time -> {

                    roomschedu_timeService.updateRoomSchedu_Time(updateRoomSchedu_Time);
                });
        return "redirect:/roomschedu_times";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoomSchedu_Time(@PathVariable("id") long id){
        roomschedu_timeService.deleteRoomSchedu_Time(id);
        return "redirect:/roomschedu_times";
    }
}
