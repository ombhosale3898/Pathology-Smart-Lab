package com.example.pathalogy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.repository.UserRepo;


@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	// --------------------------------------------------------------------------------------------

	public User registerUser(User user) {
		User newUser = userRepo.findByEmail(user.getEmail());
		
		if (newUser == null) {
			return userRepo.save(user);
		} else {
			throw new EmailNotFoundException("Account With This Email Id is all ready Exits");
		}
	}

	// --------------------------------------------------------------------------------------------

	public List<User> getAllUser() {

		List<User> userlist = userRepo.findAll();

		if (userlist.size() != 0) {

			return userlist;
		} else {

			throw new UserNotFoundException("User Not Found");
		}
	}

	// ------------------------------------------------------------------------------------------

	public User getUserById(Long id) {

		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found for this ID"));

	}

	// ---------------------------------------------------------------------------------------------

	public User getUserByEmail(String email) {

		User user = userRepo.findByEmail(email);

		if (user != null) {

			return user;
		} else {

			throw new UserNotFoundException("User Not Found");
		}

	}

	// ------------------------------------------------------------------------------------------

	public ResponseEntity<?> deleteUser(Long id) {

		User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with given id"));

		if (user != null) {

			userRepo.delete(user);
			return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete User");
		}
	}

	// ------------------------------------------------------------------------------------------

	public ResponseEntity<?> updateUser(User updateuser) {

		User user = userRepo.findById(updateuser.getUserId())
				.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update"));

		if (user != null) {

			user.setAddress(updateuser.getAddress());
			user.setEmail(updateuser.getEmail());
			user.setMobileNo(updateuser.getMobileNo());
			user.setFullname(updateuser.getFullname());
	

			userRepo.save(user);
			return new ResponseEntity<>("User Information Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the user information");
		}
	}

	// ------------------------------------------------------------------------------------------

	public ResponseEntity<?> upadatePassword(Long userId, String password) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update password"));

		if (user != null) {

			user.setPassword(password);
			userRepo.save(user);

			return new ResponseEntity<>("Password change successfully", HttpStatus.OK);
			
		} else {

			throw new CustomException("Unable to change the password");
		}

	}

	// ---------------------------------------------------------------------------------------------
	
	    public User findByEmail(String email) {
	        return userRepo.findByEmail(email);
	      
	    }
	
	 
	 //-------------------------------------------------------------------------------------------
	 
	    public User updateUserByEmail(String email, User updatedUser)  {
	        User user = userRepo.findByEmail(email);
	            
	        if(user != null) {
	        // Update patient fields
	        user.setFullname(updatedUser.getFullname());
	        user.setAddress(updatedUser.getAddress());
	        user.setMobileNo(updatedUser.getMobileNo());
	        user.setPassword(updatedUser.getPassword());

	        return userRepo.save(user);
	        }else {
	        	 
	        	throw new CustomException("Admin Not Found");
	        }
	    }
	 
	 //-------------------------------------------------------------------------------------
	
	// to disable spring generated password by spring
	// User can use his custom password
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
//		User user = userRepoRef.findByEmail(username);
//		if (user == null) {
//			throw new UserNotFoundException("User not found with email: " + username);
//		}
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
//	}
//
//	public User findUserProfileByJwt(String jwt) throws UserNotFoundException {
//		String email = jwtProviderRef.getEmailFromToken(jwt);
//		User user = userRepoRef.findByEmail(email);
//		if (user == null) {
//			throw new UserNotFoundException("User not found with this email: " + email);
//		}
//		return user;
//	}

}