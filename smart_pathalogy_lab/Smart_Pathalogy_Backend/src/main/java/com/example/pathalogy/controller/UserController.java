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

import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")

public class UserController {

	@Autowired
	private UserService userserviceRef;

	// -----------------------------------------------------------------------------------

	@PostMapping("/register")
	public ResponseEntity<?> Register(@RequestBody User userregister) {
		try {

			System.out.println(userregister);
			return new ResponseEntity<>(userserviceRef.registerUser(userregister), HttpStatus.OK);

		} catch (EmailNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllUser() {

		try {
			return new ResponseEntity<>(userserviceRef.getAllUser(), HttpStatus.OK);
		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/email/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

		try {

			return new ResponseEntity<>(userserviceRef.getUserByEmail(email), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// -----------------------------------------------------------------------------------

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(userserviceRef.getUserById(id), HttpStatus.OK);

		} catch (UserNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updateuser")
	public ResponseEntity<?> updateUser(@RequestBody User updateduser) {
		try {

			return new ResponseEntity<>(userserviceRef.updateUser(updateduser), HttpStatus.OK);

		} catch (CustomException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// -----------------------------------------------------------------------------------

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		try {

			return new ResponseEntity<>(userserviceRef.deleteUser(id), HttpStatus.OK);

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
			return new ResponseEntity<>(userserviceRef.upadatePassword(id, password), HttpStatus.OK);

		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	}

	// -----------------------------------------------------------------------------------

	@PutMapping("/updatepassword")
	public ResponseEntity<?> updatePasword1(@RequestParam Long userId, @RequestParam String password) {

		System.out.println(userId + "," + password);

		try {
			return new ResponseEntity<>(userserviceRef.upadatePassword(userId, password), HttpStatus.OK);

		} catch (CustomException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
	}

	// ---------------------------------------------------------------------------------------------
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User logReq) {
	    // Find the user by email
	    User user = userserviceRef.findByEmail(logReq.getEmail());

	    if (user != null) {
	        // Check if the provided password matches the stored password
	        if (user.getPassword().equals(logReq.getPassword())) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            // If the password doesn't match, return a 401 Unauthorized status
//	            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
	        	return null;
	        }
	    } else {
	        // If the email doesn't match any user, return a 401 Unauthorized status
//	        return new ResponseEntity<>("Email not found", HttpStatus.UNAUTHORIZED);
	    	return null;
	    }
	}

	// -------------------------------------------------------------------------------------------------

	  @PatchMapping("update/email/{email}")
	    public ResponseEntity<?> updateUserByEmail(@PathVariable String email, @RequestBody User updatedUser) {
	        try {
	            User updated = userserviceRef.updateUserByEmail(email, updatedUser);
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
