package com.example.pathalogy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.entity.TestOrder;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.PatientRepo;
import com.example.pathalogy.repository.TestOrderRepo;

@Service
public class TestOrderService {

	@Autowired
	private TestOrderRepo testOrderRepo;
	
	@Autowired
	public PatientRepo patientRepoRef;
	
	//-------------------------------------------------------------------------------------------
	  public TestOrder registerTestOrder(TestOrder testOrder) {
	        // You can add more business logic here if needed
	        return testOrderRepo.save(testOrder);
	    }
	

	public List<TestOrder> getAllTest() {

		List<TestOrder> testlist = testOrderRepo.findAll();

		if (testlist.size() != 0) {

			return testlist;
		} else {

			throw new CustomException("The Given Test is Not Found");
		}
	}
	
	//-------------------------------------------------------------------------------------------
	
	public TestOrder getOrderById(Long id) {

		return testOrderRepo.findById(id).orElseThrow(() -> new CustomException("Id Not Found"));

	}
	
	//-------------------------------------------------------------------------------------------
	
	
	
	public List<TestOrder> getOrderByPatientEmail(String email) {
		
		Patient newUser = patientRepoRef.findByEmail(email);
		if (newUser != null) {
			return testOrderRepo.findByPatientIdPatientId(newUser.getPatientId());
		} else {
			throw new CustomException("Orders are not available for this Patient Email");
		}
	}
	
	
	//-------------------------------------------------------------------------------------------
	
	public TestOrder createOrder(TestOrder testOrder) {
		
        testOrder.setStatus("Pending"); // Default status
        return testOrderRepo.save(testOrder);
    }
	
	//-------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteTestOrder(Long id) {

		TestOrder testOrder = testOrderRepo.findById(id).orElseThrow(() -> new CustomException("Order is not found with given id"));

		if (testOrder != null) {

			testOrderRepo.delete(testOrder);
			return new ResponseEntity<>("Order Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Order");
		}
	}
	
	//-------------------------------------------------------------------------------------------
	
	
	public ResponseEntity<?> update(TestOrder updateorder) {

		TestOrder testOrder = testOrderRepo.findById(updateorder.getOrderId())
				.orElseThrow(() -> new CustomException("Given Order Id is  Not Found for Update"));

		if (testOrder != null) {

		    
			testOrder.setTestType(updateorder.getTestType());
			testOrder.setStatus(updateorder.getStatus());
			testOrder.setOrderDate(updateorder.getOrderDate());

			testOrderRepo.save(testOrder);
			return new ResponseEntity<>("Order Information Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the Order information");
		}
	}
	
	//-------------------------------------------------------------------------------------------
	
	 public TestOrder updateStatus(TestOrder updateStatus, String status) {
	        TestOrder testOrder = testOrderRepo.findById(updateStatus.getOrderId())
	                .orElseThrow(() -> new CustomException("TestOrder not found for this id "));
	        testOrder.setStatus(status);
	        return testOrderRepo.save(testOrder);
	    }
	//-------------------------------------------------------------------------------------------
	 
	 
}
