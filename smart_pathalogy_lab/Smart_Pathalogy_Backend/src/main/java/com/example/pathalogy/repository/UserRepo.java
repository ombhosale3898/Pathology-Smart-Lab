package com.example.pathalogy.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.stereotype.Repository;
import com.example.pathalogy.entity.User;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
//	User findByPassword(String password);
	

	
}
