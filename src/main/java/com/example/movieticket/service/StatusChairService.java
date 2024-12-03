package com.example.movieticket.service;

import com.example.movieticket.model.Status_Chair;
import com.example.movieticket.repository.StatusChairRepository;
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
public class StatusChairService {
    private final StatusChairRepository statusChairRepository;

    public List<Status_Chair> getAllStatusChair() {
        return statusChairRepository.findAll();
    }

    public Optional<Status_Chair> getStatusChairById(Long id) {
        return statusChairRepository.findById(id);
    }

    public void addStatusChair(Status_Chair statusChair) {
        statusChairRepository.save(statusChair);
    }

    public void updateStatusChair(@NotNull Status_Chair statusChair) {
        Status_Chair existingStatusChair = statusChairRepository.findById(statusChair.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "TypeChair with ID " + statusChair.getId() + " does not exist."));
        existingStatusChair.setStatus(statusChair.getStatus());
        existingStatusChair.setRoomschedu_time(statusChair.getRoomschedu_time());
        // existingStatusChair.setChair_type(statusChair.getChair_type());
        statusChairRepository.save(existingStatusChair);
    }

    public void deleteStatusChair(Long id) {
        if (!statusChairRepository.existsById(id)) {
            throw new IllegalStateException("TypeChair with ID " + id + " does not exist.");
        }
        statusChairRepository.deleteById(id);
    }

    public void addStatusChairs(List<Status_Chair> statusChairs) {
        statusChairRepository.saveAll(statusChairs);
    }
}
