package com.example.pathalogy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pathalogy.entity.TestOrder;
import com.example.pathalogy.entity.TestOrderByDoctor;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.TestOrderRepoByDoctor;

@Service
public class TestOrderByDoctorService {

	@Autowired
	
	private TestOrderRepoByDoctor testOrderRepoByDoctor;
	
	 public TestOrderByDoctor registerTestOrder(TestOrderByDoctor testOrder) {
	        // You can add more business logic here if needed
	        return testOrderRepoByDoctor.save(testOrder);
	    }
	 
		public List<TestOrderByDoctor> getAllTest() {

			List<TestOrderByDoctor> testlist = testOrderRepoByDoctor.findAll();

			if (testlist.size() != 0) {

				return testlist;
			} else {

				throw new CustomException("The Given Test is Not Found");
			}
		}
	
}
