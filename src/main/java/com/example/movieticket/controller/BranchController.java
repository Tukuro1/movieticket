package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Branch;
import com.example.movieticket.service.AreaService;
import com.example.movieticket.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branchs")
public class BranchController {
    @Autowired
    private BranchService branchService;
    @Autowired
    private AreaService areaService;

    @GetMapping
    public String showAllBranch(Model model)
    {
        List<Branch> branch =  branchService.getAllBranchs();
        model.addAttribute("branchs", branch);
        model.addAttribute("address","branchs");

        return "branch/list";
    }
    @GetMapping("/add")
    public String addBranchForm(Model model){
        model.addAttribute("branch",new Branch());
        model.addAttribute("areas", areaService.getAllAreas());
        return "branch/add";
    }


    @PostMapping("/add")
    public String addBranch(@Valid @ModelAttribute("branch") Branch branch , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("areas", areaService.getAllAreas());

            return "branch/add";
        }
        branchService.createBranch(branch);
        return "redirect:/branchs";
    }

    @GetMapping("/edit/{id}")
    public String editBranchForm(@PathVariable("id") long id, Model model){
        Branch editBranch =  branchService.getBranchById(id);
        if(editBranch != null){
            model.addAttribute("branch", editBranch);
            model.addAttribute("areas", areaService.getAllAreas());
            return "branch/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editBranch(@Valid @ModelAttribute("branch") Branch updateBranch, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("branchs", branchService.getAllBranchs());
            model.addAttribute("areas", areaService.getAllAreas());
            return "branch/edit";
        }
        branchService.getAllBranchs().stream()
                .filter(branch -> branch.getClass() == updateBranch.getClass())
                .findFirst()
                .ifPresent( branch -> {

                    branchService.updateBranch(updateBranch);
                });
        return "redirect:/branchs";
    }
    @PostMapping("/delete/{id}")
    public String deleteBranch(@PathVariable("id") long id){
        branchService.deleteBranch(id);
        return "redirect:/branchs";
    }
}