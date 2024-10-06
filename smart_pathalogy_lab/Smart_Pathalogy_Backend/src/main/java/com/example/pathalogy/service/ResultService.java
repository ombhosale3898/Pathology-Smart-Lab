package com.example.pathalogy.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.Patient;
import com.example.pathalogy.entity.Result;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.PatientRepo;
import com.example.pathalogy.repository.ResultRepo;

@Service
public class ResultService {

	@Autowired
	private ResultRepo resultRepoRef;

	@Autowired
	private PatientRepo patientRepoRef;

	// ------------------------------------------------------------------------------------

	public List<Result> getAllResult() {

		List<Result> resultList = resultRepoRef.findAll();

		if (resultList.size() != 0) {

			return resultList;
		} else {

			throw new CustomException("The Report is Not Found");
		}
	}
	
	// ------------------------------------------------------------------------------------

	public Result getResultById(Long id) {

		return resultRepoRef.findById(id).orElseThrow(() -> new CustomException("Result  Not Found for given id"));

	}
	// -------------------------------------------------------------------------------------

	public List<Result> getResultByPatientEmail(String email) {

		Patient newResult = patientRepoRef.findByEmail(email);
		if (newResult != null) {
			Long resultId = newResult.getPatientId();
			return resultRepoRef.findByLabtechIdLabtechId(resultId);
		} else {
			throw new CustomException("Results  are not available for this Patient Email");
		}
	}

	// ------------------------------------------------------------------------------------------------

	public Result generateResult(Result createResult) {
		return resultRepoRef.save(createResult);
	}

	//----------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteResult(Long id) {

		Result result = resultRepoRef.findById(id).orElseThrow(() -> new CustomException("Results are not found with given id"));

		if (result != null) {

			resultRepoRef.delete(result);
			return new ResponseEntity<>("Result Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Result");
		}
	}
	
	//-----------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> updateResult(Result updateResult) {

		Result result = resultRepoRef.findById(updateResult.getResultId())
				.orElseThrow(() -> new CustomException("Given Order Id is  Not Found for Update"));

		if (result != null) {


			resultRepoRef.save(result);
			return new ResponseEntity<>("Order Information Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the Order information");
		}
	}
	
	//----------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
}
