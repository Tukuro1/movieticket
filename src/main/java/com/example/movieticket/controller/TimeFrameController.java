package com.example.movieticket.controller;


import com.example.movieticket.model.TimeFrame;
import com.example.movieticket.service.TimeFrameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timeFrames")
public class TimeFrameController {
    @Autowired
    private TimeFrameService timeFrameService;

    @GetMapping
    public String showAllTimeFrame(Model model)
    {
        List<TimeFrame> timeFrame =  timeFrameService.getAllTimeFrames();
        model.addAttribute("timeFrames", timeFrame);
        model.addAttribute("starttime","timeFrames");
        model.addAttribute("endtime","timeFrames");
        return "timeFrame/list";
    }

    @GetMapping("/add")
    public String addTimeFrameForm(Model model){
        model.addAttribute("timeFrame",new TimeFrame());
        return "timeFrame/add";
    }


    @PostMapping("/add")
    public String addTimeFrame(@Valid @ModelAttribute("timeFrame") TimeFrame timeFrame , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {

            return "timeFrame/add";
        }
        timeFrameService.createTimeFrame(timeFrame);
        return "redirect:/timeFrames";
    }

    @GetMapping("/edit/{id}")
    public String editTimeFrameForm(@PathVariable("id") long id, Model model){
        TimeFrame editTimeFrame =  timeFrameService.getTimeFrameById(id);
        if(editTimeFrame != null){
            model.addAttribute("timeFrame", editTimeFrame);
            return "timeFrame/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editTimeFrame(@Valid @ModelAttribute("timeFrame") TimeFrame updateTimeFrame, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("timeFrames", timeFrameService.getAllTimeFrames());
            return "timeFrame/edit";
        }
        timeFrameService.getAllTimeFrames().stream()
                .filter(timeFrame -> timeFrame.getClass() == updateTimeFrame.getClass())
                .findFirst()
                .ifPresent( timeFrame -> {

                    timeFrameService.updateTimeFrame(updateTimeFrame);
                });
        return "redirect:/timeFrames";
    }
    @PostMapping("/delete/{id}")
    public String deleteTimeFrame(@PathVariable("id") long id){
        timeFrameService.deleteTimeFrame(id);
        return "redirect:/timeFrames";
    }
}
