package com.example.movieticket.service;

import com.example.movieticket.model.TimeFrame;

import com.example.movieticket.repository.TimeFrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeFrameService {
    @Autowired
    private TimeFrameRepository timeFrameRepository;


    public List<TimeFrame> getAllTimeFrames(){ return timeFrameRepository.findAll();}
    public TimeFrame getTimeFrameById(Long id)

    {
        Optional<TimeFrame> optionalTimeFrame = timeFrameRepository.findById(id);
        if(optionalTimeFrame.isPresent()){
            return optionalTimeFrame.get();
        }else{
            throw new RuntimeException("TimeFrame not found");
        }
    }

    public TimeFrame saveTimeFrame (TimeFrame timeFrame){ return timeFrameRepository.save(timeFrame);}
    public TimeFrame  createTimeFrame (TimeFrame timeFrame) { return timeFrameRepository.save(timeFrame);}



    public  void  updateTimeFrame (TimeFrame timeFrame) { timeFrameRepository.save(timeFrame);}
    public void deleteTimeFrame (Long id) { timeFrameRepository.deleteById(id);}
}
