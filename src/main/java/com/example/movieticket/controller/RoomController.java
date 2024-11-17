package com.example.movieticket.controller;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Room;
import com.example.movieticket.service.AreaService;
import com.example.movieticket.service.BranchService;
import com.example.movieticket.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String showAllRoom(Model model)
    {
        List<Room> room =  roomService.getAllRooms();
        model.addAttribute("rooms", room);
        model.addAttribute("room_number","rooms");
        return "room/list";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model){
        model.addAttribute("room",new Room());
        model.addAttribute("branchs", branchService.getAllBranchs());
        return "room/add";
    }


    @PostMapping("/add")
    public String addRoom(@Valid @ModelAttribute("room") Room room , BindingResult bindingResult , Model model){
        if(bindingResult.hasErrors())
        {
            model.addAttribute("branchs", branchService.getAllBranchs());
            return "room/add";
        }
        roomService.createRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable("id") long id, Model model){
        Room editRoom =  roomService.getRoomById(id);
        if(editRoom != null){
            model.addAttribute("room", editRoom);
            model.addAttribute("branchs", branchService.getAllBranchs());
            return "room/edit";
        }else {
            return "not-found";
        }
    }
    @PostMapping("/edit")
    public String editRoom(@Valid @ModelAttribute("room") Room updateRoom, BindingResult bindingResult, Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("branchs", branchService.getAllBranchs());
            return "room/edit";
        }
        roomService.getAllRooms().stream()
                .filter(room -> room.getClass() == updateRoom.getClass())
                .findFirst()
                .ifPresent( room -> {

                    roomService.updateRoom(updateRoom);
                });
        return "redirect:/rooms";
    }
    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id){
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

}