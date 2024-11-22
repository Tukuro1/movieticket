package com.example.movieticket.service;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.repository.ChairTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ChairTypeService {
    private final ChairTypeRepository chairTypeRepository;
    public List<Chair_Type> getAllChairType() {
        return chairTypeRepository.findAll();
    }
    public Chair_Type getChairTypeById(Long id) {
        return chairTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ChairType not found with id: " + id));
    }
    public Chair_Type addChairType(Chair_Type chairType) {
        return chairTypeRepository.save(chairType);
    }
    public Chair_Type updateChairType(Long id, Chair_Type chairType) {
        Chair_Type existingChairType = getChairTypeById(id);
        existingChairType.setChair(chairType.getChair());
        existingChairType.setTypechair(chairType.getTypechair());
        return chairTypeRepository.save(existingChairType);
    }
    public void deleteChairType(Long id) {
        Chair_Type chairType = getChairTypeById(id);
        chairTypeRepository.delete(chairType);
    }
}
