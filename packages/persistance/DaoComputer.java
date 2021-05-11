package persistance;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import mapper.MapperComputer;
import model.Computer;
import ui.VueComputer;

public class DaoComputer {
		 
	
	public static ArrayList<Computer> readDatabase() throws Exception {
		try {
			
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from computer");
			ResultSet resultSet=preparedStatement.executeQuery();
			return MapperComputer.writeResultSet(resultSet);
			
			
		
		}catch(Exception e){
			throw e ;
		}finally {
			
		}
	}
	
	
	public static void newComputer ( Computer c ) throws Exception {
		try {
			Connection connection = Database.getConnection();			
			PreparedStatement preparedStatement =  connection.prepareStatement("INSERT INTO computer( name,introduced,discontinued,company_id)"
					+ "VALUES(?,?,?,?);");
			preparedStatement.setString(1,c.getName());
			Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(2,ts1);
			preparedStatement.setTimestamp(3,ts2);
			preparedStatement.setInt(4,c.getCompany_id());
			preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		System.out.println("INSERT INTO computer( name,introduced,discontinued,company_id)"
				+ "VALUES("+c.getName()+","+c.getIntroduced()+","+c.getDiscontinued()+","+c.getCompany_id()+");");
		}catch(Exception e){
			throw e ;
		}finally {
			
		}
	}
	
	public static ArrayList<Computer>  searchComputer(int idComputer) throws Exception {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from computer where id =" + idComputer);
			ResultSet resultSet=preparedStatement.executeQuery();
			System.out.println("search computer n° : "+idComputer);
			return MapperComputer.writeResultSet(resultSet);
			

		}catch(Exception e){
			throw e ;
		}finally {
			
		}
	}

	
	
}