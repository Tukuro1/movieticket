package com.example.movieticket.service;

import com.example.movieticket.model.Type;
import com.example.movieticket.repository.TypeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TypeService {
    private final TypeRepository typeRepository;
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }
    public Optional<Type> getTypeById(Long id) {
        return typeRepository.findById(id);
    }
    public void addType(Type type) {
        typeRepository.save(type);
    }
    public void updateType(@NotNull Type type) {
        Type existingType = typeRepository.findById(type.getId())
                .orElseThrow(() -> new IllegalStateException("Type with id " + type.getId() + "does not exist"));
                existingType.setName(type.getName());
                typeRepository.save(existingType);
    }
    public void deleteTypeById(Long id) {
        if (!typeRepository.existsById(id)) {
            throw new IllegalStateException("Type with id " + id + " does not exist");
        }
        typeRepository.deleteById(id);
    }
}
