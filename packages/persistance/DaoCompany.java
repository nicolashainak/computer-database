package persistance;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mapper.MapperCompany;
import model.Company;
import ui.VueCompany;

public class DaoCompany {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	public static ArrayList<Company> readDatabase() throws Exception {
		try {
			
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from company");
			ResultSet resultSet=preparedStatement.executeQuery();
			return MapperCompany.writeResultSet(resultSet);
			
		}catch(Exception e){
			throw e ;
		}finally {
			
		}
	}
	
	
	
    }
	


