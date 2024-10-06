package com.example.pathalogy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.pathalogy.entity.Patient;

@Repository
@EnableJpaRepositories
public interface PatientRepo extends JpaRepository<Patient, Long> {

	//Patient findByName(String name);

	Patient findByEmail(String email);
	
	
}

