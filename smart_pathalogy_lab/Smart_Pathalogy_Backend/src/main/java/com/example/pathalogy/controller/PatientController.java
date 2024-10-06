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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.service.PatientService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/patient")
@CrossOrigin("*")
public class PatientController {

	@Autowired
	private PatientService patientServiceRef;
	
	//-------------------------------------------------------------------------------------------
	
	@PostMapping("/register")
	public ResponseEntity<?> Register(@RequestBody Patient patientregister) {
		try {

			System.out.println(patientregister);
			return new ResponseEntity<>(patientServiceRef.registerPatient(patientregister), HttpStatus.OK);

		} catch (EmailNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	//-------------------------------------------------------------------------------------------
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllPatient() {

		try {
			return new ResponseEntity<>(patientServiceRef.getAllPatient(), HttpStatus.OK);
		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//------------------------------------------------------------------------------------------
	
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getPatientByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(patientServiceRef.getPatientByEmail(email), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//------------------------------------------------------------------------------------------
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(patientServiceRef.getPatientById(id), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//------------------------------------------------------------------------------------------
	
	@PutMapping("/updatepatient")
	public ResponseEntity<?> updatePatient(@RequestBody Patient updatedPatient) {
	    try {
	        // Debugging: Print the incoming patient data
	        System.out.println("Updating Patient: " + updatedPatient);

	        
	        return new ResponseEntity<>(patientServiceRef.updatePatient(updatedPatient), HttpStatus.OK);
	    } catch (CustomException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}

	
	//------------------------------------------------------------------------------------------
	
	@DeleteMapping("/deletepatient/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(patientServiceRef.deletePatient(id), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//------------------------------------------------------------------------------------------
	
	@PutMapping("/updatepassword/{id}/{password}")
	public ResponseEntity<?> updatePasword(@PathVariable Long id, @PathVariable String password) {

		System.out.println(id + "," + password);

		try {
			return new ResponseEntity<>(patientServiceRef.upadatePassword(id, password), HttpStatus.OK);
			
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePasword1(@RequestParam Long patientId, @RequestParam String password) {

		System.out.println(patientId + "," + password);

		try {
			return new ResponseEntity<>(patientServiceRef.upadatePassword(patientId, password), HttpStatus.OK);
			
		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
	}
	
	//-----------------------------------------------------------------------------------------
	
    @PatchMapping("update/email/{email}")
    public ResponseEntity<?> updatePatientByEmail(@PathVariable String email, @RequestBody Patient updatedPatient) {
        try {
            Patient updated = patientServiceRef.updatePatientByEmail(email, updatedPatient);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	// ---------------------------------------------------------------------------------------------
	
	@PostMapping("/login")
	public ResponseEntity<Patient> login(@RequestBody Patient patientReq) {

	    // Retrieve the patient by their email
	    Patient patientmail = patientServiceRef.findByEmail(patientReq.getEmail());

	    // Check if the patient exists and the passwords match
	    if (patientmail != null) {
	        if (patientmail.getPassword().equals(patientReq.getPassword())) {
	             return new ResponseEntity<>(patientmail, HttpStatus.OK);
	        	
	        } else {
//	            return ResponseEntity.status(401).body("Invalid password");
	        	return null;
	        }
	    } else {
//	        return ResponseEntity.status(401).body("Invalid email");
	    	return null;
	    }
	}

	//------------------------------------------------------------------------------------
	
	@GetMapping("/current")
    public Patient getCurrentPatient(@RequestHeader("Authorization") String token) {
        // Extract the token from the "Bearer" string
        String jwtToken = token.replace("Bearer ", "");

        // Parse the token to extract the email (assuming the token contains the email)
        Claims claims = Jwts.parser()
                .setSigningKey("your_secret_key") // Replace with your actual secret key
                .parseClaimsJws(jwtToken)
                .getBody();

        String email = claims.getSubject(); // Assuming the email is stored as the subject

        return patientServiceRef.getPatientByEmail(email);
    }
	 
	
	

}
