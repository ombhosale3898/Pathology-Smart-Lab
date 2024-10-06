package com.example.pathalogy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pathalogy.entity.BloodTest;
import com.example.pathalogy.exceptions.CustomException;
import com.example.pathalogy.repository.BloodTestRepo;
@Service
public class BloodTestService {

	@Autowired
	private BloodTestRepo bloodTestRepoRef;

	// ----------------------------------------------------------------------------------------------

	public BloodTest saveBloodTest(BloodTest bloodTest) {
		return bloodTestRepoRef.save(bloodTest);
	}

	// --------------------------------------------------------------------------------------------

	public List<BloodTest> getAllBloodTestParamerets() {

		List<BloodTest> bloodTestParameterList = bloodTestRepoRef.findAll();

		if (bloodTestParameterList.size() != 0) {

			return bloodTestParameterList;
		} else {

			throw new CustomException("The Given Blood Test parameters are Not Found");
		}
	}
	//---------------------------------------------------------------------------------------------
	
	public BloodTest getBloodTestById(Long id) {

		return bloodTestRepoRef.findById(id).orElseThrow(() -> new CustomException("Blood test Id Not Found"));

	}
	//---------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> deleteBloodTest(Long id) {

		BloodTest bloodTest = bloodTestRepoRef.findById(id).orElseThrow(() -> new CustomException("Blood Test Report is not found with given id"));

		if (bloodTest != null) {

			bloodTestRepoRef.delete(bloodTest);
			return new ResponseEntity<>("Blood Test Report Deleted successfully", HttpStatus.OK);
		}

		else {
			throw new CustomException("Unable to delete Blood Test Report");
		}
	}
	
	//----------------------------------------------------------------------------------------------
	
	public ResponseEntity<?> updateBloodTestParameter(BloodTest updatedResults) {

		BloodTest existingResults = bloodTestRepoRef.findById(updatedResults.getBloodTestId())
				.orElseThrow(() -> new CustomException("Given Blood test Id is  Not Found for Update"));

		if (existingResults != null) {

			    existingResults.setWhiteBloodCells(updatedResults.getWhiteBloodCells());
	            existingResults.setNeutrophils(updatedResults.getNeutrophils());
	            existingResults.setLymphocytes(updatedResults.getLymphocytes());
	            existingResults.setMonocytes(updatedResults.getMonocytes());
	            existingResults.setEosinophils(updatedResults.getEosinophils());
	            existingResults.setBasophils(updatedResults.getBasophils());
	            existingResults.setRedBloodCells(updatedResults.getRedBloodCells());
	            existingResults.setHemoglobin(updatedResults.getHemoglobin());
	            existingResults.setHematocrit(updatedResults.getHematocrit());
	            existingResults.setMeanCorpuscularVolume(updatedResults.getMeanCorpuscularVolume());
	            existingResults.setMeanCorpuscularHemoglobin(updatedResults.getMeanCorpuscularHemoglobin());
	            existingResults.setMeanCorpuscularHemoglobinConcentration(updatedResults.getMeanCorpuscularHemoglobinConcentration());
	            existingResults.setRedCellDistributionWidth(updatedResults.getRedCellDistributionWidth());
	            existingResults.setPlatelets(updatedResults.getPlatelets());
	            existingResults.setMeanPlateletVolume(updatedResults.getMeanPlateletVolume());
	            existingResults.setPlateletDistributionWidth(updatedResults.getPlateletDistributionWidth());
	            existingResults.setRemark(updatedResults.getRemark());

			bloodTestRepoRef.save(existingResults);
			return new ResponseEntity<>("Blood Test Parameters Updated successfully", HttpStatus.OK);

		} else {

			throw new CustomException("unable to update the Blood Test Parameters");
		}
	}
	//---------------------------------------------------------------------------------------------
}
