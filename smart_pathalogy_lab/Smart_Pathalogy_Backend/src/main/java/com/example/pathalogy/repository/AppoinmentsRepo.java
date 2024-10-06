package com.example.pathalogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathalogy.entity.Appoinments;

@Repository
public interface AppoinmentsRepo extends JpaRepository<Appoinments, Long>{

	List<Appoinments> findByPatientidRefPatientId(Long userId);
	
}