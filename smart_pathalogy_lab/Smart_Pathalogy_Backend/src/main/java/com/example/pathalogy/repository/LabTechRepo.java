package com.example.pathalogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pathalogy.entity.LabTech;

@Repository
public interface LabTechRepo extends JpaRepository<LabTech, Long> {

	LabTech findByEmail(String email);

	List<LabTech> findByFullName(String name);
}
