package com.example.pathalogy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.Result;

@Repository
@EnableJpaRepositories
public interface ResultRepo extends JpaRepository<Result, Long>{

	List<Result> findByLabtechIdLabtechId(Long resultId);
}
