package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	public List<Reservation> getReservationList(){
		List<Reservation> resultList = new ArrayList<>();
		
		try {
			final PreparedStatement statement = dataBaseConnection.prepareStatement("Select * FROM reservations WHERE deleted = false");
			
			try(statement){
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
					while(resultSet.next()) {
						resultList.add(new Reservation(
								resultSet.getInt("id"),
								resultSet.getDate("checkInDate"),
								resultSet.getDate("checkOutDate"),
								resultSet.getLong("total"),
								resultSet.getString("paymentMethod")
								));
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public void edit(String id, Date chechInDate, Date checkOutDate, Long total, String paymentMethod) {
		try {
			final PreparedStatement statement = dataBaseConnection.prepareStatement(
                    "UPDATE reservations SET "
                    + "checkInDate = ?, "
                    + "checkOutDate = ?, "
                    + "total = ?, "
                    + "paymentMethod = ?"
                    + " WHERE id = ?"
					);
			try (statement) {
                statement.setDate(1, chechInDate);
                statement.setDate(2, checkOutDate);
                statement.setLong(3, total);
                statement.setString(4, paymentMethod);
                statement.setInt(5, Integer.valueOf(id));
                statement.execute();

                int updateCount = statement.getUpdateCount();
                System.out.print("se editó de reservas" + updateCount);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		 try {
	            final PreparedStatement statement = dataBaseConnection.prepareStatement("UPDATE reservations SET deleted = true WHERE id = ?");

	            try (statement) {
	                statement.setInt(1, id);
	                statement.execute();

	                int updateCount = statement.getUpdateCount();
	                System.out.println("Se eliminó el id: " + updateCount);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
}
