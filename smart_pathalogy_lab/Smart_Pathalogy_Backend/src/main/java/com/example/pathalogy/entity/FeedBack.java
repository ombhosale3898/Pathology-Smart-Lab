package com.example.pathalogy.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class FeedBack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "patientId",nullable = false)
	private Patient patientId;

	@ManyToOne
	@JoinColumn(name = "doctorId",nullable = false)
	private Doctor doctorId;
	
	@Column(nullable = false)
	private Integer rating;

	@Column(nullable = false)
	private String comments;

	@Column(nullable = false)
	private LocalDate feedbackDate;

	public FeedBack() {

	}

	public FeedBack(Long id, Patient patientId, Doctor doctorId, Integer rating, String comments,
			LocalDate feedbackDate) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.rating = rating;
		this.comments = comments;
		this.feedbackDate = feedbackDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatientId() {
		return patientId;
	}

	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}

	public Doctor getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Doctor doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(LocalDate feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	

	
}
