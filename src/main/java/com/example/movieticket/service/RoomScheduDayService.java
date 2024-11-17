package com.example.movieticket.service;

import com.example.movieticket.model.RoomSchedu_Day;
import com.example.movieticket.repository.RoomScheduDayRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomScheduDayService {
    private final RoomScheduDayRepository roomScheduDayRepository;
    public List<RoomSchedu_Day> getAllRoomScheduDay() {return roomScheduDayRepository.findAll();}
    public Optional<RoomSchedu_Day> getRoomScheduDayById(Long id) {return roomScheduDayRepository.findById(id);}
    public RoomSchedu_Day addRoomScheduDay(RoomSchedu_Day roomScheduDay) {return roomScheduDayRepository.save(roomScheduDay);}
    public RoomSchedu_Day updateRoomScheduDay(@NotNull RoomSchedu_Day roomScheduDay) {
        RoomSchedu_Day existingRoomScheduDay = roomScheduDayRepository.findById(roomScheduDay.getId())
                .orElseThrow(()-> new IllegalStateException("RoomSchedu_Day with ID " + roomScheduDay.getId() + " does not exist."));
        existingRoomScheduDay.setDateshow(roomScheduDay.getDateshow());
        existingRoomScheduDay.setRoom(roomScheduDay.getRoom());
        existingRoomScheduDay.setRoomschedu_time(roomScheduDay.getRoomschedu_time());
        return roomScheduDayRepository.save(existingRoomScheduDay);
    }
    public void deleteRoomScheduDayById(Long id) {
        if (!roomScheduDayRepository.existsById(id)) {
            throw new IllegalStateException("RoomScheduDay with id " + id + " does not exist");
        }
        roomScheduDayRepository.deleteById(id);
    }

}
