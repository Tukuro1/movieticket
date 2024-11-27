package com.example.movieticket.service;

import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.repository.RoomScheduTimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomScheduTimeService {
    @Autowired
    private RoomScheduTimeRepository roomScheduTimeRepository;


    public List<RoomSchedu_Time> getAllRoomSchedu_Times(){ return roomScheduTimeRepository.findAll();}
    public RoomSchedu_Time getRoomSchedu_TimeById(Long id)

    {
        Optional<RoomSchedu_Time> optionalRoomSchedu_Time = roomScheduTimeRepository.findById(id);
        if(optionalRoomSchedu_Time.isPresent()){
            return optionalRoomSchedu_Time.get();
        }else{
            throw new RuntimeException("RoomSchedu_Time not found");
        }
    }

    public RoomSchedu_Time saveRoomSchedu_Time (RoomSchedu_Time roomschedu_time){ return roomScheduTimeRepository.save(roomschedu_time);}
    public RoomSchedu_Time  createRoomSchedu_Time (RoomSchedu_Time roomschedu_time) { return roomScheduTimeRepository.save(roomschedu_time);}



    public  void  updateRoomSchedu_Time (RoomSchedu_Time roomschedu_time) { roomScheduTimeRepository.save(roomschedu_time);}
    public void deleteRoomSchedu_Time (Long id) { roomScheduTimeRepository.deleteById(id);}

}
