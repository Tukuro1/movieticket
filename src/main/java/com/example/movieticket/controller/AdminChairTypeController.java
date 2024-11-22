package com.example.movieticket.controller;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.service.ChairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/admin/chairtype")
public class AdminChairTypeController {
    @Autowired
    private ChairTypeService chairTypeService;

    // Create
    @PostMapping
    public ResponseEntity<Chair_Type> createChairType(@RequestBody Chair_Type chairType) {
        Chair_Type createdChairType = chairTypeService.addChairType(chairType);
        return ResponseEntity.ok(createdChairType);
    }

    // Read (Get by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Chair_Type> getChairTypeById(@PathVariable Long id) {
        Chair_Type chairType = chairTypeService.getChairTypeById(id);
        return ResponseEntity.ok(chairType);
    }

    // Read (Get all)
    @GetMapping
    public ResponseEntity<List<Chair_Type>> getAllChairTypes() {
        List<Chair_Type> chairTypes = chairTypeService.getAllChairType();
        return ResponseEntity.ok(chairTypes);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Chair_Type> updateChairType(@PathVariable Long id, @RequestBody Chair_Type chairType) {
        Chair_Type updatedChairType = chairTypeService.updateChairType(id, chairType);
        return ResponseEntity.ok(updatedChairType);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChairType(@PathVariable Long id) {
        chairTypeService.deleteChairType(id);
        return ResponseEntity.noContent().build();
    }
}
