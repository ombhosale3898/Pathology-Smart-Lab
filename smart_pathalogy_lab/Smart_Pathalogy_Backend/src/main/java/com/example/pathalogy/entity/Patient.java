package com.example.pathalogy.entity;

//import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate dob;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(Long patientId, String name, LocalDate dob, String gender, String address, String phone,
			String email, String password) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", name=" + name + ", dob=" + dob + ", gender=" + gender
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + ", password=" + password + "]";
	}

}
