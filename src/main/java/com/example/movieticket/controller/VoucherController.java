package com.example.movieticket.controller;

import com.example.movieticket.model.Voucher;
import com.example.movieticket.service.UserService;
import com.example.movieticket.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String showVoucher(Model model) {
        List<Voucher> voucherList = voucherService.getAllVoucher();
        //bill
        return "/voucher/list";
    }
    @GetMapping("/{vouchercode}")
    public String showVoucherByCode(@PathVariable Long vouchercode, Model model) {
        model.addAttribute("voucher",voucherService.getVouvherById(vouchercode)
                .orElseThrow(()-> new IllegalStateException("voucher not found")));
        return "/voucher/detail";
    }
}
