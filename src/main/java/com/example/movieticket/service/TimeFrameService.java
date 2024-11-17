package com.example.movieticket.service;

import com.example.movieticket.model.RoomSchedu_Day;
import com.example.movieticket.model.TimeFrame;

import com.example.movieticket.repository.ITimeFrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeFrameService {
    @Autowired
    private ITimeFrameRepository itimeFrameRepository;


    public List<TimeFrame> getAllTimeFrames(){ return itimeFrameRepository.findAll();}
    public TimeFrame getTimeFrameById(Long id)

    {
        Optional<TimeFrame> optionalTimeFrame = itimeFrameRepository.findById(id);
        if(optionalTimeFrame.isPresent()){
            return optionalTimeFrame.get();
        }else{
            throw new RuntimeException("TimeFrame not found");
        }
    }

    public TimeFrame saveTimeFrame (TimeFrame timeFrame){ return itimeFrameRepository.save(timeFrame);}
    public TimeFrame  createTimeFrame (TimeFrame timeFrame) { return itimeFrameRepository.save(timeFrame);}



    public  void  updateTimeFrame (TimeFrame timeFrame) { itimeFrameRepository.save(timeFrame);}
    public void deleteTimeFrame (Long id) { itimeFrameRepository.deleteById(id);}
}
