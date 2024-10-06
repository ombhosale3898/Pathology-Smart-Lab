package com.example.pathalogy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class LabTech {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labtechId;

	@Column()
	private String fullName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String mobileNo;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String password;
	

	public LabTech() {
		// TODO Auto-generated constructor stub
	}


	public LabTech(Long labtechId, String fullName, String email, String mobileNo, String address, String password) {
		super();
		this.labtechId = labtechId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.password = password;
	}


	public Long getLabtechId() {
		return labtechId;
	}


	public void setLabtechId(Long labtechId) {
		this.labtechId = labtechId;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	

}
