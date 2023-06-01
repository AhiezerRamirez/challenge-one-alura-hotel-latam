package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.modelo.Guest;

public class GuestDao {

private Connection dataBaseConnection;
	
	public GuestDao(Connection con) {
		this.dataBaseConnection = con;
	}
	
	public void guardar(Guest guest) {
		
		PreparedStatement statement;
		try{
			statement = this.dataBaseConnection.prepareStatement("INSERT INTO guests "
					+ "(name, lastname, dateOfBrith, Nationalitly, phoneNumber, reservationID) "
					+ "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				
				statement.setString(1, guest.getFirstName());
				statement.setString(2, guest.getLastName());
				statement.setDate(3, guest.getDetaOfBirth());
				statement.setString(4, guest.getNationality());
				statement.setLong(5, guest.getPhoneNumber());
				statement.setLong(6, guest.getReservationId());
				
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
				
				try(statement){
					while(resultSet.next()) {
						System.out.print("Sí se guardó el huésped " + guest.getFirstName());
					}
				}
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
