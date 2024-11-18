package com.example.movieticket.service;

import com.example.movieticket.model.Branch;
import com.example.movieticket.model.Chair;
import com.example.movieticket.repository.ChairRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChairService {
    private final ChairRepository chairRepository;
    public List<Chair> getAllChair() {return chairRepository.findAll();}
    public Optional<Chair> getChairById(Long id) {return chairRepository.findById(id);}
    public void addChair(Chair chair) {chairRepository.save(chair);}
    public void updateChair(@NotNull Chair chair) {
        Chair existingChair = chairRepository.findById(chair.getId())
                .orElseThrow(() -> new IllegalStateException("Chair with ID " + chair.getId() + " does not exist."));
        existingChair.setChair_name(chair.getChair_name());
        existingChair.setRowchair(chair.getRowchair());
        chairRepository.save(existingChair);
    }
    public void deleteChair(Long id) {
        if (!chairRepository.existsById(id)) {
            throw new IllegalStateException("Chair with ID " + id + " does not exist.");
        }
        chairRepository.deleteById(id);
    }
}
