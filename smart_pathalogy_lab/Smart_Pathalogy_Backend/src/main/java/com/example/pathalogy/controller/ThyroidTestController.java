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
import com.example.pathalogy.entity.ThyroidTest;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.ThyroidTestService;

@RestController
@RequestMapping("/thyroidTest")
@CrossOrigin("*")
public class ThyroidTestController {

	@Autowired
	
	private ThyroidTestService thyroidTestServiceRef;
	
	//--------------------------------------------------------------------------------------------
	
	@PostMapping("/thyroidTestParam")
	public ResponseEntity<?> createThyroidTestParam(@RequestBody ThyroidTest createThyroidTest) {
		
		System.out.println(createThyroidTest);
		return new ResponseEntity<>(thyroidTestServiceRef.saveThyroidTest(createThyroidTest), HttpStatus.OK);

  }
	//-------------------------------------------------------------------------------------------
	
	@GetMapping("/getAllThyroidTestParam")
	public ResponseEntity<?> getAllThyroidTestParam() {

		try {
			return new ResponseEntity<>(thyroidTestServiceRef.getAllThyroidTestParamerets(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//------------------------------------------------------------------------------------------
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getThyroidTestParamById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(thyroidTestServiceRef.getThyroidTestById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//------------------------------------------------------------------------------------------
	
	@PutMapping("/updateThyroidTestParam")
	public ResponseEntity<?> updateBloodTestParam(@RequestBody ThyroidTest updatedThyroidTestParam) {
		try {

			return new ResponseEntity<>(thyroidTestServiceRef.updateThyroidTestParameter(updatedThyroidTestParam), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-----------------------------------------------------------------------------------------
	
	@DeleteMapping("deleteThyroidTestParam/{id}")
	public ResponseEntity<?> deleteThyroidTestParam(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(thyroidTestServiceRef.deleteThyroidTest(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-----------------------------------------------------------------------------------------
	
}
