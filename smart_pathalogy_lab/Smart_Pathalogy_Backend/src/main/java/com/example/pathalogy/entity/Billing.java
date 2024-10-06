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
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "patientId",nullable = false)
	private Patient patientIdRef;

	@ManyToOne
	@JoinColumn(name = "orderId",nullable = false)
	private TestOrder testOrderRef;

	@Column(nullable = false)
	private Double amount;

	@Column(nullable = false)
	private String status; // "paid", "unpaid"

	@Column
	private LocalDate billingDate;

	public Billing() {
		
	}

	public Billing(Long id, Patient patientIdRef, TestOrder testOrderRef, Double amount, String status,
			LocalDate billingDate) {
		super();
		this.id = id;
		this.patientIdRef = patientIdRef;
		this.testOrderRef = testOrderRef;
		this.amount = amount;
		this.status = status;
		this.billingDate = billingDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatientIdRef() {
		return patientIdRef;
	}

	public void setPatientIdRef(Patient patientIdRef) {
		this.patientIdRef = patientIdRef;
	}

	public TestOrder getTestOrderRef() {
		return testOrderRef;
	}

	public void setTestOrderRef(TestOrder testOrderRef) {
		this.testOrderRef = testOrderRef;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	
	
}
