package com.example.pathalogy.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Doctor;
import com.example.pathalogy.entity.FeedBack;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.FeedBackRepo;
import com.example.pathalogy.repository.PatientRepo;

@Service
public class FeedBackService {

	@Autowired
	
	private FeedBackRepo feedBackRepoRef;
	
	@Autowired
	
	private PatientRepo patientRepoRef;
	
	@Autowired
	
	private PatientService patientServiceRef;
	
	
	@Autowired
	
	private DoctorService doctorServiceRef;
	
	//------------------------------------------------------------------------------------------
	
    public FeedBack saveFeedback(FeedBack feedbackRef,Long patientId,Long doctorId) {
    	
    	Patient patient = patientServiceRef.getPatientById(patientId);
    	
    	Doctor doctor  =  doctorServiceRef.getUserById(doctorId);
    	
    	feedbackRef.setDoctorId(doctor);
    	feedbackRef.setPatientId(patient);
    	feedbackRef.setFeedbackDate(LocalDate.now());
    	FeedBack feedback =  feedBackRepoRef.save(feedbackRef);
    	
    	if(feedback!=null) {
    		
    		return feedback;
    	}
    	else {
    		
    		throw new CustomException("unable to save feedBack");
    	}
    	
    }
	//-------------------------------------------------------------------------------------------
    
	public List<FeedBack> getAllFeedBack() {

		List<FeedBack> feedbackList = feedBackRepoRef.findAll();

		if (feedbackList.isEmpty()) {

			throw new CustomException("Feedbacks are not found");
		} else {

			return feedbackList;
		}
	}
	
	//----------------------------------------------------------------------------------------
	
	public FeedBack getFeedbackById(Long id) {

		return feedBackRepoRef.findById(id).orElseThrow(() -> new CustomException("Feedback Id Not Found"));

	}
	
	//--------------------------------------------------------------------------------
	
	public List<FeedBack> getFeedbackByPatientEmail(String email) {
		
		Patient patient = patientRepoRef.findByEmail(email);
		
		if (patient != null) {
			Long id = patient.getPatientId();
			return feedBackRepoRef.findByPatientIdPatientId(id);
		} else {
			throw new CustomException("Orders are not available for this Patient Email");
		}
	}
	
	//-----------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteFeedback(Long id) {

		FeedBack feedbackId = feedBackRepoRef.findById(id).orElseThrow(() -> new CustomException("Order is not found with given id"));

		if (feedbackId != null) {

			feedBackRepoRef.delete(feedbackId);
			return new ResponseEntity<>("Feedback  Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Feedback");
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	public ResponseEntity<?> updateFeedback(FeedBack updateFeedback) {

		FeedBack testOrderId = feedBackRepoRef.findById(updateFeedback.getId())
				.orElseThrow(() -> new CustomException("Given Order Id is  Not Found for Update"));

		if (testOrderId != null) {

			testOrderId.setPatientId(updateFeedback.getPatientId());
			testOrderId.setDoctorId(updateFeedback.getDoctorId());
			testOrderId.setRating(updateFeedback.getRating());
			testOrderId.setComments(updateFeedback.getComments());
			testOrderId.setFeedbackDate(LocalDate.now());

			feedBackRepoRef.save(testOrderId);
			return new ResponseEntity<>("FeedBack Information Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the FeedBack information");
		}
	}
	//------------------------------------------------------------------------------------
	
}
