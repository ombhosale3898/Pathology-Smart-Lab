package com.example.pathalogy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ThyroidTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long thyroidTestId;

	@OneToOne
	@JoinColumn(name = "patientId", nullable = false)
	private Patient patientRef;

	@ManyToOne
	@JoinColumn(name = "doctorId", nullable = false)
	private Doctor doctorId;
	
	@Column(nullable = false)
	private Double tsh; // Thyroid Stimulating Hormone
	@Column(nullable = false)
	private Double freeT4; // Free Thyroxine
	@Column(nullable = false)
	private Double totalT4; // Total Thyroxine
	@Column(nullable = false)
	private Double freeT3; // Free Triiodothyronine
	@Column(nullable = false)
	private Double totalT3; // Total Triiodothyronine
	@Column(nullable = false)
	private Double thyroglobulin; // Thyroglobulin
	@Column(nullable = false)
	private String antiThyroglobulinAntibodies; // Anti-Thyroglobulin Antibodies
	@Column(nullable = false)
	private String antiThyroidPeroxidaseAntibodies; // Anti-Thyroid Peroxidase Antibodie


	public ThyroidTest() {

	}


	public ThyroidTest(Long thyroidTestId, Patient patientRef, Doctor doctorId, Double tsh, Double freeT4,
			Double totalT4, Double freeT3, Double totalT3, Double thyroglobulin, String antiThyroglobulinAntibodies,
			String antiThyroidPeroxidaseAntibodies) {
		super();
		this.thyroidTestId = thyroidTestId;
		this.patientRef = patientRef;
		this.doctorId = doctorId;
		this.tsh = tsh;
		this.freeT4 = freeT4;
		this.totalT4 = totalT4;
		this.freeT3 = freeT3;
		this.totalT3 = totalT3;
		this.thyroglobulin = thyroglobulin;
		this.antiThyroglobulinAntibodies = antiThyroglobulinAntibodies;
		this.antiThyroidPeroxidaseAntibodies = antiThyroidPeroxidaseAntibodies;
	}


	public Long getThyroidTestId() {
		return thyroidTestId;
	}


	public void setThyroidTestId(Long thyroidTestId) {
		this.thyroidTestId = thyroidTestId;
	}


	public Patient getPatientRef() {
		return patientRef;
	}


	public void setPatientRef(Patient patientRef) {
		this.patientRef = patientRef;
	}


	public Doctor getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}


	public Double getTsh() {
		return tsh;
	}


	public void setTsh(Double tsh) {
		this.tsh = tsh;
	}


	public Double getFreeT4() {
		return freeT4;
	}


	public void setFreeT4(Double freeT4) {
		this.freeT4 = freeT4;
	}


	public Double getTotalT4() {
		return totalT4;
	}


	public void setTotalT4(Double totalT4) {
		this.totalT4 = totalT4;
	}


	public Double getFreeT3() {
		return freeT3;
	}


	public void setFreeT3(Double freeT3) {
		this.freeT3 = freeT3;
	}


	public Double getTotalT3() {
		return totalT3;
	}


	public void setTotalT3(Double totalT3) {
		this.totalT3 = totalT3;
	}


	public Double getThyroglobulin() {
		return thyroglobulin;
	}


	public void setThyroglobulin(Double thyroglobulin) {
		this.thyroglobulin = thyroglobulin;
	}


	public String getAntiThyroglobulinAntibodies() {
		return antiThyroglobulinAntibodies;
	}


	public void setAntiThyroglobulinAntibodies(String antiThyroglobulinAntibodies) {
		this.antiThyroglobulinAntibodies = antiThyroglobulinAntibodies;
	}


	public String getAntiThyroidPeroxidaseAntibodies() {
		return antiThyroidPeroxidaseAntibodies;
	}


	public void setAntiThyroidPeroxidaseAntibodies(String antiThyroidPeroxidaseAntibodies) {
		this.antiThyroidPeroxidaseAntibodies = antiThyroidPeroxidaseAntibodies;
	}



}
