package com.example.movieticket.service;

import com.example.movieticket.model.Room;
import com.example.movieticket.model.RowChair;
import com.example.movieticket.repository.IRoomRepository;
import com.example.movieticket.repository.IRowChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RowChairService {
    @Autowired
    private IRowChairRepository irowchairRepository;


    public List<RowChair> getAllRowChairs(){ return irowchairRepository.findAll();}
    public RowChair getRowChairById(Long id)

    {
        Optional<RowChair> optionalRowChair = irowchairRepository.findById(id);
        if(optionalRowChair.isPresent()){
            return optionalRowChair.get();
        }else{
            throw new RuntimeException("RowChair not found");
        }
    }

    public RowChair saveRowChair (RowChair rowchair){ return irowchairRepository.save(rowchair);}
    public RowChair  createRowChair (RowChair rowchair)
    { return irowchairRepository.save(rowchair);}



    public  void  updateRowChair (RowChair rowchair) { irowchairRepository.save(rowchair);}
    public void deleteRowChair (Long id) { irowchairRepository.deleteById(id);}
}