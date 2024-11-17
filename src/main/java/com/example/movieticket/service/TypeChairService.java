package com.example.movieticket.service;

import com.example.movieticket.model.TypeChair;
import com.example.movieticket.repository.TypeChairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeChairService {
    @Autowired
    private TypeChairRepository itypeChairRepository;


    public List<TypeChair> getAllTypeChairs(){ return itypeChairRepository.findAll();}
    public TypeChair getTypeChairById(Long id)

    {
        Optional<TypeChair> optionalTypeChair = itypeChairRepository.findById(id);
        if(optionalTypeChair.isPresent()){
            return optionalTypeChair.get();
        }else{
            throw new RuntimeException("TypeChair not found");
        }
    }

    public TypeChair saveTypeChair (TypeChair typeChair){ return itypeChairRepository.save(typeChair);}
    public TypeChair  createTypeChair (TypeChair typeChair) { return itypeChairRepository.save(typeChair);}



    public  void  updateTypeChair (TypeChair typeChair) { itypeChairRepository.save(typeChair);}
    public void deleteTypeChair (Long id) { itypeChairRepository.deleteById(id);}
}
