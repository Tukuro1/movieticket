package com.example.movieticket.service;

import com.example.movieticket.model.Branch;
import com.example.movieticket.repository.BranchRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    public List<Branch> getAllBranch() {return branchRepository.findAll();}
    public Optional<Branch> getBranchById(Long id) {return branchRepository.findById(id);}
    public void addBranch(Branch branch) {branchRepository.save(branch);}
    public void updateBranch(@NotNull Branch branch) {
        Branch existingBranch = branchRepository.findById(branch.getId())
                .orElseThrow(() -> new IllegalStateException("Branch with ID " + branch.getId() + " does not exist."));
        existingBranch.setAddress(branch.getAddress());
        existingBranch.setHotlline(branch.getHotlline());
        existingBranch.setArea(branch.getArea());
        branchRepository.save(existingBranch);
    }
    public void deleteBranch(Long id) {
        if (!branchRepository.existsById(id)) {
            throw new IllegalStateException("Branch with ID " + id + " does not exist.");
        }
        branchRepository.deleteById(id);
    }
}
