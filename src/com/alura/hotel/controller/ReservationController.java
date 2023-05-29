package com.alura.hotel.controller;

import com.alura.hotel.dao.ReservationDao;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Reservation;

public class ReservationController {

	private ReservationDao reservationDao;
	
	public ReservationController() {
		var factory = new ConnectionFactory();
		this.reservationDao = new ReservationDao(factory.recuperaConexion());
	}
	
	public void saveReservations(Reservation reservatio) {
		reservationDao.guardar(reservatio);
	}
}
