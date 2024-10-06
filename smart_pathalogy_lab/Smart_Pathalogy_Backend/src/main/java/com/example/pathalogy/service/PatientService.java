package com.example.pathalogy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.EmailNotFoundException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.repository.PatientRepo;

@Service
public class PatientService {

	@Autowired
	private PatientRepo patientRepoRef;
	
	// --------------------------------------------------------------------------------------------

		public Patient registerPatient(Patient patient) {
			Patient newPatient = patientRepoRef.findByEmail(patient.getEmail());
			if (newPatient == null) {
				return patientRepoRef.save(patient);
			} else {
				throw new EmailNotFoundException("Account With This Email Id is all ready Exits");
			}
		}

		// --------------------------------------------------------------------------------------------
	
	
		public List<Patient> getAllPatient() {

			List<Patient> patientlist = patientRepoRef.findAll();

			if (patientlist.size() != 0) {

				return patientlist;
			} else {

				throw new UserNotFoundException("Patients Not Found");
			}
		}
		
		
		// --------------------------------------------------------------------------------------------
		
		public Patient getPatientById(Long id) {

			return patientRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException(" Patient Id Not Found"));

		}
		
		// --------------------------------------------------------------------------------------------
		
		
		
		public Patient getPatientByEmail(String email) {

			Patient patientemail = patientRepoRef.findByEmail(email);

			if (patientemail != null) {

				return patientemail;
			} else {

				throw new UserNotFoundException("Patient Not Found for Given email address");
			}

		}
		
		
		
		
		  public Patient updatePatientByEmail(String email, Patient updatedPatient)  {
		        Patient patient = patientRepoRef.findByEmail(email);
		            
		        if(patient != null) {
		        // Update patient fields
		        patient.setName(updatedPatient.getName());
		        patient.setDob(updatedPatient.getDob());
		        patient.setGender(updatedPatient.getGender());
		        patient.setAddress(updatedPatient.getAddress());
		        patient.setPhone(updatedPatient.getPhone());
		        patient.setPassword(updatedPatient.getPassword());

		        return patientRepoRef.save(patient);
		        }else {
		        	 
		        	throw new CustomException("Patient Not Found");
		        }
		    }
		
		//--------------------------------------------------------------------------------------------


		public ResponseEntity<?> deletePatient(Long id) {

			Patient patient = patientRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException("Patient not found with given id"));

			if (patient != null) {

				patientRepoRef.delete(patient);
				return new ResponseEntity<>("Patient Deleted successfully", HttpStatus.OK);
			}

			else {
				throw new CustomException("Unable to delete Patient");
			}
		}


		//--------------------------------------------------------------------------------------------
		
		public ResponseEntity<?> updatePatient(Patient updatePatient) {

			Patient patient = patientRepoRef.findById(updatePatient.getPatientId())
					.orElseThrow(() -> new UserNotFoundException("Given Patient Id is  Not Found for Update"));

			if (patient != null) {

				patient.setAddress(updatePatient.getAddress());
				patient.setDob(updatePatient.getDob());
				patient.setEmail(updatePatient.getEmail());
				patient.setGender(updatePatient.getGender());
				patient.setName(updatePatient.getName());
				patient.setPhone(updatePatient.getPhone());
				patient.setPassword(updatePatient.getPassword());

				patientRepoRef.save(patient);
				return new ResponseEntity<>("Patient Information Updated successfully", HttpStatus.OK);

			} else {

				throw new CustomException("unable to update the Patient information");
			}
		}


		//--------------------------------------------------------------------------------------------

		public ResponseEntity<?> upadatePassword(Long userId, String password) {

			Patient patient = patientRepoRef.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update password"));

			if (patient != null) {

				patient.setPassword(password);
				patientRepoRef.save(patient);

				return new ResponseEntity<>("Password change successfully", HttpStatus.OK);
				
			} else {

				throw new CustomException("Unable to change the password");
			}

		}
		
		//---------------------------------------------------------------------
		
		 public Patient findByEmail(String email) {
		        return patientRepoRef.findByEmail(email);
		      
		    }
		 
		 //----------------------------------------------------------------------------
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
}

