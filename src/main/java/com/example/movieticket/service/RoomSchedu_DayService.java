package com.example.movieticket.service;


import com.example.movieticket.model.RoomSchedu_Day;
import com.example.movieticket.repository.IRoomSchedu_DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomSchedu_DayService {
    @Autowired
    private IRoomSchedu_DayRepository iroomschedu_dayRepository;


    public List<RoomSchedu_Day> getAllRoomSchedu_Days(){ return iroomschedu_dayRepository.findAll();}
    public RoomSchedu_Day getRoomSchedu_DayById(Long id)

    {
        Optional<RoomSchedu_Day> optionalRoomSchedu_Day = iroomschedu_dayRepository.findById(id);
        if(optionalRoomSchedu_Day.isPresent()){
            return optionalRoomSchedu_Day.get();
        }else{
            throw new RuntimeException("RoomSchedu_Day not found");
        }
    }

    public RoomSchedu_Day saveRoomSchedu_Day (RoomSchedu_Day roomschedu_day){ return iroomschedu_dayRepository.save(roomschedu_day);}
    public RoomSchedu_Day  createRoomSchedu_Day (RoomSchedu_Day roomschedu_day) { return iroomschedu_dayRepository.save(roomschedu_day);}



    public  void  updateRoomSchedu_Day (RoomSchedu_Day roomschedu_day) { iroomschedu_dayRepository.save(roomschedu_day);}
    public void deleteRoomSchedu_Day (Long id) { iroomschedu_dayRepository.deleteById(id);}
}
