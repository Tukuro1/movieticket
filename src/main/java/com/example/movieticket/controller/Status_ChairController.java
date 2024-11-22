package com.example.movieticket.controller;

import com.example.movieticket.model.Status_Chair;
import com.example.movieticket.service.StatusChairService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/status_chair")
public class Status_ChairController {
    @Autowired
    private StatusChairService statusChairService;
    @GetMapping
    public String showStatusChair(Model model)
    {
        List<Status_Chair> status_chair =  statusChairService.getAllStatusChair();
        model.addAttribute("status_chair", status_chair);
        return "status_chair/list";
    }
}
