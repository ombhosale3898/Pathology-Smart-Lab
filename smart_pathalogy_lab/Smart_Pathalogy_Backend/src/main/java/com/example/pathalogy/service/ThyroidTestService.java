package com.example.pathalogy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.ThyroidTest;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.ThyroidTestRepo;


@Service
public class ThyroidTestService {

	@Autowired
	
	private ThyroidTestRepo thyroidTestRepoRef;
	
	//---------------------------------------------------------------------------------------
	
	public ThyroidTest saveThyroidTest(ThyroidTest thyroidTestTest) {
		return thyroidTestRepoRef.save(thyroidTestTest);
	}
	//-------------------------------------------------------------------------------------
	
	public List<ThyroidTest> getAllThyroidTestParamerets() {

		List<ThyroidTest> thyroidTestParameterList = thyroidTestRepoRef.findAll();

		if (thyroidTestParameterList.size() != 0) {

			return thyroidTestParameterList;
		} else {

			throw new CustomException("The Thyroid Test parameters are Not Found");
		}
	}
	//--------------------------------------------------------------------------------------
	
	public ThyroidTest getThyroidTestById(Long id) {

		return thyroidTestRepoRef.findById(id).orElseThrow(() -> new CustomException("ThyroidTest Id Not Found"));

	}
	
	//---------------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteThyroidTest(Long id) {

		ThyroidTest thyroidTest = thyroidTestRepoRef.findById(id).orElseThrow(() -> new CustomException("Blood Test Report is not found with given id"));

		if (thyroidTest != null) {

			thyroidTestRepoRef.delete(thyroidTest);
			return new ResponseEntity<>("thyroidTest Report Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete thyroidTest Report");
		}
	}
	//---------------------------------------------------------------------------------------
	public ResponseEntity<?> updateThyroidTestParameter(ThyroidTest updatedResults) {

		ThyroidTest existingResults = thyroidTestRepoRef.findById(updatedResults.getThyroidTestId())
				.orElseThrow(() -> new CustomException("Given ThyroidTest Id is  Not Found for Update"));

		if (existingResults != null) {

			existingResults.setTsh(updatedResults.getTsh());
            existingResults.setFreeT4(updatedResults.getFreeT4());
            existingResults.setTotalT4(updatedResults.getTotalT4());
            existingResults.setFreeT3(updatedResults.getFreeT3());
            existingResults.setTotalT3(updatedResults.getTotalT3());
            existingResults.setThyroglobulin(updatedResults.getThyroglobulin());
            existingResults.setAntiThyroglobulinAntibodies(updatedResults.getAntiThyroglobulinAntibodies());
            existingResults.setAntiThyroidPeroxidaseAntibodies(updatedResults.getAntiThyroidPeroxidaseAntibodies());

			thyroidTestRepoRef.save(existingResults);
			return new ResponseEntity<>("ThyroidTest Parameters Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the ThyroidTest Parameters");
		}
	}
	
	//------------------------------------------------------------------------------------------
	
	
}
