package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Branch;
import com.example.movieticket.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showBranch(Model model)
    {
//        model.addAttribute("Branch", branchService.getAllBranch());
//        return "branch/list";
        List<Branch> branch =  branchService.getAllBranch();
        model.addAttribute("branch", branch);
        return "branch/list";
    }

    @GetMapping("/getByArea/{id}")
    @ResponseBody
    public  List<Branch> getArea(@PathVariable("id") Long id) {
        return branchService.getBranchByArea(id);
    }
}
