package com.example.movieticket.repository;

import com.example.movieticket.model.Director_Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorActorRepository extends JpaRepository<Director_Actor, Long> {
}
