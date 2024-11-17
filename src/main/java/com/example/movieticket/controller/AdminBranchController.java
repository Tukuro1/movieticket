package com.example.movieticket.controller;

import com.example.movieticket.model.Branch;
import com.example.movieticket.service.AreaService;
import com.example.movieticket.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/branch")
public class AdminBranchController {
    @Autowired
    private BranchService branchService;
    @Autowired
    private AreaService areaService;

    @GetMapping
    public String showBranch(Model model)
    {
        model.addAttribute("Branch", branchService.getAllBranch());
        return "admin/branch/list";
    }
    @GetMapping("/add")
    public String addBranchForm(Model model){
        model.addAttribute("branch",new Branch());
        model.addAttribute("area", areaService.getAllAreas());
        return "admin/branch/add";
    }
    @PostMapping("/add")
    public String addBranch(@Valid Branch branch, BindingResult result){
        if(result.hasErrors())
        {
            return "admin/branch/add";
        }
        branchService.addBranch(branch);
        return "redirect:/admin/branch";
    }

    @GetMapping("/edit/{id}")
    public String editBranchForm(@PathVariable("id") long id, Model model){
        Branch branch = branchService.getBranchById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid area Id:" + id));
        model.addAttribute("branch",branch);
        model.addAttribute("area", areaService.getAllAreas());
        return "admin/branch/edit";
    }
    @PostMapping("/edit")
    public String editBranch(@PathVariable("id" ) Long id, @Valid Branch branch , BindingResult result, Model model ){
        if (result.hasErrors()){
            branch.setId(id);
            return "admin/branch/edit";
        }
        branchService.updateBranch(branch);
        model.addAttribute("branch",branch);
        return "redirect:/admin/branch";
    }
    @PostMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") long id){
        branchService.deleteBranch(id);
        return "redirect:/admin/branch";
    }
}
