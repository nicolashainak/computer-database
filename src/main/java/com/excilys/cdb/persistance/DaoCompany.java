package com.excilys.cdb.persistance;
import org.springframework.stereotype.Repository;

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

@Repository
public class DaoCompany {
	private final static String RQTSELECTALL = "select id,name from company  ;";
	private final static String RQTSELECTALLLIMIT = "select id,name from company limit  ? offset ? ;";
	private final static String RQTCOMPANYBYID = "select id,name from company where id =? ";
	private final static String RQTCOMPANYBYNAME = "select id,name from company where name = ? ";
	private final static String RQTNBCOMPANY = "SELECT COUNT(*) FROM company ;";
	private final static String RQTDELETECOMPUTER="DELET  FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE company.id = ?";
	private final static String RQTDELETECOMPANY="DELET  FROM company WHERE company.id = ?";
	
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private DaoCompany() {
	} 


	public List<Company> getListCompany() {
		List<Company> listCompany = new ArrayList<Company>();

		try (Connection connection = CdbConnection.getInstance().getConnection();){
		

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
		try (Connection connection = CdbConnection.getInstance().getConnection();) {

			
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

	public Company getCompanyById(int i) {
		Company company = null;
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(RQTCOMPANYBYID);
			preparedStatement.setInt(1, i);
			ResultSet resultSet = preparedStatement.executeQuery();
			company = MapperCompany.getThisCompany(resultSet);
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		// Database.close();
		return company;
	}

	public Company getCompanyByName(String name) {
		Company company = null;
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(RQTCOMPANYBYNAME);
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			company = MapperCompany.getThisCompany(resultSet);
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		// Database.close();
		return company;
	}
	
	public int nbCompany() {
		try (Connection connection = CdbConnection.getInstance().getConnection();) {
			
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
	
	
	
	public void delet(int company_id) {
		try (Connection connection = CdbConnection.getInstance().getConnection();) {

			try (PreparedStatement preparedStatement1 = connection.prepareStatement(RQTDELETECOMPUTER);
					PreparedStatement preparedStatement2 = connection.prepareStatement(RQTDELETECOMPANY);) {
				
				//instruction 1 à 1
				connection.setAutoCommit(false);
				
				preparedStatement1.setInt(1, company_id);
				preparedStatement1.execute();

				preparedStatement2.setInt(2,company_id);
				preparedStatement2.execute();
				
				connection.commit();

				
			} catch (SQLException del) {
				
				if (connection != null) {
					try {
						logger.error(" error  Delete: " + del);
						connection.rollback();
					} catch (SQLException e) {
						logger.error("MySQL error : " + e);
					}
				}
			} finally {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException ero) {
					logger.error("MySQL error : " + ero);
				}
			}

		} catch (SQLException error) {
			logger.error("MySQL error connection : " + error);
			
		}
	}

}
