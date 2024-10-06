package com.example.pathalogy.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Appoinments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appoinmentId;

	@OneToOne
	@JoinColumn(name = "patientId", nullable = false)
	private Patient patientidRef;

	@ManyToOne
	@JoinColumn(name = "doctorId", nullable = false)
	private Doctor doctoridRef;

	@Column(nullable = false)
	private LocalDate appoinmentDate;
	@Column(nullable = false)
	private LocalTime appoinmentTime;
	@Column(nullable = false)
	private String status;

	public Appoinments() {
		// TODO Auto-generated constructor stub
	}

	public Appoinments(Long appoinmentId, Patient patientidRef, Doctor doctoridRef, LocalDate appoinmentDate,
			LocalTime appoinmentTime, String status) {
		super();
		this.appoinmentId = appoinmentId;
		this.patientidRef = patientidRef;
		this.doctoridRef = doctoridRef;
		this.appoinmentDate = appoinmentDate;
		this.appoinmentTime = appoinmentTime;
		this.status = status;
	}

	public Long getAppoinmentId() {
		return appoinmentId;
	}

	public void setAppoinmentId(Long appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public Patient getPatientidRef() {
		return patientidRef;
	}

	public void setPatientidRef(Patient patientidRef) {
		this.patientidRef = patientidRef;
	}

	public Doctor getDoctoridRef() {
		return doctoridRef;
	}

	public void setDoctoridRef(Doctor doctoridRef) {
		this.doctoridRef = doctoridRef;
	}

	public LocalDate getAppoinmentDate() {
		return appoinmentDate;
	}

	public void setAppoinmentDate(LocalDate appoinmentDate) {
		this.appoinmentDate = appoinmentDate;
	}

	public LocalTime getAppoinmentTime() {
		return appoinmentTime;
	}

	public void setAppoinmentTime(LocalTime appoinmentTime) {
		this.appoinmentTime = appoinmentTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
