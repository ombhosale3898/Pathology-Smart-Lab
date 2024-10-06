package com.example.pathalogy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(nullable = false)
	private String fullname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String mobileNo;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String password;
	

	public User() {

	}


	public User(Long userId, String fullname, String email, String mobileNo, String address, String password) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.email = email;
		this.mobileNo = mobileNo;
		this.address = address;
		this.password = password;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
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
