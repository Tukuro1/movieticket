package com.example.movieticket.repository;

import com.example.movieticket.model.Area;
import com.example.movieticket.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<Branch,Long> {
}
