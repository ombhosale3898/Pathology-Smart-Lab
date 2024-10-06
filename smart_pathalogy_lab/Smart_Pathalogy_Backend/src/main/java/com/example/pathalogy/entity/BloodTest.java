package com.example.pathalogy.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class BloodTest {
	//--------------------------------------------------------------------------------------
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodTestId;

	@OneToOne
	@JoinColumn(name = "patientId")
	private Patient patientIdRef;
	
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctorIdRef;
	//-----------------------------------------------------------------------------------
	
	@Column(nullable = false)
	private Integer whiteBloodCells;
	@Column(nullable = false)
	private Integer neutrophils;
	@Column(nullable = false)
	private Integer lymphocytes;
	@Column(nullable = false)
	private Integer monocytes;
	@Column(nullable = false)
	private Integer eosinophils;
	@Column(nullable = false)
	private Integer basophils;
	@Column(nullable = false)
	private Integer redBloodCells;
	@Column(nullable = false)
	private Double hemoglobin;
	@Column(nullable = false)
	private Double hematocrit;
	@Column(nullable = false)
	private Double meanCorpuscularVolume;
	@Column(nullable = false)
	private Double meanCorpuscularHemoglobin;
	@Column(nullable = false)
	private Double meanCorpuscularHemoglobinConcentration;
	@Column(nullable = false)
	private Double redCellDistributionWidth;
	@Column(nullable = false)
	private Integer platelets;
	@Column(nullable = false)
	private Double meanPlateletVolume;
	@Column(nullable = false)
	private Double plateletDistributionWidth;
	@Column(nullable = false)
	private String remark;

	//--------------------------------------------------------------------------------------------
	
	
	
	
	//--------------------------------------------------------------------------------------

	public BloodTest() {

	}

	public BloodTest(Long bloodTestId, Patient patientIdRef, Doctor doctorIdRef, Integer whiteBloodCells,
			Integer neutrophils, Integer lymphocytes, Integer monocytes, Integer eosinophils, Integer basophils,
			Integer redBloodCells, Double hemoglobin, Double hematocrit, Double meanCorpuscularVolume,
			Double meanCorpuscularHemoglobin, Double meanCorpuscularHemoglobinConcentration,
			Double redCellDistributionWidth, Integer platelets, Double meanPlateletVolume,
			Double plateletDistributionWidth, String remark) {
		super();
		this.bloodTestId = bloodTestId;
		this.patientIdRef = patientIdRef;
		this.doctorIdRef = doctorIdRef;
		this.whiteBloodCells = whiteBloodCells;
		this.neutrophils = neutrophils;
		this.lymphocytes = lymphocytes;
		this.monocytes = monocytes;
		this.eosinophils = eosinophils;
		this.basophils = basophils;
		this.redBloodCells = redBloodCells;
		this.hemoglobin = hemoglobin;
		this.hematocrit = hematocrit;
		this.meanCorpuscularVolume = meanCorpuscularVolume;
		this.meanCorpuscularHemoglobin = meanCorpuscularHemoglobin;
		this.meanCorpuscularHemoglobinConcentration = meanCorpuscularHemoglobinConcentration;
		this.redCellDistributionWidth = redCellDistributionWidth;
		this.platelets = platelets;
		this.meanPlateletVolume = meanPlateletVolume;
		this.plateletDistributionWidth = plateletDistributionWidth;
		this.remark = remark;
	}

	public Long getBloodTestId() {
		return bloodTestId;
	}

	public void setBloodTestId(Long bloodTestId) {
		this.bloodTestId = bloodTestId;
	}

	public Patient getPatientIdRef() {
		return patientIdRef;
	}

	public void setPatientIdRef(Patient patientIdRef) {
		this.patientIdRef = patientIdRef;
	}

	public Doctor getDoctorIdRef() {
		return doctorIdRef;
	}

	public void setDoctorIdRef(Doctor doctorIdRef) {
		this.doctorIdRef = doctorIdRef;
	}

	public Integer getWhiteBloodCells() {
		return whiteBloodCells;
	}

	public void setWhiteBloodCells(Integer whiteBloodCells) {
		this.whiteBloodCells = whiteBloodCells;
	}

	public Integer getNeutrophils() {
		return neutrophils;
	}

	public void setNeutrophils(Integer neutrophils) {
		this.neutrophils = neutrophils;
	}

	public Integer getLymphocytes() {
		return lymphocytes;
	}

	public void setLymphocytes(Integer lymphocytes) {
		this.lymphocytes = lymphocytes;
	}

	public Integer getMonocytes() {
		return monocytes;
	}

	public void setMonocytes(Integer monocytes) {
		this.monocytes = monocytes;
	}

	public Integer getEosinophils() {
		return eosinophils;
	}

	public void setEosinophils(Integer eosinophils) {
		this.eosinophils = eosinophils;
	}

	public Integer getBasophils() {
		return basophils;
	}

	public void setBasophils(Integer basophils) {
		this.basophils = basophils;
	}

	public Integer getRedBloodCells() {
		return redBloodCells;
	}

	public void setRedBloodCells(Integer redBloodCells) {
		this.redBloodCells = redBloodCells;
	}

	public Double getHemoglobin() {
		return hemoglobin;
	}

	public void setHemoglobin(Double hemoglobin) {
		this.hemoglobin = hemoglobin;
	}

	public Double getHematocrit() {
		return hematocrit;
	}

	public void setHematocrit(Double hematocrit) {
		this.hematocrit = hematocrit;
	}

	public Double getMeanCorpuscularVolume() {
		return meanCorpuscularVolume;
	}

	public void setMeanCorpuscularVolume(Double meanCorpuscularVolume) {
		this.meanCorpuscularVolume = meanCorpuscularVolume;
	}

	public Double getMeanCorpuscularHemoglobin() {
		return meanCorpuscularHemoglobin;
	}

	public void setMeanCorpuscularHemoglobin(Double meanCorpuscularHemoglobin) {
		this.meanCorpuscularHemoglobin = meanCorpuscularHemoglobin;
	}

	public Double getMeanCorpuscularHemoglobinConcentration() {
		return meanCorpuscularHemoglobinConcentration;
	}

	public void setMeanCorpuscularHemoglobinConcentration(Double meanCorpuscularHemoglobinConcentration) {
		this.meanCorpuscularHemoglobinConcentration = meanCorpuscularHemoglobinConcentration;
	}

	public Double getRedCellDistributionWidth() {
		return redCellDistributionWidth;
	}

	public void setRedCellDistributionWidth(Double redCellDistributionWidth) {
		this.redCellDistributionWidth = redCellDistributionWidth;
	}

	public Integer getPlatelets() {
		return platelets;
	}

	public void setPlatelets(Integer platelets) {
		this.platelets = platelets;
	}

	public Double getMeanPlateletVolume() {
		return meanPlateletVolume;
	}

	public void setMeanPlateletVolume(Double meanPlateletVolume) {
		this.meanPlateletVolume = meanPlateletVolume;
	}

	public Double getPlateletDistributionWidth() {
		return plateletDistributionWidth;
	}

	public void setPlateletDistributionWidth(Double plateletDistributionWidth) {
		this.plateletDistributionWidth = plateletDistributionWidth;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
