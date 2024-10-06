package com.example.pathalogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{
	Doctor findByEmail(String email);

	List<Doctor> findByFullName(String name);
	
	
	
}
