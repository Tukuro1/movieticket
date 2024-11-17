package com.example.movieticket.service;


import com.example.movieticket.model.Status_Chair;
import com.example.movieticket.repository.IStatus_ChairRepository;
import com.example.movieticket.repository.ITimeFrameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Status_ChairService {
    @Autowired
    private IStatus_ChairRepository istatus_chairRepository;


    public List<Status_Chair> getAllStatus_Chairs(){ return istatus_chairRepository.findAll();}
    public Status_Chair getStatus_ChairById(Long id)

    {
        Optional<Status_Chair> optionalStatus_Chair = istatus_chairRepository.findById(id);
        if(optionalStatus_Chair.isPresent()){
            return optionalStatus_Chair.get();
        }else{
            throw new RuntimeException("Status_Chair not found");
        }
    }

    public Status_Chair saveStatus_Chair (Status_Chair status_chair){ return istatus_chairRepository.save(status_chair);}
    public Status_Chair  createStatus_Chair (Status_Chair status_chair) { return istatus_chairRepository.save(status_chair);}



    public  void  updateStatus_Chair (Status_Chair status_chair) { istatus_chairRepository.save(status_chair);}
    public void deleteStatus_Chair (Long id) { istatus_chairRepository.deleteById(id);}
}
