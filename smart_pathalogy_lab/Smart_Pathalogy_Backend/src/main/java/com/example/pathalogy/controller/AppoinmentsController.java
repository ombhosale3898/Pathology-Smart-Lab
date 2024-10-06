package com.example.pathalogy.controller;

import java.time.LocalDate;

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
import com.example.pathalogy.entity.Appoinments;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.service.AppoinmentsService;

@RestController
@RequestMapping("/appoinment")
@CrossOrigin("*")
public class AppoinmentsController {

	@Autowired
	private AppoinmentsService appoinmentsServiceRef;
	
	//-----------------------------------------------------------------------------------------
	
//	@PostMapping("/registerAppoinment/{id}/{date}")
//	public ResponseEntity<?> registerAppoinment(@PathVariable Long patientid, @RequestBody User doctorname,@PathVariable LocalDate date) {
//		try {
//
//			
//			return new ResponseEntity<>(appoinmentsServiceRef.createAppointment(patientid,doctorname,date), HttpStatus.OK);
//
//		} catch (CustomException e) {
//
//			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//
//	}
	//--------------------------------------------------------------------------------------------
	@GetMapping("/getAllAppoinments")
	
	public ResponseEntity<?> getAllAppoinments() {

		try {
			return new ResponseEntity<>(appoinmentsServiceRef.getAllAppoinments(), HttpStatus.OK);
		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	//---------------------------------------------------------------------------------------
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getAppoinmentByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(appoinmentsServiceRef.getAppoinmentByPatientEmail(email), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//---------------------------------------------------------------------------------------
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserAppoinmentById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(appoinmentsServiceRef.getAppoinmentById(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	
	//---------------------------------------------------------------------------------------
	
	@PutMapping("/cancelAppoinment/{id}")
	public ResponseEntity<?> cancelAppoinment(@PathVariable Long appoinmentId) {
		try {

			return new ResponseEntity<>(appoinmentsServiceRef.cancalAppoinment(appoinmentId), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	//----------------------------------------------------------------------------------------
	
	@PutMapping("/confirmAppoinment/{id}")
	public ResponseEntity<?> comfirmAppoinment(@PathVariable Long appoinmentId) {
		try {

			return new ResponseEntity<>(appoinmentsServiceRef.completeAppoinment(appoinmentId), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	@PutMapping("/updateAppoinment")
	public ResponseEntity<?> updateUserAppoinment(@RequestBody Appoinments updatedAppoinment) {
		try {

			return new ResponseEntity<>(appoinmentsServiceRef.updateAppoinment(updatedAppoinment), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePatientAppoinment(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(appoinmentsServiceRef.deleteAppoinment(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//--------------------------------------------------------------------------------------
}