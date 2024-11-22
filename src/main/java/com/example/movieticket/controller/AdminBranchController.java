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

import java.util.List;
import java.util.Optional;

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
//        model.addAttribute("Branch", branchService.getAllBranch());
//        return "admin/branch/list";
        List<Branch> branch =  branchService.getAllBranch();
        model.addAttribute("branch", branch);
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
        Optional<Branch> editBranch =  branchService.getBranchById(id);
        if(editBranch != null){
            model.addAttribute("branch", editBranch);
            model.addAttribute("area", areaService.getAllAreas());
            return "admin/branch/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBranch(@Valid @ModelAttribute("branch") Branch updateBranch, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("branch", branchService.getAllBranch());
            model.addAttribute("area", areaService.getAllAreas());
            return "admin/branch/edit";
        }
        branchService.getAllBranch().stream()
                .filter(branch -> branch.getClass() == updateBranch.getClass())
                .findFirst()
                .ifPresent( branch -> {

                    branchService.updateBranch(updateBranch);
                });
        return "redirect:/admin/branch";
    }
    @PostMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") long id){
        branchService.deleteBranch(id);
        return "redirect:/admin/branch";
    }
}
