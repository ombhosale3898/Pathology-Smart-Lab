package com.example.pathalogy.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class TestOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patientId;


	@ManyToOne
	@JoinColumn(name = "labtechId")
	private LabTech labtechId;

	@Column()
	private LocalDate orderDate;
	@Column()
	private String testType;
	@Column()
	private String status;
	
	public TestOrder() {
		// TODO Auto-generated constructor stub
	}
	
	public TestOrder(Long orderId, Patient patientId, LabTech labtechId, LocalDate orderDate, String testType,
			String status) {
		super();
		this.orderId = orderId;
		this.patientId = patientId;
		this.labtechId = labtechId;
		this.orderDate = orderDate;
		this.testType = testType;
		this.status = status;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Patient getPatientId() {
		return patientId;
	}
	public void setPatientId(Patient patientId) {
		this.patientId = patientId;
	}
	public LabTech getLabtechId() {
		return labtechId;
	}
	public void setLabtechId(LabTech labtechId) {
		this.labtechId = labtechId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
