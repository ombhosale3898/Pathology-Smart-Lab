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
import com.example.pathalogy.entity.LabTech;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.service.LabTechService;

@RestController
@RequestMapping("/labtech")
@CrossOrigin("*")
public class LabTechController {

	@Autowired

	private LabTechService labTechServiceRef;

	// -----------------------------------------------------------------------------------

	@PostMapping("/register")
	public ResponseEntity<?> Register(@RequestBody LabTech labtechregister) {
		try {

//				System.out.println(userregister);
			return new ResponseEntity<>(labTechServiceRef.registerUser(labtechregister), HttpStatus.OK);

		} catch (EmailNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllLabTech() {

		try {
			return new ResponseEntity<>(labTechServiceRef.getAllLabTech(), HttpStatus.OK);
		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/email/{email}")
	public ResponseEntity<?> getLabTechByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(labTechServiceRef.getLabTechByEmail(email), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getLabTechById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(labTechServiceRef.getLabTechById(id), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updatelabtech")
	public ResponseEntity<?> updateUser(@RequestBody LabTech updatedlabtech) {
		try {

			return new ResponseEntity<>(labTechServiceRef.updateUser(updatedlabtech), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// -----------------------------------------------------------------------------------

	@DeleteMapping("/deletelabtech/{id}")
	public ResponseEntity<?> deletelabtech(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(labTechServiceRef.deleteLabTech(id), HttpStatus.OK);

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
			return new ResponseEntity<>(labTechServiceRef.upadatePassword(id, password), HttpStatus.OK);

		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePasword1(@RequestParam Long labtechId, @RequestParam String password) {

		System.out.println(labtechId + "," + password);

		try {
			return new ResponseEntity<>(labTechServiceRef.upadatePassword(labtechId, password), HttpStatus.OK);

		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	}

	// ------------------------------------------------------------------------------------

	@PostMapping("/login")
	public ResponseEntity<LabTech> login(@RequestBody LabTech labTechReq) {

		LabTech labtechemail = labTechServiceRef.findByEmail(labTechReq.getEmail());

		if (labtechemail != null) {

			if (labtechemail.getPassword().equals(labTechReq.getPassword())) {
				return new ResponseEntity<>(labtechemail, HttpStatus.OK);
			} else {
				return null;
			}
		}

		return null;
	}

	// ---------------------------------------------------------------------

	@PatchMapping("update/email/{email}")
	public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody LabTech updatedLabTech) {
		try {
			LabTech updated = labTechServiceRef.updateLabTechByEmail(email, updatedLabTech);
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
