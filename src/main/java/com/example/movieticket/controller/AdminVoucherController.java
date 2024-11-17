package com.example.movieticket.controller;

import com.example.movieticket.model.Voucher;
import com.example.movieticket.service.UserService;
import com.example.movieticket.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/voucher")
public class AdminVoucherController {
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String showVoucher(Model model) {
        List<Voucher> voucher = voucherService.getAllVoucher();
        //bill
        return "/admin/voucher/list";
    }
    @GetMapping("/{vouchercode}")
    public String showVoucherByCode(@PathVariable Long vouchercode, Model model) {
        model.addAttribute("voucher",voucherService.getVouvherById(vouchercode)
                .orElseThrow(()-> new IllegalStateException("voucher not found")));
        return "/admin/voucher/detail";
    }
    @GetMapping("/add")
    public String addVoucher(Model model) {
        model.addAttribute("voucher", new Voucher());
        return "/admin/voucher/add";
    }
    @PostMapping("/add")
    public String addVoucher(@Valid @ModelAttribute Voucher voucher, BindingResult result) {
        if(result.hasErrors()) {
            return "/admin/voucher/add";
        }
        return "redirect:/admin/voucher/list";
    }
    @GetMapping("/edit/{vouchercode}")
    public String editVoucher(@PathVariable Long vouchercode, Model model) {
        Voucher voucher = voucherService.getVouvherById(vouchercode)
                .orElseThrow(()-> new IllegalArgumentException("Invalid voucher code" + vouchercode));
        model.addAttribute("voucher", voucher);
        //bill
        return "/admin/voucher/edit";
    }
    @PostMapping("/edit/{vouchercode}")
    public String editVoucher(@Valid Voucher voucher, BindingResult result, @PathVariable Long vouchercode, Model model) {
        if(result.hasErrors()) {
            voucher.setVouchercode(vouchercode);
            return "/admin/voucher/edit";
        }
        voucherService.updateVoucher(voucher);
        model.addAttribute("voucher", voucher);
        return "/admin/voucher/list";
    }
    @GetMapping("/deleta/{vouchercode}")
    public String deleteVoucher(@PathVariable Long vouchercode) {
        voucherService.getVouvherById(vouchercode)
                .orElseThrow(()-> new IllegalArgumentException("Invalid voucher code" + vouchercode));
        voucherService.deleteVoucher(vouchercode);
        return "redirect:/admin/voucher/list";
    }

}
