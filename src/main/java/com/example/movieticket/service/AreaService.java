package com.example.movieticket.service;


import com.example.movieticket.model.Area;
import com.example.movieticket.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {
    @Autowired
    private AreaRepository iareaRepository;


    public List<Area> getAllAreas(){ return iareaRepository.findAll();}
    public Area getAreaById(Long id)

    {
        Optional<Area> optionalArea = iareaRepository.findById(id);
        if(optionalArea.isPresent()){
            return optionalArea.get();
        }else{
            throw new RuntimeException("area not found");
        }
    }

    public Area saveArea (Area area){ return iareaRepository.save(area);}
    public Area  createArea (Area area){ return iareaRepository.save(area);}
    public  void  updateArea (Area area) { iareaRepository.save(area);}
    public void deleteArea (Long id) { iareaRepository.deleteById(id);}
}
