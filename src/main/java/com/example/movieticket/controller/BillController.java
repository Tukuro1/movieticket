package com.example.movieticket.controller;

import com.example.movieticket.model.Bill;
import com.example.movieticket.model.Chair;
import com.example.movieticket.model.Movie;
import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.model.Status_Chair;
import com.example.movieticket.model.Ticket;
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
@RequestMapping("/admin/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private TicketService ticketService;

    // Hiển thị trang index với danh sách lịch chiếu, hỗ trợ tìm kiếm và phân trang
    @GetMapping
    public String index(
            @RequestParam(defaultValue = "") String searchTerm, // Điều kiện tìm kiếm (nếu có)
            @RequestParam(defaultValue = "0") int page, // Trang hiện tại (mặc định trang đầu tiên)
            @RequestParam(defaultValue = "10") int size, // Số lượng bản ghi trên mỗi trang
            @RequestParam(defaultValue = "date") String sort, // Sắp xếp theo (nếu có)
            Model model, @RequestParam List<Long> seatIds,
            @AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal OAuth2User oauth2User) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        if (oauth2User != null) {
            model.addAttribute("username", oauth2User.getName());
        }
        if (page < 0) {
            page = 0;
        }
        // Lấy danh sách lịch chiếu từ service (có hỗ trợ phân trang và tìm kiếm)
        Page<Bill> bills = billService.findBySearch(searchTerm, page, size, sort);

        // Thêm dữ liệu vào model để truyền cho view
        model.addAttribute("bills", bills);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bills.getTotalPages());
        model.addAttribute("totalItems", bills.getTotalElements());

        return "admin/bill/index"; // Trả về view index
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Bill bill = billService.findById(id).orElseThrow(() -> new RuntimeException("bill not found"));
        model.addAttribute("bill", bill);
        List<Ticket> tickets = this.ticketService.getAllByBillId(id);
        model.addAttribute("tickets", tickets);
        Movie movie = tickets.stream()
                .findFirst()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getRoomschedu_time)
                .map(RoomSchedu_Time::getMovie)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        RoomSchedu_Time roomScheduTimes = tickets.stream()
                .findFirst()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getRoomschedu_time)
                .orElseThrow(() -> new RuntimeException("getRoomschedu_time not found"));
        model.addAttribute("roomScheduTimes", roomScheduTimes);
        List<Chair> chairs = tickets.stream()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getChair)
                .toList();
        model.addAttribute("chairs", chairs);
        return "admin/bill/detail";
    }

    @GetMapping("/my-bill-detail/{id}")
    public String myDetail(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        Bill bill = billService.findById(id).orElseThrow(() -> new RuntimeException("bill not found"));
        model.addAttribute("bill", bill);
        List<Ticket> tickets = this.ticketService.getAllByBillId(id);
        model.addAttribute("tickets", tickets);
        Movie movie = tickets.stream()
                .findFirst()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getRoomschedu_time)
                .map(RoomSchedu_Time::getMovie)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        RoomSchedu_Time roomScheduTimes = tickets.stream()
                .findFirst()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getRoomschedu_time)
                .orElseThrow(() -> new RuntimeException("getRoomschedu_time not found"));
        model.addAttribute("roomScheduTimes", roomScheduTimes);
        List<Chair> chairs = tickets.stream()
                .map(Ticket::getStatus_chair)
                .map(Status_Chair::getChair)
                .toList();
        model.addAttribute("chairs", chairs);
        return "home/ticket-details";
    }

    @GetMapping("/my-bills")
    public String myBills(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        List<Bill> bills = billService.findAll().stream()
                .filter(bill -> bill.getUser().getUsername().equals(userDetails.getUsername())).toList();
        model.addAttribute("bills", bills);
        return "home/my-tickets";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Bill> getBill(@PathVariable Long id) {
        Bill bill = billService.findById(id).orElseThrow(() -> new RuntimeException("bill not found"));
        return ResponseEntity.ok(bill);
    }

    @GetMapping("complete/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> completeItem(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            billService.complete(id);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
