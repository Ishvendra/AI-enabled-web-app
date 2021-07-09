package com.higradius;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.util.Date;
import com.higradius.model;
import com.higradius.model2;

public class DAO{
	private String jdbcURL = "jdbc:mysql://localhost:3306/db?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_ROWS_SQL = "INSERT INTO hr_table" + "  (name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_clear_date, Notes) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_ROW_BY_ID = "select name_customer, cust_number, invoice_id, total_open_amount, due_in_date, predicted_clear_date, Notes from hr_table where invoice_id =?";
	private static final String SELECT_ALL_ROWS = "select * from hr_table limit ?,10;";
	private static final String DELETE_ROWS_SQL = "delete from hr_table where invoice_id = ?;";
	private static final String UPDATE_ROWS_SQL = "update hr_table set total_open_amount= ?, Notes= ? where invoice_id = ?;";
	
	public DAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertRow(model user) throws SQLException {
		System.out.println(INSERT_ROWS_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROWS_SQL)) {
			preparedStatement.setString(1, user.getName_customer());
			preparedStatement.setString(2, user.getCust_number());
			preparedStatement.setLong(3, user.getInvoice_id());
			preparedStatement.setLong(4, user.getTotal_open_amount());
			preparedStatement.setString(5, user.getDue_in_date());
			preparedStatement.setDate(6, (java.sql.Date) user.getPredicted_clear_date());
			preparedStatement.setString(7, user.getNotes());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public model selectRow(long invoice_id) {
		model user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROW_BY_ID);) {
			preparedStatement.setLong(1, invoice_id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name_customer = rs.getString("name_customer");
				String cust_number = rs.getString("cust_number");
				int total_open_amount = rs.getInt("total_open_amount");
				String due_in_date = rs.getString("due_in_date");
				Date predicted_clear_date = rs.getDate("predicted_clear_date");
				String Notes = rs.getString("Notes");
				user = new model(name_customer, cust_number,invoice_id, total_open_amount, due_in_date, predicted_clear_date, Notes);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<model> selectAllRows(int pgno) {

		List<model> hr_table = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROWS);) {
			
			int start = pgno*10;
			preparedStatement.setInt(1,start);
			
				
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name_customer = rs.getString("name_customer");
				String cust_number = rs.getString("cust_number");
				long invoice_id = rs.getLong("invoice_id");
				int total_open_amount = rs.getInt("total_open_amount");
				String due_in_date = rs.getString("due_in_date");
				Date predicted_clear_date = rs.getDate("predicted_clear_date");
				String Notes = rs.getString("Notes");
				hr_table.add(new model(name_customer, cust_number,invoice_id, total_open_amount, due_in_date, predicted_clear_date, Notes));
			}
			rs.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			printSQLException(e);
		}
		return hr_table;
	}

	public boolean deleteRow(long invoice_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ROWS_SQL);) {
			statement.setLong(1, invoice_id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateRow(model2 user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ROWS_SQL);) {

			statement.setLong(1, user.getTotal_open_amount());
			statement.setString(2, user.getNotes());
			statement.setLong(3, user.getInvoice_id());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
