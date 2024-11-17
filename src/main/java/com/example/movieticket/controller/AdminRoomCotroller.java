package com.example.movieticket.controller;

import com.example.movieticket.model.Room;
import com.example.movieticket.service.BranchService;
import com.example.movieticket.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/room")
public class AdminRoomCotroller {
    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showRoom(Model model)
    {
        model.addAttribute("room", roomService.getAllRoom());
        return "room/list";
    }
    @GetMapping("/add")
    public String addRoomForm(Model model){
        model.addAttribute("room",new Room());
        model.addAttribute("branch", branchService.getAllBranch());
        return "admin/room/add";
    }
    @PostMapping("/add")
    public String addRoom(@Valid Room room, BindingResult result){
        if(result.hasErrors())
        {
            return "admin/room/add";
        }
        roomService.addRoom(room);
        return "redirect:/admin/room";
    }

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable("id") long id, Model model){
        Room room = roomService.getRoomById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room Id:" + id));
        model.addAttribute("room",room);
        model.addAttribute("branch", branchService.getAllBranch());
        return "admin/room/edit";
    }
    @PostMapping("/edit")
    public String editRoom(@PathVariable("id" ) Long id, @Valid Room room , BindingResult result, Model model ){
        if (result.hasErrors()){
            room.setId(id);
            return "admin/room/edit";
        }
        roomService.updateRoom(room);
        model.addAttribute("room",room);
        return "redirect:/admin/room";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id){
        roomService.deleteRoom(id);
        return "redirect:/admin/room";
    }
}
