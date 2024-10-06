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
import com.example.pathalogy.entity.BloodTest;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.BloodTestService;

@RestController
@RequestMapping("/bloodTest")
@CrossOrigin("*")

public class BloodTestController {

	@Autowired
	private BloodTestService bloodTestServiceRef;
	
	//------------------------------------------------------------------------------------------
	
	@PostMapping("/createbloodTestParam")
	public ResponseEntity<?> createBloodTestParam(@RequestBody BloodTest createBloodTest) {
		
		System.out.println(createBloodTest);
		return new ResponseEntity<>(bloodTestServiceRef.saveBloodTest(createBloodTest), HttpStatus.OK);

  }
	
   //-------------------------------------------------------------------------------------------
	
	@GetMapping("/getAllBloodTestParam")
	public ResponseEntity<?> getAllBloodTestParam() {

		try {
			return new ResponseEntity<>(bloodTestServiceRef.getAllBloodTestParamerets(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-------------------------------------------------------------------------------------------
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getBloodTestParamById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(bloodTestServiceRef.getBloodTestById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-----------------------------------------------------------------------------------------
	
	
	@PutMapping("/updateBloodTestParam")
	public ResponseEntity<?> updateBloodTestParam(@RequestBody BloodTest updatedBloodTestParam) {
		try {

			return new ResponseEntity<>(bloodTestServiceRef.updateBloodTestParameter(updatedBloodTestParam), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-----------------------------------------------------------------------------------------
	
	@DeleteMapping("/deleteBloodTestParam/{id}")
	public ResponseEntity<?> deleteBloodTestParam(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(bloodTestServiceRef.deleteBloodTest(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-----------------------------------------------------------------------------------------
	
}
