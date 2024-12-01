package com.example.movieticket.controller;

import com.example.movieticket.model.*;
import com.example.movieticket.service.*;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class PaymentController {

        @Autowired
        private RoomScheduTimeService roomScheduTimeService;

        @Autowired
        private StatusChairService status_chairService;

        @Autowired
        private ChairService chairService;

        @Autowired
        private BillService billService;

        @Autowired
        private TicketService ticketService;

        @Autowired
        private UserService userService;

        @Autowired
        private VoucherService voucherService;

        @GetMapping("/payment/save")
        @ResponseBody
        public void paymentSave(@RequestParam Long scheduleId, @RequestParam Long voucherCode,
                        @RequestParam List<Long> seatIds,
                        @AuthenticationPrincipal UserDetails userDetails) {
                RoomSchedu_Time schedu_Time = roomScheduTimeService.findById(scheduleId)
                                .orElseThrow(() -> new IllegalStateException("Schedule not found"));
                List<Chair> chairs = chairService.getChairByIds(seatIds);
                float total = schedu_Time.getTicketPrice() * chairs.size();
                for (Chair chair : chairs) {
                        if (chair.getChair_type() != null && chair.getChair_type().getPriceMore() != 0) {
                                total += chair.getChair_type().getPriceMore();
                        }
                }
                Voucher voucher = voucherService.getVouvherById(voucherCode).orElse(null);

                // Tạo và lưu Bill
                Bill bill = new Bill();
                bill.setDatebill(new Date()); // Ngày tạo hóa đơn
                User user = this.userService.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new IllegalStateException("User not found"));
                bill.setUser(user);
                bill.setStatus(0); // 1: Đ ã thanh toán
                bill.setTotalPrice(total);

                if (voucher != null) {
                        bill.setVoucher(voucher);
                        voucher.setQuantity(voucher.getQuantity() - 1);
                        bill.setTotalPrice(total - voucher.getDiscount() * total / 100);
                }
                billService.save(bill); // Lưu Bill vào database
                voucherService.updateVoucher(voucher);
                List<Status_Chair> statusChairs = new ArrayList<>();
                List<Ticket> tickets = new ArrayList<>();

                // Tạo và lưu các Ticket tương ứng
                for (Chair chair : chairs) {
                        // Tạo mới Status_Chair
                        Status_Chair newStatusChair = new Status_Chair();
                        newStatusChair.setChair(chair);
                        newStatusChair.setStatus("booked"); // Đặt ghế đã được đặt
                        newStatusChair.setRoomschedu_time(schedu_Time);
                        statusChairs.add(newStatusChair);

                        // Tạo Ticket mới
                        Ticket ticket = new Ticket();
                        ticket.setPrice(schedu_Time.getTicketPrice()
                                        + (chair.getChair_type() != null ? chair.getChair_type().getPriceMore() : 0));
                        ticket.setStatus_chair(newStatusChair);
                        ticket.setBill(bill); // Liên kết ticket với hóa đơn
                        tickets.add(ticket);
                }
                this.status_chairService.addStatusChairs(statusChairs);
                this.ticketService.saveTickets(tickets);
        }
}
