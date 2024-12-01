package com.example.movieticket.service;

import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.repository.RoomScheduTimeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomScheduTimeService {
    @Autowired
    private RoomScheduTimeRepository roomScheduTimeRepository;

    // Tạo mới
    public RoomSchedu_Time create(RoomSchedu_Time roomScheduTime) {
        return roomScheduTimeRepository.save(roomScheduTime);
    }

    // Cập nhật
    public RoomSchedu_Time update(Long id, RoomSchedu_Time roomScheduTime) {
        if (roomScheduTimeRepository.existsById(id)) {
            roomScheduTime.setId(id); // Đảm bảo id không bị thay đổi
            return roomScheduTimeRepository.save(roomScheduTime);
        }
        return null; // Nếu không tìm thấy ID, trả về null
    }

    // Tìm theo ID
    public Optional<RoomSchedu_Time> findById(Long id) {
        return roomScheduTimeRepository.findById(id);
    }

    // Xóa theo ID
    public void delete(Long id) {
        roomScheduTimeRepository.deleteById(id);
    }

    // Tìm kiếm và phân trang
    public Page<RoomSchedu_Time> findBySearch(String searchTerm, int page, int size) {
        return roomScheduTimeRepository.findAll(PageRequest.of(page, size)); // Thêm điều kiện tìm kiếm nếu cần
    }

    public void saveOrUpdate(RoomSchedu_Time schedule) {
        roomScheduTimeRepository.save(schedule);
    }

    public List<RoomSchedu_Time> getSchedulesFromToday() {
        // Get today's date
        LocalDate today = LocalDate.now();
        // Fetch schedules from today onward
        return roomScheduTimeRepository.findByDateGreaterThanEqual(today);
    }

    public List<RoomSchedu_Time> getSchedulesForDate(LocalDate date) {
        return roomScheduTimeRepository.findByDateEqual(date);
    }

    public List<RoomSchedu_Time> getAll() {
        return roomScheduTimeRepository.findAll();
    }
}
