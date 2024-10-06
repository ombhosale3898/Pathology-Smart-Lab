package com.example.pathalogy.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.FeedBack;

@Repository
public interface FeedBackRepo  extends JpaRepository<FeedBack, Long>{

	List<FeedBack> findByPatientIdPatientId(Long id);
}
