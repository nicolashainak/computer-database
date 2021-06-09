package com.excilys.cdb.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.excilys.cdb.binding.mapper.MapperCompany;
import com.excilys.cdb.model.Company;
import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class DaoCompany {

	private final static String RQTSELECTALL = "select id,name from company  ;";
	private final static String RQTSELECTALLLIMIT = "select id,name from company limit  ? offset ? ;";
	private final static String RQTCOMPANYBYID = "select id,name from company where id =? ";
	private final static String RQTCOMPANYBYNAME = "select id,name from company where name = ? ";
	private final static String RQTNBCOMPANY = "SELECT COUNT(*) FROM company ;";
	private final static String RQTDELETECOMPUTER = "DELET  FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE company.id = ?";
	private final static String RQTDELETECOMPANY = "DELET  FROM company WHERE company.id = ?";
	private JdbcTemplate vJdbcTemplate;

	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	public DaoCompany(DataSource datasource) {
		vJdbcTemplate = new JdbcTemplate(datasource);
	}

	// version jdbc
	public List<Company> getListCompany() {
		String vSQL = RQTSELECTALL;
		List<Company> listCompany = vJdbcTemplate.query(vSQL, new MapperCompany());

		return listCompany;
	}

	public Company getCompanyById(int i) {
		Company company = null;
		String vSQL = RQTCOMPANYBYID;

		company = vJdbcTemplate.queryForObject(vSQL, Company.class);

		return company;
	}

	public Company getCompanyByName(String name) {
		Company company = null;
		String vSQL = RQTCOMPANYBYNAME;

		company = vJdbcTemplate.queryForObject(vSQL, Company.class);

		return company;
	}

	public int nbCompany() {

		String vSQL = RQTNBCOMPANY;

		int nbCompany = vJdbcTemplate.queryForObject(vSQL, int.class);

		return nbCompany;
	}

//	public void delet(int company_id) {
//		try (Connection connection = CdbConnection.getInstance().getConnection();) {
//
//			try (PreparedStatement preparedStatement1 = connection.prepareStatement(RQTDELETECOMPUTER);
//					PreparedStatement preparedStatement2 = connection.prepareStatement(RQTDELETECOMPANY);) {
//				
//				//instruction 1 à 1
//				connection.setAutoCommit(false);
//				
//				preparedStatement1.setInt(1, company_id);
//				preparedStatement1.execute();
//
//				preparedStatement2.setInt(2,company_id);
//				preparedStatement2.execute();
//				
//				connection.commit();
//
//				
//			} catch (SQLException del) {
//				
//				if (connection != null) {
//					try {
//						logger.error(" error  Delete: " + del);
//						connection.rollback();
//					} catch (SQLException e) {
//						logger.error("MySQL error : " + e);
//					}
//				}
//			} finally {
//				try {
//					connection.setAutoCommit(true);
//				} catch (SQLException ero) {
//					logger.error("MySQL error : " + ero);
//				}
//			}
//
//		} catch (SQLException error) {
//			logger.error("MySQL error connection : " + error);
//			
//		}
//	}

}
