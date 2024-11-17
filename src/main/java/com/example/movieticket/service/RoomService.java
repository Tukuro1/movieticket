package com.example.movieticket.service;

import com.example.movieticket.model.Room;
import com.example.movieticket.repository.RoomRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    public List<Room> getAllRoom() {return roomRepository.findAll();}
    public Optional<Room> getRoomById(Long id) {return roomRepository.findById(id);}
    public void addRoom(Room room) {roomRepository.save(room);}
    public void updateRoom(@NotNull Room room) {
        Room existingRoom = roomRepository.findById(room.getId())
                .orElseThrow(() -> new IllegalStateException("Room with ID " + room.getId() + " does not exist."));
        existingRoom.setRoom_number(room.getRoom_number());
        existingRoom.setRow_count(room.getRow_count());
        existingRoom.setBranch(room.getBranch());
        roomRepository.save(existingRoom);
    }
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new IllegalStateException("Room with ID " + id + " does not exist.");
        }
        roomRepository.deleteById(id);
    }
}