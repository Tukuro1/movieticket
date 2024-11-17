package com.example.movieticket.service;

import com.example.movieticket.model.Director_Actor;
import com.example.movieticket.repository.DirectorActorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DirectorActorService {
    private final DirectorActorRepository directorActorRepository;
    public List<Director_Actor> getAllDirectorActor() {return directorActorRepository.findAll();}
    public Optional<Director_Actor> getDirectorActorById(Long id) {return directorActorRepository.findById(id);}
    public Director_Actor addDirectorActor(Director_Actor director_actor) {return directorActorRepository.save(director_actor);}
    public Director_Actor updateDirectorActor(@NotNull Director_Actor director_actor) {
        Director_Actor existingDirectorActor = directorActorRepository.findById(director_actor.getId())
                .orElseThrow(()-> new IllegalArgumentException("Director actor with id " + director_actor.getId() + "does not exist"));
        existingDirectorActor.setName(director_actor.getName());
        existingDirectorActor.setDetail(director_actor.getDetail());
        existingDirectorActor.setAge(director_actor.getAge());
        return directorActorRepository.save(existingDirectorActor);
    }
    public void deleteDirectorActorById(Long id) {
        if(directorActorRepository.existsById(id)) {
            throw new IllegalStateException("Director actor with id " + id + " dose not exist");
        }
        directorActorRepository.deleteById(id);
    }
}
