package com.excilys.cdb.persistance;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.binding.dto.DtoComputerDbService;
import com.excilys.cdb.binding.mapper.MapperComputer;
import com.excilys.cdb.binding.mapper.MapperDtoComputerDbService;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public class DaoComputer {
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);

	private DaoComputer() {
	}

	private static DaoComputer instance = new DaoComputer();
	private final static String RQTSELECTCOMPUTERALL = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? ;";
	private final static String RQTINSERT = "INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private final static String RQTSEARCH = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id where computer.id = ? ; ";
	private final static String RQTUPDATE = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?";
	private final static String RQTDELETEBYID = "delete from computer where id=?";
	private final static String RQTNBCOMPUTER = "SELECT COUNT(*) FROM computer ;";
	private final static String RQTORDERBY = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? order by ? ;";
	
	
	
	public static DaoComputer getInstance() {
		return instance;
	}

	public List<Computer> getListComputer(Page page) {
		List<Computer> listComputer = new ArrayList<Computer>();

		try (Connection connection = CdbConnection.getConnection();) {// connection dans les parenthèses
			PreparedStatement preparedStatement = connection.prepareStatement(RQTSELECTCOMPUTERALL);

			preparedStatement.setInt(1, page.getNbComputerParPage());

			preparedStatement.setInt(2, page.getNbComputerParPage() * (page.getNumPage() - 1));
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputer = MapperComputer.writeResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listComputer;
	}


	public void newComputer(Computer computer) {
		DtoComputerDbService c = MapperDtoComputerDbService.mapperDtoToDbService(computer);
		try (Connection connection = CdbConnection.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTINSERT);

			preparedStatement.setString(1, c.getName());
			
			if (c.getIntroduced() != null) {
				preparedStatement.setDate(2, c.getIntroduced());
			} else {
				preparedStatement.setNull(2, 0);
			}
			if (c.getDiscontinued() != null) {
				preparedStatement.setDate(3, c.getDiscontinued());
			} else {
				preparedStatement.setNull(3, 0);
			}
			if (c.getCompany().getId() == 0) {
				preparedStatement.setNull(4, 0);
			} else {
				preparedStatement.setInt(4, c.getCompany().getId());
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

			logger.error("MySQL error : " + e);
		}

	}
	public List<Computer> orderBy(Page page, String collonne ) {
		List<Computer> listComputer = new ArrayList<Computer>();
		try (Connection connection = CdbConnection.getConnection();) {
			PreparedStatement preparedStatement = connection.prepareStatement(RQTORDERBY);
			preparedStatement.setInt(1, page.getNbComputerParPage());

			preparedStatement.setInt(2, page.getNbComputerParPage() * (page.getNumPage() - 1));
			preparedStatement.setString(3,collonne);
			ResultSet resultSet = preparedStatement.executeQuery();
			listComputer = MapperComputer.writeResultSet(resultSet);
		}	 catch (SQLException e) {

			logger.error("MySQL error : " + e);
		}
		return listComputer;
	}
	
	/////// Changer en return Computer --> ajouter methode mapper pour 1 seul ordi
	/////// cf company
	public ArrayList<Computer> searchComputer(int idComputer) {
		ArrayList<Computer> db = new ArrayList<Computer>();
		try (Connection connection = CdbConnection.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTSEARCH);
			preparedStatement.setInt(1, idComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			db = MapperComputer.writeResultSet(resultSet);

		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
		return db;
	}

	public void updateComputer(int idComputer, Computer c) {
		try (Connection connection = CdbConnection.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTUPDATE);
			preparedStatement.setString(1, c.getName());
			Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(2, ts1);
			preparedStatement.setTimestamp(3, ts2);
			preparedStatement.setInt(4, c.getCompany_id().getId());
			preparedStatement.setInt(5, idComputer);
			preparedStatement.executeUpdate();
			// Database.close();
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
	}

	public void deleteComputer(int idComputer) {
		try (Connection connection = CdbConnection.getConnection();) {

			PreparedStatement preparedStatement = connection.prepareStatement(RQTDELETEBYID);
			preparedStatement.setInt(1, idComputer);
			preparedStatement.executeUpdate();
			// Database.close();
		} catch (SQLException e) {
			logger.error("MySQL error : " + e);
		}
	}

	public int nbComputer() {
		
		try (Connection connection = CdbConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(RQTNBCOMPUTER);) {

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
	
	

}
