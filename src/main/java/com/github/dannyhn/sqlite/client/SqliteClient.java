package com.github.dannyhn.sqlite.client;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class SqliteClient implements InitializingBean, DisposableBean {
	
	private Connection connection;
	
	public String read(String id) {
		try {
			String sql = "SELECT jsontext FROM Simple " +
	                     "WHERE id=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			if (results.next()) {
				return results.getString(1);
			} else {
				return "error";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public void write(String id, String text) {
		String entry = read(id);
		if ("error".equals(entry)) {
			insert(id, text);
		} else {
			update(id, text);
		}
	}
	
	private void insert(String id, String text) {
		try {
			String sql = "INSERT INTO Simple " +
	                     "(id, jsontext) VALUES " +
	                     "(?, ?) ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, id);
			statement.setString(2, text);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void update(String id, String text) {
		try {
			String sql = "UPDATE Simple " +
	                     "SET jsontext=? WHERE " +
	                     "id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, text);
			statement.setString(2, id);
			statement.execute();

		} catch (SQLException e) {
			
		}
	}
	
	private void createSimpleTable() {
		try {
			Statement statement = connection.createStatement();
			String sql = "CREATE TABLE Simple " +
	                   "(id VARCHAR(255) not NULL, " +
	                   " jsontext longtext, " + 
	                   " PRIMARY KEY ( id ))"; 
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Set<String> getTables() throws SQLException {
		DatabaseMetaData metadata = connection.getMetaData();
		ResultSet results = metadata.getTables(null, null, "%", null);
		Set<String> tables = new HashSet<>();
		while (results.next()) {
			tables.add(results.getString(3));
		}
		return tables;
	}


	@Override
	public void destroy() throws Exception {
		if (connection != null) {
			connection.close();
		}
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Class.forName("org.sqlite.JDBC"); 
        String url = "jdbc:sqlite:D:/Danny/Documents/test2.db"; // use properties to get name of db
        try {
        	connection = DriverManager.getConnection(url) ;
			Set<String> tables = getTables();
			if (!tables.contains("Simple"))
			{
	        	createSimpleTable();
			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
}
