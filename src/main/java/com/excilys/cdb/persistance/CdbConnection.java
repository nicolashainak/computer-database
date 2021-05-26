package com.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CdbConnection {
	private static Connection connect = null;

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

		if (connect == null) {
			open();

		}

		return connect;
	}

}
