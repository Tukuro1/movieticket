package com.example.movieticket.controller;

import com.example.movieticket.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showBranch(Model model)
    {
        model.addAttribute("Branch", branchService.getAllBranch());
        return "branch/list";
    }
}