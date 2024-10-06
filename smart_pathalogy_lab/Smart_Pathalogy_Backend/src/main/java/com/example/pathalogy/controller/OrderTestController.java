package com.example.pathalogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pathalogy.entity.TestOrder;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.TestOrderService;

@RestController
@RequestMapping("/testOrder")
@CrossOrigin("*")

public class OrderTestController {

	@Autowired
	private TestOrderService testOrderService;
	
	
	 @PostMapping("/registerOrder")
	    public ResponseEntity<TestOrder> registerOrder(@RequestBody TestOrder testOrder) {
	        TestOrder savedOrder = testOrderService.registerTestOrder(testOrder);
	        return ResponseEntity.ok(savedOrder);
	    }
	

	
	//----------------------------------------------------------------------------------------------
	@PostMapping("/register")
	public ResponseEntity<?> createOrder(@RequestBody TestOrder createTestOrder) {
		
			return new ResponseEntity<>(testOrderService.createOrder(createTestOrder), HttpStatus.OK);

	}
	
	//----------------------------------------------------------------------------------------------
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<?> getAllTestOrder() {

		try {
			return new ResponseEntity<>(testOrderService.getAllTest(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-----------------------------------------------------------------------------------------------
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPatientOrderByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(testOrderService.getOrderByPatientEmail(email), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-----------------------------------------------------------------------------------------------
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getPatientOrderById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(testOrderService.getOrderById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//----------------------------------------------------------------------------------------------
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUserOrder(@RequestBody TestOrder updatedOrder) {
		try {

			return new ResponseEntity<>(testOrderService.update(updatedOrder), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	
	@DeleteMapping("deleteOrder/{id}")
	public ResponseEntity<?> deleteUserOrder(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(testOrderService.deleteTestOrder(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//----------------------------------------------------------------------------------------------
	
	 @PutMapping("/status/in-process")
	    public ResponseEntity<TestOrder> markAsInProcess(@RequestBody TestOrder updateStatus) {
	        try {
	        	
	            TestOrder updatedOrder = testOrderService.updateStatus(updateStatus, "In Process");
	            
	            return ResponseEntity.ok(updatedOrder);
	            
	        } catch (CustomException e) {
	        	
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 //------------------------------------------------------------------------------------------
	 
	 @PutMapping("/{id}/status/completed")
	    public ResponseEntity<TestOrder> markAsCompleted(@RequestBody TestOrder updateStatus) {
	        try {
	        	
	            TestOrder updatedOrder = testOrderService.updateStatus(updateStatus, "Completed");
	            
	            return ResponseEntity.ok(updatedOrder);
	            
	        } catch (CustomException e) {
	        	
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	//------------------------------------------------------------------------------------------
	 
	
}
