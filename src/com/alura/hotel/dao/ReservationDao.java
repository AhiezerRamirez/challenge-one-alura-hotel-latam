package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.modelo.Reservation;

public class ReservationDao {
	
	private Connection dataBaseConnection;
	
	public ReservationDao(Connection con) {
		this.dataBaseConnection = con;
	}
	
	public void guardar(Reservation reservation) {
		
		PreparedStatement statement;
		try {
			statement = this.dataBaseConnection.prepareStatement("INSERT INTO reservations"
					+ "(checkInDate,checkOutdate, total, paymentMethod) "
					+ " VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			
			try(statement){
				statement.setDate(1, reservation.getCheckInDate());
				statement.setDate(2, reservation.getCheckOutDate());
				statement.setLong(3, reservation.getTotal());
				statement.setString(4, reservation.getPaymentMethod().toString());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(resultSet){
					while(resultSet.next()) {
						reservation.setId(resultSet.getInt(1));
						
						System.out.println("Se agregó el producto: "+ reservation.getTotal());
					}
					
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
