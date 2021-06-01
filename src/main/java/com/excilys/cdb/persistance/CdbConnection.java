package com.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class CdbConnection {
	//private static Connection connect = null;
	 private static HikariConfig config = new HikariConfig();
	 private static HikariDataSource ds;

	    
	 static {
	        config.setJdbcUrl( "jdbc:mysql://localhost:3306/computer-database-db" );
	        config.setUsername( "admincdb" );
	        config.setPassword( "qwerty1234" );
	        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        ds = new HikariDataSource( config );
	    }
	    private CdbConnection() {
	    
	    }
	    
	    public static Connection getConnection() throws SQLException {
	        return ds.getConnection();
	    }
	    
	    
	   /* 
	    	private static void open() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/computer-database-db", "admincdb",
					"qwerty1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		try {
			if (connect == null || connect.isClosed()) {
				open();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connect;
	}*/

}
