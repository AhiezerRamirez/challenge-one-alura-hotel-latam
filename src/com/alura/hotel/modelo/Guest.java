package com.alura.hotel.modelo;

import java.sql.Date;

public class Guest {
	private Long id;
	private String firstName;
	private String lastName;
	private Date detaOfBirth;
	private String nationality;
	private Long phoneNumber;
	private Long reservationId;
	
	public Guest(String firstName, String lastName, java.util.Date detaOfBirth, String nationality, Long phoneNumber,
			Long reservationId) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.detaOfBirth = new Date(detaOfBirth.getDate());
		this.nationality = nationality;
		this.phoneNumber = phoneNumber;
		this.reservationId = reservationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDetaOfBirth() {
		return detaOfBirth;
	}

	public void setDetaOfBirth(Date detaOfBirth) {
		this.detaOfBirth = detaOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	
	
	
}
