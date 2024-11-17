package com.example.movieticket.service;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Branch;
import com.example.movieticket.repository.IAreaRepository;
import com.example.movieticket.repository.IBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    @Autowired
    private IBranchRepository ibranchRepository;


    public List<Branch> getAllBranchs(){ return ibranchRepository.findAll();}
    public Branch getBranchById(Long id)

    {
        Optional<Branch> optionalBranch = ibranchRepository.findById(id);
        if(optionalBranch.isPresent()){
            return optionalBranch.get();
        }else{
            throw new RuntimeException("area not found");
        }
    }
    public Branch saveBranch (Branch branch){ return ibranchRepository.save(branch);}
    public  Branch  createBranch (Branch branch){ return ibranchRepository.save(branch);}
    public  void  updateBranch (Branch branch) { ibranchRepository.save(branch);}
    public void deleteBranch (Long id) { ibranchRepository.deleteById(id);}
}
