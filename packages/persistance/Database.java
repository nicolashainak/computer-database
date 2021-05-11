package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Connection connect = null;
	private static void open() throws SQLException {
			 connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/computer-database-db","admincdb","qwerty1234");
	}
	public static Connection getConnection() throws SQLException {
		if (connect==null) {
			open();
		}
		return connect;
	}
	public static void close() throws SQLException {
		  if (connect != null) {
              connect.close();
          }
	}
}
