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
import com.example.pathalogy.entity.FeedBack;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.FeedBackService;


@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {

	@Autowired
	
	private FeedBackService feedBackServiceRef;
	
	//-----------------------------------------------------------------------------------------
	
	@PostMapping("/writeFeedback/{id}")
	public ResponseEntity<?> createFeedBack(@RequestBody FeedBack generateFeedback,@PathVariable Long patientId,@PathVariable Long doctorId) {
		
			System.out.println(generateFeedback);
			return new ResponseEntity<>(feedBackServiceRef.saveFeedback(generateFeedback,patientId,doctorId), HttpStatus.OK);

	}
	//----------------------------------------------------------------------------------------
	
	@GetMapping("/getAllFeedback")
	public ResponseEntity<?> getAllFeedback() {

		try {
			return new ResponseEntity<>(feedBackServiceRef.getAllFeedBack(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	//-----------------------------------------------------------------------------------------
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPatientFeedbackByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(feedBackServiceRef.getFeedbackByPatientEmail(email), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//--------------------------------------------------------------------------------------------
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getFeedbackById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(feedBackServiceRef.getFeedbackById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//----------------------------------------------------------------------------------------------
	
	@PutMapping("/updatefeedback")
	public ResponseEntity<?> updateFeedbck(@RequestBody FeedBack updatedFeedback) {
		try {

			return new ResponseEntity<>(feedBackServiceRef.updateFeedback(updatedFeedback), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//----------------------------------------------------------------------------------------------
	
	
	@DeleteMapping("/deletefeedback/{id}")
	public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(feedBackServiceRef.deleteFeedback(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	//------------------------------------------------------------------------------------------------
	
}
