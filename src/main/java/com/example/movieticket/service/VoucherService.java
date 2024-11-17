package com.example.movieticket.service;

import com.example.movieticket.model.Voucher;
import com.example.movieticket.repository.VoucherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoucherService {
    private final VoucherRepository voucherRepository;
    public List<Voucher> getAllVoucher(){ return voucherRepository.findAll(); }
    public Optional<Voucher> getVouvherById(Long vouchercode){ return voucherRepository.findById(vouchercode); }
    public void addVoucher(Voucher voucher){voucherRepository.save(voucher);}
    public void updateVoucher(Voucher voucher){
        Voucher existingVoucher = voucherRepository.findById(voucher.getVouchercode())
                .orElseThrow(() -> new IllegalStateException("Voucher with id " + voucher.getVouchercode() + " not found"));
        existingVoucher.setVouchercode(voucher.getVouchercode());
        existingVoucher.setDiscount(voucher.getDiscount());
        existingVoucher.setStartday(voucher.getStartday());
        existingVoucher.setEndday(voucher.getEndday());
        existingVoucher.setQuantity(voucher.getQuantity());
        existingVoucher.setDetail(voucher.getDetail());
        existingVoucher.setListvoucher_customer(voucher.getListvoucher_customer());
        existingVoucher.setBill(voucher.getBill());
        voucherRepository.save(existingVoucher);
    }
    public void deleteVoucher(Long id){
        if(!voucherRepository.existsById(id)){
            throw new IllegalStateException("Voucher with id " + id + " not found");
        }
        voucherRepository.deleteById(id);
    }
}
