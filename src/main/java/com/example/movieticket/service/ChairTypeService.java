package com.example.movieticket.service;

import com.example.movieticket.model.Chair_Type;
import com.example.movieticket.repository.ChairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ChairTypeService {
    @Autowired
    private ChairTypeRepository ichair_typeRepository;


    public List<Chair_Type> getAllChair_Types(){ return ichair_typeRepository.findAll();}
    public Chair_Type getChair_TypeById(Long id)

    {
        Optional<Chair_Type> optionalChair_Type = ichair_typeRepository.findById(id);
        if(optionalChair_Type.isPresent()){
            return optionalChair_Type.get();
        }else{
            throw new RuntimeException("Chair_Type not found");
        }
    }

    public Chair_Type saveChair_Type (Chair_Type chair_type){ return ichair_typeRepository.save(chair_type);}
    public Chair_Type  createChair_Type (Chair_Type chair_type) { return ichair_typeRepository.save(chair_type);}



    public  void  updateChair_Type (Chair_Type chair_type) { ichair_typeRepository.save(chair_type);}
    public void deleteChair_Type (Long id) { ichair_typeRepository.deleteById(id);}
}
