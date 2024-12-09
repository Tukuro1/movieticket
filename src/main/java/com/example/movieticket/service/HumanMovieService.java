package com.example.movieticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.movieticket.model.Human_Movie;
import com.example.movieticket.repository.HumanMovieRepository;

@Service
public class HumanMovieService {
    @Autowired
    private HumanMovieRepository humanMovieRepository;

    public List<Human_Movie> getAllHuman_Movie() {
        return humanMovieRepository.findAll();
    }

    public List<Human_Movie> getHuman_MovieByIds(List<Long> ids) {
        return humanMovieRepository.findByIdIn(ids);
    }

    public void deleteHuman_Movie(Human_Movie human_movie) {
        humanMovieRepository.delete(human_movie);
    }
}
