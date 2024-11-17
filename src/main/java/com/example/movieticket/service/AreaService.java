package com.example.movieticket.service;


import com.example.movieticket.model.Area;
import com.example.movieticket.repository.AreaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;
    public List<Area> getAllAreas() {return areaRepository.findAll();}
    public Optional<Area> getAreaById(Long id) {return areaRepository.findById(id);}
    public void addArea(Area area) {areaRepository.save(area);}
    public void updateArea(@NotNull Area area) {
        Area existingArea = areaRepository.findById(area.getId())
                .orElseThrow(() -> new IllegalStateException("Area with ID " + area.getId() + " does not exist."));
        existingArea.setName(area.getName());
        areaRepository.save(existingArea);
    }
    public void deleteArea(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new IllegalStateException("Area with ID " + id + " does not exist.");
        }
        areaRepository.deleteById(id);
    }
}
