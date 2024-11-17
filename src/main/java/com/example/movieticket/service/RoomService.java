package com.example.movieticket.service;


import com.example.movieticket.model.Room;
import com.example.movieticket.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private IRoomRepository iroomRepository;


    public List<Room> getAllRooms(){ return iroomRepository.findAll();}
    public Room getRoomById(Long id)

    {
        Optional<Room> optionalRoom = iroomRepository.findById(id);
        if(optionalRoom.isPresent()){
            return optionalRoom.get();
        }else{
            throw new RuntimeException("room not found");
        }
    }

    public Room saveRoom (Room room){ return iroomRepository.save(room);}
    public Room  createRoom (Room room){ return iroomRepository.save(room);}
    public  void  updateRoom (Room room) { iroomRepository.save(room);}
    public void deleteRoom (Long id) { iroomRepository.deleteById(id);}
}