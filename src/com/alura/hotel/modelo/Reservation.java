package com.alura.hotel.modelo;

import java.sql.Date;


public class Reservation {
	private int id;
	private Date checkInDate;
	private Date checkOutDate;
	private Long total;
	private String paymentMethod;
	
	
	public Reservation(java.util.Date checkInDate, java.util.Date checkOutDate, Long total, String paymentMethod) {
		this.checkInDate = new Date(checkInDate.getDate());
		this.checkOutDate = new Date(checkOutDate.getDate());
		this.total = total;
		this.paymentMethod = paymentMethod;
	}
	
	public Reservation(int id,Date checkInDate, Date checkOutDate, Long total, String paymentMethod) {
		this.id = id;
		this.checkInDate = new Date(checkInDate.getDate());
		this.checkOutDate = new Date(checkOutDate.getDate());
		this.total = total;
		this.paymentMethod = paymentMethod;
	}
	
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public void setId(int id) {
		this.id = id;
		
	}
	
	public int getId() {
		return this.id;
	}
	
}
