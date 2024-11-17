package com.example.movieticket.service;

import com.example.movieticket.model.Branch;
import com.example.movieticket.model.RowChair;
import com.example.movieticket.repository.RowChairRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RowChairService {
    private final RowChairRepository rowChairRepository;
    public List<RowChair> getAllRowChair() {return rowChairRepository.findAll();}
    public Optional<RowChair> getRowChairById(Long id) {return rowChairRepository.findById(id);}
    public void addRowChair(RowChair rowChair) {rowChairRepository.save(rowChair);}
    public void updateRowChair(@NotNull RowChair rowChair) {
        RowChair existingRowChair = rowChairRepository.findById(rowChair.getId())
                .orElseThrow(() -> new IllegalStateException("RowChair with ID " + rowChair.getId() + " does not exist."));
        existingRowChair.setRow_name(rowChair.getRow_name());
        existingRowChair.setChair_count(rowChair.getChair_count());
        existingRowChair.setRoom(rowChair.getRoom());
        rowChairRepository.save(existingRowChair);
    }
    public void deleteRowChair(Long id) {
        if (!rowChairRepository.existsById(id)) {
            throw new IllegalStateException("RowChair with ID " + id + " does not exist.");
        }
        rowChairRepository.deleteById(id);
    }
}