package com.radianbroker.dto;

public class ObservingPractitioner {

	private String userName;
	private String lastName;
	private String firstName;

	public ObservingPractitioner(String userName, String lastName, String firstName) {
		super();
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
