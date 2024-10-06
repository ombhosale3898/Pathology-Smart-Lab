package com.example.pathalogy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pathalogy.entity.Doctor;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.service.DoctorService;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

	@Autowired
	
	private DoctorService doctorServiceRef;
	
	// -----------------------------------------------------------------------------------

		@PostMapping("/register")
		public ResponseEntity<?> Register(@RequestBody Doctor doctorregister) {
			try {

				System.out.println(doctorregister);
				return new ResponseEntity<>(doctorServiceRef.registerUser(doctorregister), HttpStatus.OK);

			} catch (EmailNotFoundException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}

		// -----------------------------------------------------------------------------------

		@GetMapping("/getAll")
		public ResponseEntity<?> getAllDoctors() {

			try {
				return new ResponseEntity<>(doctorServiceRef.getAllUser(), HttpStatus.OK);
			} catch (UserNotFoundException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			} catch (Exception e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}

		// -----------------------------------------------------------------------------------

		@GetMapping("/email/{email}")
		public ResponseEntity<?> getDoctorByEmail(@PathVariable String email) {

			try {

				return new ResponseEntity<>(doctorServiceRef.getUserByEmail(email), HttpStatus.OK);

			} catch (UserNotFoundException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

			} catch (Exception e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}

		// -----------------------------------------------------------------------------------

		@GetMapping("/id/{id}")
		public ResponseEntity<?> getDoctorById(@PathVariable Long id) {

			try {

				return new ResponseEntity<>(doctorServiceRef.getUserById(id), HttpStatus.OK);

			} catch (UserNotFoundException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			} catch (Exception e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}

		// -----------------------------------------------------------------------------------

		@PutMapping("/updatedoctor")
		public ResponseEntity<?> updateDoctor(@RequestBody Doctor updatedoctor) {
			try {

				return new ResponseEntity<>(doctorServiceRef.updateUser(updatedoctor), HttpStatus.OK);

			} catch (CustomException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}

		// -----------------------------------------------------------------------------------

		@DeleteMapping("/deleteDoctor/{id}")
		public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {

			try {

				return new ResponseEntity<>(doctorServiceRef.deleteUser(id), HttpStatus.OK);

			} catch (CustomException e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {

				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}

		// -----------------------------------------------------------------------------------

		@PutMapping("/updatepassword/{id}/{password}")
		public ResponseEntity<?> updatePasword(@PathVariable Long id, @PathVariable String password) {

			System.out.println(id + "," + password);

			try {
				return new ResponseEntity<>(doctorServiceRef.upadatePassword(id, password), HttpStatus.OK);
				
			} catch (CustomException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				
			} catch (UserNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
		}

		// -----------------------------------------------------------------------------------

		@PutMapping("/updatepassword")
		public ResponseEntity<?> updatePasword1(@RequestParam Long doctorId, @RequestParam String password) {

			System.out.println(doctorId + "," + password);

			try {
				return new ResponseEntity<>(doctorServiceRef.upadatePassword(doctorId, password), HttpStatus.OK);
				
			} catch (CustomException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				
			} catch (UserNotFoundException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
				
			}
		}

		// ---------------------------------------------------------------------------------------------
		
		@PostMapping("/login")
		public ResponseEntity<Doctor> login(@RequestBody Doctor logReq) {
		    // Find the user by email
		    Doctor doctor = doctorServiceRef.findByEmail(logReq.getEmail());

		    if (doctor != null) {
		        // Check if the provided password matches the stored password
		        if (doctor.getPassword().equals(logReq.getPassword())) {
		            return new ResponseEntity<>(doctor, HttpStatus.OK);
		        } else {
		            // If the password doesn't match, return a 401 Unauthorized status
//		            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
		        	return null;
		        }
		    } else {
		        // If the email doesn't match any user, return a 401 Unauthorized status
//		        return new ResponseEntity<>("Email not found", HttpStatus.UNAUTHORIZED);
		    	return null;
		    }
		}
		
		//------------------------------------------------------------------------------------
		
		 @PatchMapping("update/email/{email}")
		    public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody Doctor updatedDoctor) {
		        try {
		            Doctor updated = doctorServiceRef.updateDoctorByEmail(email, updatedDoctor);
		            return new ResponseEntity<>(updated, HttpStatus.OK);
		        } catch (UserNotFoundException e) {
		            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		        } catch (CustomException e) {
		            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		        } catch (Exception e) {
		            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		        }
		    }

}
