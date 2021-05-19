package com.excilys.cdb.persistance;


import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.excilys.cdb.mapper.MapperComputer;
import com.excilys.cdb.model.Computer;


public class DaoComputer {
	Logger logger = LoggerFactory.getLogger(DaoComputer.class);
	private DaoComputer() {
	}

	private static DaoComputer INSTANCE = new DaoComputer();
	private String rqtSelectComputerAll = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? ;";
	private String rqtInsert = "INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private String rqtSearch = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id where computer.id = ? ; ";
	private String rqtUpdate = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?";
	private String rqtDeleteById = "delete from computer where id=?";
	private String rqtNbComputer = "SELECT COUNT(*) FROM computer ;";

	public static DaoComputer getInstance() {
		return INSTANCE;
	}

	public ArrayList<Computer> readDatabase(int i, int nbParPage) {
		ArrayList<Computer> db = new ArrayList<Computer>();
		
		try {
			Connection connection = Database.getConnection();
			int limit = nbParPage;
			int offset = nbParPage * i;
			PreparedStatement preparedStatement = connection.prepareStatement(rqtSelectComputerAll);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			db = MapperComputer.writeResultSet(resultSet);
			//Database.close();
		} catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
		
		return db ;
	}

	public void newComputer(Computer c)  {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtInsert);

			preparedStatement.setString(1, c.getName());
			if (c.getIntroduced()!=null) {
				Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
				preparedStatement.setTimestamp(2, ts1);
			}else {
				preparedStatement.setNull(2, 0);
			}
			if (c.getDiscontinued()!=null) {
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(3, ts2);
			}else {
				preparedStatement.setNull(3, 0);
			}
			preparedStatement.setInt(4, c.getCompany_id().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
	
	}
	///////  Changer en return Computer --> ajouter methode mapper pour 1 seul ordi cf company 
	public ArrayList<Computer> searchComputer(int idComputer) {
		ArrayList<Computer> db = new ArrayList<Computer>();
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtSearch);
			preparedStatement.setInt(1, idComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			db = MapperComputer.writeResultSet(resultSet);

		}catch (SQLException e)  {
				logger.error("MySQL error : " + e);
			} 
		return db;
	}

	public void updateComputer(int idComputer, Computer c)  {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtUpdate);
			preparedStatement.setString(1, c.getName());
			Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(2, ts1);
			preparedStatement.setTimestamp(3, ts2);
			preparedStatement.setInt(4, c.getCompany_id().getId());
			preparedStatement.setInt(5, idComputer);
			preparedStatement.executeUpdate();
			//Database.close();
		}catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
	}
	public void deleteComputer(int idComputer) {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtDeleteById);
			preparedStatement.setInt(1, idComputer);
			preparedStatement.executeUpdate();
			//Database.close();
		}catch (SQLException e)  {
			logger.error("MySQL error : " + e);
		} 
	}

	public int nbComputer() {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtNbComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString(1));
				return id;
			}
			
		}catch (SQLException e)  {
				logger.error("MySQL error : " + e);
			} 
			
			return 0;
	}

}
