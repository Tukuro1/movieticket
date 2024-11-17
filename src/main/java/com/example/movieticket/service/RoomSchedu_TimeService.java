package com.example.movieticket.service;

import com.example.movieticket.model.RoomSchedu_Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomSchedu_TimeService {
    @Autowired
    private RoomSchedu_TimeRepository iroomschedu_timeRepository;


    public List<RoomSchedu_Time> getAllRoomSchedu_Times(){ return iroomschedu_timeRepository.findAll();}
    public RoomSchedu_Time getRoomSchedu_TimeById(Long id)

    {
        Optional<RoomSchedu_Time> optionalRoomSchedu_Time = iroomschedu_timeRepository.findById(id);
        if(optionalRoomSchedu_Time.isPresent()){
            return optionalRoomSchedu_Time.get();
        }else{
            throw new RuntimeException("RoomSchedu_Time not found");
        }
    }

    public RoomSchedu_Time saveRoomSchedu_Time (RoomSchedu_Time roomschedu_time){ return iroomschedu_timeRepository.save(roomschedu_time);}
    public RoomSchedu_Time  createRoomSchedu_Time (RoomSchedu_Time roomschedu_time) { return iroomschedu_timeRepository.save(roomschedu_time);}



    public  void  updateRoomSchedu_Time (RoomSchedu_Time roomschedu_time) { iroomschedu_timeRepository.save(roomschedu_time);}
    public void deleteRoomSchedu_Time (Long id) { iroomschedu_timeRepository.deleteById(id);}
}
