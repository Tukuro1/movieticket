package com.example.movieticket.repository;

import com.example.movieticket.model.TypeChair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeChairRepository extends JpaRepository<TypeChair,Long> {
}
