package com.example.movieticket.service;

import com.example.movieticket.model.TypeChair;
import com.example.movieticket.repository.TypeChairRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeChairService {
    private final TypeChairRepository typeChairRepository;
    public List<TypeChair> getAllTypeChair() {return typeChairRepository.findAll();}
    public Optional<TypeChair> getTypeChairById(Long id) {return typeChairRepository.findById(id);}
    public void addTypeChair(TypeChair typeChair) {typeChairRepository.save(typeChair);}
    public void updateTypeChair(@NotNull TypeChair typeChair) {
        TypeChair existingTypeChair = typeChairRepository.findById(typeChair.getId())
                .orElseThrow(() -> new IllegalStateException("TypeChair with ID " + typeChair.getId() + " does not exist."));
        existingTypeChair.setType_chair(typeChair.getType_chair());
        typeChairRepository.save(existingTypeChair);
    }
    public void deleteTypeChair(Long id) {
        if (!typeChairRepository.existsById(id)) {
            throw new IllegalStateException("TypeChair with ID " + id + " does not exist.");
        }
        typeChairRepository.deleteById(id);
    }
}
