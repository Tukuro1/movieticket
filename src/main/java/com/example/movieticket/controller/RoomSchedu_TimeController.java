package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Room;
import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
            @RequestParam(defaultValue = "") String searchTerm, // Điều kiện tìm kiếm (nếu có)
            @RequestParam(defaultValue = "0") int page, // Trang hiện tại (mặc định trang đầu tiên)
            @RequestParam(defaultValue = "10") int size, // Số lượng bản ghi trên mỗi trang
            @RequestParam(defaultValue = "") String sort, // Sắp xếp theo (nếu có)
            Model model, @AuthenticationPrincipal UserDetails userDetails,
            @AuthenticationPrincipal OAuth2User oauth2User) {
        String role = null;
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
            if (userDetails.getAuthorities().size() > 0) {
                role = userDetails.getAuthorities().iterator().next().getAuthority();
            }
        }
        if (oauth2User != null) {
            model.addAttribute("username", oauth2User.getName());
            if (oauth2User.getAuthorities().size() > 0) {
                role = oauth2User.getAuthorities().iterator().next().getAuthority();
            }
        }


        // Lấy danh sách lịch chiếu từ service (có hỗ trợ phân trang và tìm kiếm)
        Page<RoomSchedu_Time> schedules = roomScheduTimeService.findBySearch(searchTerm, page, size);

        // Thêm dữ liệu vào model để truyền cho view
        model.addAttribute("schedules", schedules);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", schedules.getTotalPages());
        model.addAttribute("totalItems", schedules.getTotalElements());

        return "admin/schedule/index"; // Trả về view index
    }

    // Tạo lịch chiếu mới (hiển thị form thêm mới)
    @GetMapping("/create")
    public String create(Model model) {
        List<Room> rooms = roomService.getAllRoom();

        model.addAttribute("rooms", rooms);
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/schedule/edit"; // View để tạo lịch chiếu mới
    }

    // Sửa lịch chiếu (hiển thị form chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        RoomSchedu_Time schedule = roomScheduTimeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        model.addAttribute("schedule", schedule);
        List<Room> rooms = roomService.getAllRoom();
        model.addAttribute("rooms", rooms);
        List<Movie> movies = movieService.getAllMovies();
        model.addAttribute("movies", movies);
        return "admin/schedule/edit"; // View để chỉnh sửa lịch chiếu
    }

    // Lưu lịch chiếu (thêm hoặc cập nhật)
    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute RoomSchedu_Time schedule,
            @RequestParam Long roomId,
            @RequestParam Long movieId) {
        // Retrieve the room and movie objects based on the provided IDs
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Movie movie = movieService.getMovieById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Set the room and movie to the schedule object
        schedule.setRoom(room);
        schedule.setMovie(movie);
        System.out.println("id" + schedule.getId());
        // Save or update the schedule
        roomScheduTimeService.saveOrUpdate(schedule);

        // Redirect back to the schedule list page after saving
        return "redirect:/admin/schedule";
    }

    // Xóa lịch chiếu
    @GetMapping("/delete/{id}")
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
        RoomSchedu_Time schedule = roomScheduTimeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return ResponseEntity.ok(schedule);
    }

}
