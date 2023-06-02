package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
					+ "(name, lastname, dateOfBrith, Nationalitly, phoneNumber, reservationID, deleted) "
					+ "VALUES (?, ?, ?, ?, ?, ?, false);", Statement.RETURN_GENERATED_KEYS);
			
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
					}
				}
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Guest> getGuestList(){
		List<Guest> guestList = new ArrayList<>();
		try {
			final PreparedStatement statement = dataBaseConnection.prepareStatement("SELECT * FROM guests WHERE deleted = false");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						guestList.add(new Guest(
								resultSet.getLong("id"), 
								resultSet.getString("name"), 
								resultSet.getString("lastname"), 
								resultSet.getDate("dateOfBrith"), 
								resultSet.getString("Nationalitly"), 
								resultSet.getLong("phoneNumber"),
								resultSet.getLong("reservationID")
								));
					}
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return guestList;
	}

	public void delete(int id) {
		try {
			final PreparedStatement statement = dataBaseConnection.prepareStatement("UPDATE guests "
					+ "SET deleted = true WHERE id = ?");
			
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void edit(Long id, String name, String lastName, Date dateOfBirth, String nationality, Long phone, Long reservationId) {
		try {
			final PreparedStatement statement = this.dataBaseConnection.prepareStatement(
					"UPDATE guests SET "
					+ "name = ?, "
					+ "lastname = ?, "
					+ "dateOfBrith= ?, "
					+ "Nationalitly = ?, "
					+ "phoneNumber = ?, "
					+ "reservationID = ? "
					+ "WHERE id = ?");
			
			try(statement){
				statement.setString(1, name);
				statement.setString(2, lastName);
				statement.setDate(3, dateOfBirth);
				statement.setString(4, nationality);
				statement.setLong(5,phone);
				statement.setLong(6, reservationId);
				statement.setLong(7, id);
				
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
