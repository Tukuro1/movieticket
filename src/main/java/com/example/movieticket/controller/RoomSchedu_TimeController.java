package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Room;
import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.service.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/schedule")
public class RoomSchedu_TimeController {
    @Autowired
    private RoomScheduTimeService roomScheduTimeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MovieService movieService;

    // Hiển thị trang index với danh sách lịch chiếu, hỗ trợ tìm kiếm và phân trang
    @GetMapping
    public String index(
        @RequestParam(defaultValue = "") String searchTerm,  // Điều kiện tìm kiếm (nếu có)
        @RequestParam(defaultValue = "0") int page,         // Trang hiện tại (mặc định trang đầu tiên)
        @RequestParam(defaultValue = "10") int size,        // Số lượng bản ghi trên mỗi trang
        @RequestParam(defaultValue = "") String sort,       // Sắp xếp theo (nếu có)
        Model model) {

        // Lấy danh sách lịch chiếu từ service (có hỗ trợ phân trang và tìm kiếm)
        Page<RoomSchedu_Time> schedules = roomScheduTimeService.findBySearch(searchTerm, page, size);
        
        // Thêm dữ liệu vào model để truyền cho view
        model.addAttribute("schedules", schedules);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", schedules.getTotalPages());
        model.addAttribute("totalItems", schedules.getTotalElements());

        return "admin/schedule/index";  // Trả về view index
    }

    // Tạo lịch chiếu mới (hiển thị form thêm mới)
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("schedule", new RoomSchedu_Time());
        return "admin/schedule/edit";  // View để tạo lịch chiếu mới
    }

    // Xử lý form tạo lịch chiếu mới
    @PostMapping("/create")
    public String save(@ModelAttribute RoomSchedu_Time roomScheduTime) {
        roomScheduTimeService.create(roomScheduTime);  // Lưu lịch chiếu mới vào cơ sở dữ liệu
        return "redirect:/admin/schedule";  // Quay lại trang danh sách lịch chiếu
    }

    // Sửa lịch chiếu (hiển thị form chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        RoomSchedu_Time schedule = roomScheduTimeService.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        model.addAttribute("schedule", schedule);
        return "admin/schedule/edit";  // View để chỉnh sửa lịch chiếu
    }

    // Lưu lịch chiếu (thêm hoặc cập nhật)
    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute RoomSchedu_Time schedule,
                               @RequestParam Long roomId,
                               @RequestParam Long movieId) {
        Room room = roomService.getRoomById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        Movie movie = movieService.getMovieById(movieId).orElseThrow(() -> new RuntimeException("Movie not found"));

        schedule.setRoom(room);
        schedule.setMovie(movie);

        roomScheduTimeService.saveOrUpdate(schedule);
        return "redirect:/schedule/list"; // Chuyển hướng về danh sách lịch chiếu
    }

    // Xóa lịch chiếu
   @DeleteMapping("/deleteItem/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            roomScheduTimeService.delete(id);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<RoomSchedu_Time> getSchedule(@PathVariable Long id) {
        RoomSchedu_Time schedule = roomScheduTimeService.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
        return ResponseEntity.ok(schedule);
    }

}
