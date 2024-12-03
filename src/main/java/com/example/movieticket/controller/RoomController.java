package com.example.movieticket.controller;

import com.example.movieticket.model.Room;
import com.example.movieticket.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String showRoom(Model model)
    {
        model.addAttribute("room", roomService.getAllRoom());
        return "room/list";
    }

    // API trả về tất cả các phòng chiếu
    @GetMapping("/all")
    @ResponseBody
    public List<Room> getAllRooms() {
        return roomService.getAllRoom();
    }
}