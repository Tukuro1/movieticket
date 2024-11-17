package com.example.movieticket.service;

import com.example.movieticket.model.Chair;

import com.example.movieticket.repository.IChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChairService {
    @Autowired
    private IChairRepository ichairRepository;


    public List<Chair> getAllChairs(){ return ichairRepository.findAll();}
    public Chair getChairById(Long id)

    {
        Optional<Chair> optionalChair = ichairRepository.findById(id);
        if(optionalChair.isPresent()){
            return optionalChair.get();
        }else{
            throw new RuntimeException("Chair not found");
        }
    }

    public Chair saveChair (Chair chair){ return ichairRepository.save(chair);}
    public Chair  createChair (Chair chair) { return ichairRepository.save(chair);}



    public  void  updateChair (Chair chair) { ichairRepository.save(chair);}
    public void deleteChair (Long id) { ichairRepository.deleteById(id);}
}
