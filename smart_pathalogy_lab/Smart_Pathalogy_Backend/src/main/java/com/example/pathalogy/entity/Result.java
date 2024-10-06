package com.example.pathalogy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultId;

	@ManyToOne
	@JoinColumn(name = "patientId",nullable = false)
	private Patient patientidRef;

	@ManyToOne
	@JoinColumn(name = "doctorId",nullable = false)
	private Doctor doctorId;

	@ManyToOne
	@JoinColumn(name = "labtechId",nullable = false)
	private LabTech labtechId;

	@OneToOne
	@JoinColumn(name = "orderId",nullable = false)
	private TestOrder testOrderId;

	@ManyToOne
	@JoinColumn(name = "bloodTestId",nullable = false)
	private BloodTest bloodTestId;

	@ManyToOne
	@JoinColumn(name = "thyroidTestId",nullable = false)
	private ThyroidTest thyroidTestId;
	
	@Column(nullable = false)
	private String remark;

	public Result() {
		
	}

	public Result(Long resultId, Patient patientidRef, Doctor doctorId, LabTech labtechId, TestOrder testOrderId,
			BloodTest bloodTestId, ThyroidTest thyroidTestId, String remark) {
		super();
		this.resultId = resultId;
		this.patientidRef = patientidRef;
		this.doctorId = doctorId;
		this.labtechId = labtechId;
		this.testOrderId = testOrderId;
		this.bloodTestId = bloodTestId;
		this.thyroidTestId = thyroidTestId;
		this.remark = remark;
	}

	public Long getResultId() {
		return resultId;
	}

	public void setResultId(Long resultId) {
		this.resultId = resultId;
	}

	public Patient getPatientidRef() {
		return patientidRef;
	}

	public void setPatientidRef(Patient patientidRef) {
		this.patientidRef = patientidRef;
	}

	public Doctor getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}

	public LabTech getLabtechId() {
		return labtechId;
	}

	public void setLabtechId(LabTech labtechId) {
		this.labtechId = labtechId;
	}

	public TestOrder getTestOrderId() {
		return testOrderId;
	}

	public void setTestOrderId(TestOrder testOrderId) {
		this.testOrderId = testOrderId;
	}

	public BloodTest getBloodTestId() {
		return bloodTestId;
	}

	public void setBloodTestId(BloodTest bloodTestId) {
		this.bloodTestId = bloodTestId;
	}

	public ThyroidTest getThyroidTestId() {
		return thyroidTestId;
	}

	public void setThyroidTestId(ThyroidTest thyroidTestId) {
		this.thyroidTestId = thyroidTestId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	
}
