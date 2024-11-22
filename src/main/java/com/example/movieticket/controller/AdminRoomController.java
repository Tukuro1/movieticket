package com.example.movieticket.controller;

import com.example.movieticket.model.Branch;
import com.example.movieticket.model.Room;
import com.example.movieticket.service.BranchService;
import com.example.movieticket.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/room")
public class AdminRoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showRoom(Model model)
    {
        model.addAttribute("room", roomService.getAllRoom());
        return "admin/room/list";
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
        Optional<Room> editRoom =  roomService.getRoomById(id);
        if(editRoom != null){
            model.addAttribute("room", editRoom);
            model.addAttribute("branch", branchService.getAllBranch());
            return "admin/room/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRoom(@Valid @ModelAttribute("room") Room updateRoom, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("room", roomService.getAllRoom());
            model.addAttribute("branch", branchService.getAllBranch());
            return "admin/room/edit";
        }
        roomService.getAllRoom().stream()
                .filter(room -> room.getClass() == updateRoom.getClass())
                .findFirst()
                .ifPresent( room -> {

                    roomService.updateRoom(updateRoom);
                });
        return "redirect:/admin/room";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id){
        roomService.deleteRoom(id);
        return "redirect:/admin/room";
    }
}
