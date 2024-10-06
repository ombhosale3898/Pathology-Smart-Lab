package com.example.pathalogy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Doctor;
import com.example.pathalogy.entity.User;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.repository.DoctorRepo;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepo doctorRepoRef;
	
	// --------------------------------------------------------------------------------------------

		public Doctor registerUser(Doctor user) {
			Doctor newUser = doctorRepoRef.findByEmail(user.getEmail());
			if (newUser == null) {
				return doctorRepoRef.save(user);
			} else {
				throw new EmailNotFoundException("Account With This Email Id is all ready Exits");
			}
		}

		// --------------------------------------------------------------------------------------------

		public List<Doctor> getAllUser() {

			List<Doctor> userlist = doctorRepoRef.findAll();

			if (userlist.size() != 0) {

				return userlist;
			} else {

				throw new UserNotFoundException("Doctor Not Found");
			}
		}

		// ------------------------------------------------------------------------------------------

		public Doctor getUserById(Long id) {

			return doctorRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException("Doctor Not Found for this ID"));

		}

		// ---------------------------------------------------------------------------------------------

		public Doctor getUserByEmail(String email) {

			Doctor user = doctorRepoRef.findByEmail(email);

			if (user != null) {

				return user;
			} else {

				throw new UserNotFoundException("Doctor Not Found");
			}

		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> deleteUser(Long id) {

			Doctor user = doctorRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException("Doctor not found with given id"));

			if (user != null) {

				doctorRepoRef.delete(user);
				return new ResponseEntity<>("User Deleted successfully", HttpStatus.OK);
			}

			else {
				throw new CustomException("Unable to delete Doctor");
			}
		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> updateUser(Doctor updateuser) {

			Doctor user = doctorRepoRef.findById(updateuser.getDoctorId())
					.orElseThrow(() -> new UserNotFoundException("Given Doctor Id is  Not Found for Update"));

			if (user != null) {

				user.setAddress(updateuser.getAddress());
				user.setEmail(updateuser.getEmail());
				user.setMobileNo(updateuser.getMobileNo());
				user.setFullName(updateuser.getFullName());
		

				doctorRepoRef.save(user);
				return new ResponseEntity<>("Doctor Information Updated successfully", HttpStatus.OK);

			} else {

				throw new CustomException("unable to update the Doctor information");
			}
		}

		// ------------------------------------------------------------------------------------------

		public ResponseEntity<?> upadatePassword(Long userId, String password) {

			Doctor user = doctorRepoRef.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("Given Doctor Id is  Not Found for Update password"));

			if (user != null) {

				user.setPassword(password);
				doctorRepoRef.save(user);

				return new ResponseEntity<>("Password change successfully", HttpStatus.OK);
				
			} else {

				throw new CustomException("Unable to change the password");
			}

		}
		
		
		//-----------------------------------------------------------------------------------------
		
		public Doctor findByEmail(String email) {
	        return doctorRepoRef.findByEmail(email);
	      
	    }
		
		//---------------------------------------------------------------------------------------
		
	    public Doctor updateDoctorByEmail(String email, Doctor updatedDoctor)  {
	        Doctor doctor = doctorRepoRef.findByEmail(email);
	            
	        if(doctor != null) {
	        // Update patient fields
	        	doctor.setFullName(updatedDoctor.getFullName());
	        	doctor.setAddress(updatedDoctor.getAddress());
	        	doctor.setMobileNo(updatedDoctor.getMobileNo());
	        	doctor.setPassword(updatedDoctor.getPassword());

	        return doctorRepoRef.save(doctor);
	        }else {
	        	 
	        	throw new CustomException("Doctor Not Found");
	        }
	    }
		
		

}
