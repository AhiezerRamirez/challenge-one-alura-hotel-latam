package com.alura.hotel.controller;

import java.sql.Date;
import java.util.List;

import com.alura.hotel.dao.GuestDao;
import com.alura.hotel.dao.ReservationDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Guest;
import com.alura.hotel.modelo.Reservation;

public class ReservationController {

	private ReservationDao reservationDao;
	private GuestDao guestDao;
	
	public ReservationController() {
		var factory = new ConnectionFactory();
		this.reservationDao = new ReservationDao(factory.recuperaConexion());
		this.guestDao = new GuestDao(factory.recuperaConexion());
	}
	
	public void saveReservations(Reservation reservatio) {
		reservationDao.guardar(reservatio);
	}
	
	public void saveGuests(Guest guest) {
		this.guestDao.guardar(guest);
	}
	
	public List<Reservation> getReservationList(){
		return this.reservationDao.getReservationList();
	}
	
	public List<Guest> getGuestList(){
		return this.guestDao.getGuestList();
	}
	
	public void editReservation(String id, String checkInDate, String checkOutDate, String total, String paymentMethod) {
		Date date1 = Date.valueOf(checkInDate);
		Date date2 = Date.valueOf(checkOutDate);
		this.reservationDao.edit(id, date1, date2, Long.valueOf(total), paymentMethod);
	}
	
	public void editGuest(String id, String name, String lastName, String dateOfBrith, String nationality, String phoneNumber,
			String reservationID) {
		
		Long id1 = Long.valueOf(id);
		Date date = Date.valueOf(dateOfBrith);
		Long phone = Long.valueOf(phoneNumber);
		Long ri = Long.valueOf(reservationID);
		this.guestDao.edit(id1, name, lastName, date, nationality, phone, ri);
	}

	public void deleteReservation(String id) {
		this.reservationDao.delete(Integer.valueOf(id));
	}
	
	public void deleteGuest(String id) {
		this.guestDao.delete(Integer.valueOf(id));
	}
}
