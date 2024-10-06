package com.example.pathalogy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;

	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String mobileNo;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String password;

	public Doctor() {
		// TODO Auto-generated constructor stub
	}

	public Doctor(Long doctorId, String fullName, String email, String mobileNo, String address, String password) {
		super();
		this.doctorId = doctorId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.password = password;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", fullName=" + fullName + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", address=" + address + ", password=" + password + "]";
	}

}
