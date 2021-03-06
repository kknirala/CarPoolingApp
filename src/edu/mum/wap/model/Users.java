package edu.mum.wap.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Users {
	private int userId;
	private String fullName;
	private String gender;
	private String state;
	private String city;
	private String street;
	private String zipCode;

	private Date birthYear;
	private String email;
	private String password;

	private Date dateCreated;

	private Date dateUpdated;
	
	
	public Users() {}

	public Users(int userId, String fullName, String gender, String state, String city, String street,
			String zipCode, Date birthYear, String email, String password, Date dateCreated,
			Date dateUpdated) {
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

	public Date getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Date birthYear) {
		this.birthYear = birthYear;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	//only for test

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", fullName=" + fullName + ", gender=" + gender + ", state=" + state
				+ ", city=" + city + ", street=" + street + ", zipCode=" + zipCode + ", birthYear=" + birthYear
				+ ", email=" + email + ", password=" + password + ", dateCreated=" + dateCreated + ", dateUpdated="
				+ dateUpdated + "]";
	}
	
	

	

}
