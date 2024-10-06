package com.example.pathalogy.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Appoinments;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.exceptions.UserNotFoundException;
import com.example.pathalogy.repository.AppoinmentsRepo;
import com.example.pathalogy.repository.PatientRepo;
import com.example.pathalogy.repository.UserRepo;

@Service
public class AppoinmentsService {
	
	@Autowired
	private AppoinmentsRepo appoinmentsRepoRef;
	
	@Autowired
	private PatientService patientServiceRef;
	
	@Autowired
	private UserRepo doctorRepoRef;
	
	@Autowired
	private PatientRepo patientRepoRef;
	
	// --------------------------------------------------------------------------------------------
//	
//	public Appoinments createAppointment(Long patientId, Doctor doctorName, LocalDate appointmentDate) {
//        // Find the doctor by name
//		
//		List<Doctor> doctor = doctorRepoRef.findByUsername(doctorName.getFullName());
//		
//		Patient patient = patientServiceRef.getPatientById(patientId);
//        
//		if(doctor!=null) {
//        	Appoinments appointment = new Appoinments();
//        	
//            appointment.setPatientidRef(patient);
//            appointment.setDoctoridRef(null);
//            appointment.setAppoinmentDate(appointmentDate);
//            appointment.setStatus("Scheduled");
//            
//             return appoinmentsRepoRef.save(appointment);
//        	
//        }else{
//        	
//        	throw new CustomException("Appoinment not found with Id: " + patientId);
//        	
//        }
//	}

	// --------------------------------------------------------------------------------------------

	public Appoinments getAppoinmentById(Long id) {

		return appoinmentsRepoRef.findById(id).orElseThrow(() -> new UserNotFoundException(" Appoinment Id Not Found"));

	}
	
	// --------------------------------------------------------------------------------------------

	public List<Appoinments> getAllAppoinments() {

		List<Appoinments> appoinmentlist = appoinmentsRepoRef.findAll();

		if (appoinmentlist.size() != 0) {

			return appoinmentlist;
		} else {

			throw new UserNotFoundException("Appoinments are Not Found");
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> cancalAppoinment(Long AappointmentId) {

		Appoinments appoinment = appoinmentsRepoRef.findById(AappointmentId).orElseThrow(() -> new UserNotFoundException("Appoinment not found with given appoinment id"));

		if (appoinment != null) {

			appoinment.setStatus("Cancal");
			appoinmentsRepoRef.save(appoinment);
			return new ResponseEntity<>("Appoinment Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Appoinment");
		}
	}
	
	
	//-------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> completeAppoinment(Long AappointmentId) {

		Appoinments appoinment = appoinmentsRepoRef.findById(AappointmentId).orElseThrow(() -> new UserNotFoundException("Appoinment not found with given appoinment id"));

		if (appoinment != null) {

			appoinment.setStatus("Completed");
			appoinmentsRepoRef.save(appoinment);
			return new ResponseEntity<>("Appoinment Completed successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to Complete Appoinment");
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> updateAppoinment(Appoinments updateAppoinment) {

		Appoinments appoinment = appoinmentsRepoRef.findById(updateAppoinment.getAppoinmentId())
				.orElseThrow(() -> new UserNotFoundException("Given User Id is  Not Found for Update"));

		if (appoinment != null) {

			appoinment.setAppoinmentDate(updateAppoinment.getAppoinmentDate());
			appoinment.setAppoinmentTime(updateAppoinment.getAppoinmentTime());
			appoinmentsRepoRef.save(appoinment);
			return new ResponseEntity<>("Appoinment Update successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update Appoinment");
		}
	}
	
	// --------------------------------------------------------------------------------------------
	public ResponseEntity<?> deleteAppoinment(Long id) {

		Appoinments appoinments = appoinmentsRepoRef.findById(id).orElseThrow(() -> new CustomException("Appoinment is not found with given id"));

		if (appoinments != null) {

			appoinmentsRepoRef.delete(appoinments);
			return new ResponseEntity<>("Appoinment Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Appoinment");
		}
	}
	//--------------------------------------------------------------------------------------------
	
     public List<Appoinments> getAppoinmentByPatientEmail(String email) {
		
		Patient newUser = patientRepoRef.findByEmail(email);
		if (newUser != null) {
			return appoinmentsRepoRef.findByPatientidRefPatientId(newUser.getPatientId());
		} else {
			throw new CustomException("Appoinments are not available for this Patient Email");
		}
	}
	
	//--------------------------------------------------------------------------------------------
	
}
