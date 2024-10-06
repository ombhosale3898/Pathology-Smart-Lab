package com.example.pathalogy.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.BloodTest;

@Repository
public interface BloodTestRepo extends JpaRepository<BloodTest, Long> {

	
}
