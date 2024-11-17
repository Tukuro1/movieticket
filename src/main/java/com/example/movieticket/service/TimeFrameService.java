package com.example.movieticket.service;

import com.example.movieticket.model.TimeFrame;
import com.example.movieticket.repository.TimeFrameRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TimeFrameService {
    private final TimeFrameRepository timeFrameRepository;
    private List<TimeFrame> getAllTimeFrame(){return timeFrameRepository.findAll();};
    private Optional<TimeFrame> getTimeFrameById(Long id){return timeFrameRepository.findById(id);};
    public TimeFrame addTimeFrame(TimeFrame timeFrame){return timeFrameRepository.save(timeFrame);};
    public TimeFrame updateTimeFrame(@NotNull TimeFrame timeFrame){
        TimeFrame existingTimeFrame = timeFrameRepository.findById(timeFrame.getId())
                .orElseThrow(()-> new IllegalStateException("TimeFrame not found" + timeFrame.getId()+"does not exist"));
        existingTimeFrame.setStarttime(timeFrame.getStarttime());
        existingTimeFrame.setEndtime(timeFrame.getEndtime());
        existingTimeFrame.setRoomschedu_time(timeFrame.getRoomschedu_time());
        return timeFrameRepository.save(existingTimeFrame);
    }
    public void deleteTimeFrame(Long id){
        if (!timeFrameRepository.existsById(id)) {
            throw new IllegalStateException("TimeFrame with ID " + id + " does not exist.");
        }
        timeFrameRepository.deleteById(id);
    }
}
