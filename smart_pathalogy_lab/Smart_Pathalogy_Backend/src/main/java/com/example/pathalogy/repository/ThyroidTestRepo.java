package com.example.pathalogy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.ThyroidTest;

@Repository
public interface ThyroidTestRepo extends JpaRepository<ThyroidTest, Long>{

}
