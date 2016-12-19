package edu.mum.wap.model;

import java.time.LocalDate;

public class Users {
	private int userId;
	private String fullName;
	private String gender;
	private String state;
	private String city;
	private String street;
	private String zipCode;
	private LocalDate birthYear;
	private String email;
	private String password;
	private LocalDate dateCreated;
	private LocalDate dateUpdated;
	
	public Users(int userId, String fullName, String gender, String state, String city, String street,
			String zipCode, LocalDate birthYear, String email, String password, LocalDate dateCreated,
			LocalDate dateUpdated) {
		this.userId = userId;
		this.fullName = fullName;
		this.gender = gender;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipCode = zipCode;
		this.birthYear = birthYear;
		this.email = email;
		this.password = password;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public LocalDate getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(LocalDate birthYear) {
		this.birthYear = birthYear;
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

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDate getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDate dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", fullName=" + fullName + ", gender=" + gender + ", state=" + state + "]";
	}

	

	

}
