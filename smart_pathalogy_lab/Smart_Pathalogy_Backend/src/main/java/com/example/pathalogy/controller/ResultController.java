package com.example.pathalogy.controller;

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
import com.example.pathalogy.entity.Result;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.ResultService;

@RestController
@RequestMapping("/result")
@CrossOrigin("*")
public class ResultController {

	private ResultService resultServiceRef;
	
	//-------------------------------------------------------------------------------------
	
	
	@PostMapping("/result")
	public ResponseEntity<?> generateResult(@RequestBody Result createResult) {
		
			System.out.println(createResult);
			return new ResponseEntity<>(resultServiceRef.generateResult(createResult), HttpStatus.OK);

	}
	//---------------------------------------------------------------------------------------
	
	@GetMapping("/getAllResults")
	public ResponseEntity<?> getAllResukts() {

		try {
			return new ResponseEntity<>(resultServiceRef.getAllResult(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	//----------------------------------------------------------------------------------------
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPatientResultByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(resultServiceRef.getResultByPatientEmail(email), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//-----------------------------------------------------------------------------------------
	
	@GetMapping("/id{id}")
	public ResponseEntity<?> getPatientResultById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(resultServiceRef.getResultById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-----------------------------------------------------------------------------------------
	
	@PutMapping("/updateResult")
	public ResponseEntity<?> updatePatientResult(@RequestBody Result updatedResult) {
		try {

			return new ResponseEntity<>(resultServiceRef.updateResult(updatedResult), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deletePatientResult(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(resultServiceRef.deleteResult(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//-------------------------------------------------------------------------------------------
	
}
