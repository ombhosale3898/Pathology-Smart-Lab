package com.example.pathalogy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.LabTech;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.repository.LabTechRepo;

@Service
public class LabTechService {

	@Autowired
	
	private LabTechRepo labTechRepoRef;
	
	// --------------------------------------------------------------------------------------------

		public LabTech registerUser(LabTech user) {
			LabTech newUser = labTechRepoRef.findByEmail(user.getEmail());
			if (newUser == null) {
				return labTechRepoRef.save(user);
			} else {
				throw new EmailNotFoundException("Account With This Email Id is all ready Exits");
			}
		}

		// --------------------------------------------------------------------------------------------

		public List<LabTech> getAllLabTech() {

			List<LabTech> userlist = labTechRepoRef.findAll();

			if (userlist.size() != 0) {

				return userlist;
			} else {

				throw new UserNotFoundException("LabTech Not Found");
			}
		}

		// ------------------------------------------------------------------------------------------

		public LabTech getLabTechById(Long id) {

			return labTechRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException("LabTech Not Found for this ID"));

		}

		// ---------------------------------------------------------------------------------------------

		public LabTech getLabTechByEmail(String email) {

			LabTech user = labTechRepoRef.findByEmail(email);

			if (user != null) {

				return user;
			} else {

				throw new UserNotFoundException("LabTech Not Found");
			}

		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> deleteLabTech(Long id) {

			LabTech user = labTechRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException("LabTech not found with given id"));

			if (user != null) {

				labTechRepoRef.delete(user);
				return new ResponseEntity<>("LabTech Deleted successfully", HttpStatus.OK);
			}

			else {
				throw new CustomException("Unable to delete LabTech");
			}
		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> updateUser(LabTech updateuser) {

			LabTech user = labTechRepoRef.findById(updateuser.getLabtechId())
					.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update"));

			if (user != null) {

				user.setAddress(updateuser.getAddress());
				user.setEmail(updateuser.getEmail());
				user.setMobileNo(updateuser.getMobileNo());
				user.setFullName(updateuser.getFullName());
		

				labTechRepoRef.save(user);
				return new ResponseEntity<>("User Information Updated successfully", HttpStatus.OK);

			} else {

				throw new CustomException("unable to update the user information");
			}
		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> upadatePassword(Long userId, String password) {

			LabTech user = labTechRepoRef.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update password"));

			if (user != null) {

				user.setPassword(password);
				labTechRepoRef.save(user);

				return new ResponseEntity<>("Password change successfully", HttpStatus.OK);
				
			} else {

				throw new CustomException("Unable to change the password");
			}

		}
		
		//-----------------------------------------------------------------------------------------
		
		
		 public LabTech findByEmail(String email) {
		        return labTechRepoRef.findByEmail(email);
		      
		    }
		 //--------------------------------------------------------------------------------------
		
		 public LabTech updateLabTechByEmail(String email, LabTech updatedLabtech)  {
		        LabTech user = labTechRepoRef.findByEmail(email);
		            
		        if(user != null) {
		        // Update patient fields
		        user.setFullName(updatedLabtech.getFullName());
		        user.setAddress(updatedLabtech.getAddress());
		        user.setMobileNo(updatedLabtech.getMobileNo());
		        user.setPassword(updatedLabtech.getPassword());

		        return labTechRepoRef.save(user);
		        }else {
		        	 
		        	throw new CustomException("LabTech Not Found");
		        }
		    }
		
}
