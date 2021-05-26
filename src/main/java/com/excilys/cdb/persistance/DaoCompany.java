package com.excilys.cdb.persistance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.binding.mapper.MapperCompany;
import com.excilys.cdb.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoCompany {
	private final static String RQTSELECTALL = "select id,name from company  ;";
	private final static String RQTSELECTALLLIMIT = "select id,name from company limit  ? offset ? ;";
	private final static String RQTCOMPANYBYID = "select id,name from company where id =? ";
	private final static String RQTNBCOMPANY = "SELECT COUNT(*) FROM company ;";
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private DaoCompany() {
	} 

	private static DaoCompany instance = new DaoCompany();

	public static DaoCompany getInstance() {
		return instance;
	}

	public List<Company> getListCompany() {
		List<Company> listCompany = new ArrayList<Company>();

		try {
			Connection connection = CdbConnection.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(RQTSELECTALL);
			ResultSet resultSet = preparedStatement.executeQuery();
			listCompany = MapperCompany.writeResultSet(resultSet);

		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		return listCompany;
	}

	public ArrayList<Company> readDatabase(int i) {
		ArrayList<Company> db = new ArrayList<Company>();
		try {

			Connection connection = CdbConnection.getConnection();
			int limit = 20;
			int offset = 20 * i;
			PreparedStatement preparedStatement = connection.prepareStatement(RQTSELECTALLLIMIT);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			db = MapperCompany.writeResultSet(resultSet);

		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		return db;
	}

	public Company getCompany(int i) {
		Company company = null;
		try {
			Connection connection = CdbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(RQTCOMPANYBYID);
			preparedStatement.setInt(1, i);
			ResultSet resultSet = preparedStatement.executeQuery();
			company = MapperCompany.companyById(resultSet);
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		// Database.close();
		return company;
	}

	public int nbCompany() {
		try {
			Connection connection = CdbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(RQTNBCOMPANY);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				return id;
			}
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		return 0;
	}

	public int nbPageCompany() {
		int nbCompany = nbCompany();
		int nbPageCompany = (nbCompany - (nbCompany % 20)) / 20 +1;
		return nbPageCompany;
	}

}
