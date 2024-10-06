package com.example.pathalogy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.Billing;


@Repository
public interface BillingRepo extends JpaRepository<Billing, Long>{

	
}
