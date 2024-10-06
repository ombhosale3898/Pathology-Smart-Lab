package com.example.pathalogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.TestOrder;

@Repository
@EnableJpaRepositories
public interface TestOrderRepo extends JpaRepository<TestOrder, Long>{
	
//			List<TestOrder> findByLabtechidRefLabtechId(Long userId);
	
	       List<TestOrder> findByPatientIdPatientId(Long patientId);

}