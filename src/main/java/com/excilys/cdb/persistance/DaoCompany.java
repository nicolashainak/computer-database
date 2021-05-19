package com.excilys.cdb.persistance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.excilys.cdb.mapper.MapperCompany;
import com.excilys.cdb.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoCompany {
	
	private String rqtSelectAll = "select * from company limit  ? offset ? ;";
	private String rqtCompanyById ="select id,name from company where id =? ";
	private String rqtNbCompany = "SELECT COUNT(*) FROM company ;";
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);
	private DaoCompany() {}
	
	private static DaoCompany INSTANCE= new DaoCompany();
	
	public static DaoCompany getInstance() {
		return INSTANCE;
	}
	
	
	public ArrayList<Company> readDatabase(int i )  {
		ArrayList<Company> db= new ArrayList<Company>();
		try {

			Connection connection = Database.getConnection();
			int limit = 20;
			int offset = 20 * i;
			PreparedStatement preparedStatement = connection.prepareStatement(rqtSelectAll);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			db = MapperCompany.writeResultSet(resultSet);

		} catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
		return db;
	}
	
	public  Company getCompany(int i)  {
		Company company = null;
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtCompanyById);
			preparedStatement.setInt(1, i);
			ResultSet resultSet = preparedStatement.executeQuery();
			company = MapperCompany.companyById(resultSet);
		} catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
		//Database.close();
		return company; 
	}
	public int nbCompany()  {
		try {
			Connection connection=Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtNbCompany);
			ResultSet resultSet  = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = Integer.parseInt (resultSet.getString(1));
				return id ;
			}
		} catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
		return 0;
	}
	public int nbPageCompany () {
		int nbCompany = nbCompany();
		int nbPageCompany = (nbCompany - (nbCompany%20)) /20;
		return nbPageCompany;
	}

}
