package com.example.pathalogy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathalogy.entity.TestOrderByDoctor;

@Repository
public interface TestOrderRepoByDoctor extends JpaRepository<TestOrderByDoctor, Long>{

}
