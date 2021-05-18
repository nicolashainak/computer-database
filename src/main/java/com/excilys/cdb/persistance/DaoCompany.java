package com.excilys.cdb.persistance;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.cdb.mapper.MapperCompany;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.ui.VueCompany;

public class DaoCompany {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String rqtSelectAll = "select * from company limit  ? offset ? ;";
	private String rqtCompanyById ="select id,name from company where id =? ";
	private String rqtNbCompany = "SELECT COUNT(*) FROM company ;";
	
	private DaoCompany() {}
	
	private static DaoCompany INSTANCE= new DaoCompany();
	
	public static DaoCompany getInstance() {
		return INSTANCE;
	}
	
	
	public ArrayList<Company> readDatabase(int i ) throws Exception {
		try {

			Connection connection = Database.getConnection();
			int limit = 20;
			int offset = 20 * i;
			PreparedStatement preparedStatement = connection.prepareStatement(rqtSelectAll);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			return MapperCompany.writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {

		}
	}
	
	public  Company getCompany(int i ) throws SQLException {
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(rqtCompanyById);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		Company company = null;
		try {
			company = MapperCompany.companyById(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Database.close();
		return company; 
	}
	public int nbCompany() throws Exception  {
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(rqtNbCompany);
		ResultSet resultSet  = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int id = Integer.parseInt (resultSet.getString(1));
			return id ;
		}
		return 0;
	}
	public int nbPageCompany () throws Exception {
		int nbCompany = nbCompany();
		int nbPageCompany = (nbCompany - (nbCompany%20)) /20;
		return nbPageCompany;
	}

}
