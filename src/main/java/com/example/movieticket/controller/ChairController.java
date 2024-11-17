//package com.example.movieticket.controller;
//
//import com.example.movieticket.model.Chair;
//
//import com.example.movieticket.service.ChairService;
//import com.example.movieticket.service.RowChairService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/chairs")
//public class ChairController {
//    @Autowired
//    private ChairService chairService;
//    @Autowired
//    private RowChairService rowchairService;
//
//    @GetMapping
//    public String showAllChair(Model model)
//    {
//        List<Chair> chair =  chairService.getAllChairs();
//        model.addAttribute("chairs", chair);
//        model.addAttribute("chair_name","chairs");
//        return "chair/list";
//    }
//
//    @GetMapping("/add")
//    public String addChairForm(Model model){
//        model.addAttribute("chair",new Chair());
//        model.addAttribute("rowchairs", rowchairService.getAllRowChairs());
//        return "chair/add";
//    }
//
//
//    @PostMapping("/add")
//    public String addChair(@Valid @ModelAttribute("chair") Chair chair , BindingResult bindingResult , Model model){
//        if(bindingResult.hasErrors())
//        {
//            model.addAttribute("rowchairs", rowchairService.getAllRowChairs());
//            return "chair/add";
//        }
//        chairService.createChair(chair);
//        return "redirect:/chairs";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editChairForm(@PathVariable("id") long id, Model model){
//        Chair editChair =  chairService.getChairById(id);
//        if(editChair != null){
//            model.addAttribute("chair", editChair);
//            model.addAttribute("rowchairs", rowchairService.getAllRowChairs());
//            return "chair/edit";
//        }else {
//            return "not-found";
//        }
//    }
//    @PostMapping("/edit")
//    public String editChair(@Valid @ModelAttribute("chair") Chair updateChair, BindingResult bindingResult, Model model ){
//        if (bindingResult.hasErrors()){
//            model.addAttribute("chairs", chairService.getAllChairs());
//            model.addAttribute("rowchairs", rowchairService.getAllRowChairs());
//            return "chair/edit";
//        }
//        chairService.getAllChairs().stream()
//                .filter(chair -> chair.getClass() == updateChair.getClass())
//                .findFirst()
//                .ifPresent( chair -> {
//
//                    chairService.updateChair(updateChair);
//                });
//        return "redirect:/chairs";
//    }
//    @PostMapping("/delete/{id}")
//    public String deleteChair(@PathVariable("id") long id){
//        chairService.deleteChair(id);
//        return "redirect:/chairs";
//    }
//}
