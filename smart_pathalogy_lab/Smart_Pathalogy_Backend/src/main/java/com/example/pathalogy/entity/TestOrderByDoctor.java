package com.example.pathalogy.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class TestOrderByDoctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testByDoctorId;
	
	@ManyToOne
	@JoinColumn(name = "dname", referencedColumnName = "doctorId")
	private Doctor dname;

	@ManyToOne
	@JoinColumn(name = "lname", referencedColumnName = "labtechId")
	private LabTech lname;

	@ManyToOne
	@JoinColumn(name = "pname", referencedColumnName = "patientId")
	private Patient pname;
	
	@Column(nullable = false)
	private String testType;
	
	@Column(nullable = false)
	private LocalDate orderDate;
	
	public TestOrderByDoctor() {
		// TODO Auto-generated constructor stub
	}

	public TestOrderByDoctor(Long testByDoctorId, Doctor dname, LabTech lname, Patient pname, String testType,
			LocalDate orderDate) {
		super();
		this.testByDoctorId = testByDoctorId;
		this.dname = dname;
		this.lname = lname;
		this.pname = pname;
		this.testType = testType;
		this.orderDate = orderDate;
	}

	public Long getTestByDoctorId() {
		return testByDoctorId;
	}

	public void setTestByDoctorId(Long testByDoctorId) {
		this.testByDoctorId = testByDoctorId;
	}

	public Doctor getDname() {
		return dname;
	}

	public void setDname(Doctor dname) {
		this.dname = dname;
	}

	public LabTech getLname() {
		return lname;
	}

	public void setLname(LabTech lname) {
		this.lname = lname;
	}

	public Patient getPname() {
		return pname;
	}

	public void setPname(Patient pname) {
		this.pname = pname;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	
	
	
	
}
