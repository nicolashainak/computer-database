package main.webapp;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;



public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public void computerDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/computer-database-db?"+ "user=admincdb&password=qwerty1234");
			
		}catch(Exception e){
			throw e ;
		//}finally {
			//close();
		}
	}
	
	
	
}
