package com.example.movieticket.controller;

import com.example.movieticket.model.Type;
import com.example.movieticket.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TypeController {
    @Autowired
    private final TypeService typeService;

    @GetMapping("/type")
    public String listType(Model model) {
        List<Type> type = typeService.getAllTypes();
        model.addAttribute("type", type);
        return "/type/list";
    }
}
