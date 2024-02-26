package com.userdetails;

public class Person {
	  private String personId;
	  private String firstName;
	  private String lastName;
	  private char gender;
	  private String email;
	  private long phoneNumber;
	  private String address;
	  private String dateOfBirth;
	  private String userName;
	  private String password;
	public Person(String personId, String firstName, String lastName, char gender, String email, long phoneNumber,
			String address, String dateOfBirth,String userName, String password) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.userName = userName;
		this.password = password;
	}

	public Person(int userID, String password2) {
		// TODO Auto-generated constructor stub
	}

	public String getPersonId() {
		return personId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dob) {
		this.dateOfBirth = dob;
	}
}
	
