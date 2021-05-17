package persistance;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Type;

import mapper.MapperComputer;
import model.Computer;
import ui.VueComputer;

public class DaoComputer {

	private DaoComputer(){}
	private static DaoComputer INSTANCE = new DaoComputer();
	private String rqtSelectComputerAll="select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id limit ? offset ? ;";
	private String rqtInsert ="INSERT INTO computer( name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
	private String rqtSearch = "select computer.id,computer.name,computer.introduced,computer.discontinued,computer.company_id,company.name from computer  left join company on computer.company_id = company.id where computer.id = ? ; ";
	private String rqtUpdate = "update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?";
	private String rqtDeleteById = "delete from computer where id=?";
	private String rqtNbComputer ="SELECT COUNT(*) FROM computer ;";
	public static DaoComputer getInstance() {
		return INSTANCE;
	}
	
	
	public ArrayList<Computer> readDatabase(int i,int nbParPage) throws Exception {
		try {
			
			Connection connection = Database.getConnection();
			int limit = nbParPage;
			int offset = nbParPage * i;
			PreparedStatement preparedStatement = connection.prepareStatement(rqtSelectComputerAll);
			preparedStatement.setInt(1, limit);
			preparedStatement.setInt(2, offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Computer> db = MapperComputer.writeResultSet(resultSet);
			//Database.close();
			return (db);

		} catch (Exception e) {
			throw e;
		} finally {
			
		}
	}

	public void newComputer(Computer c) throws Exception {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(rqtInsert	);
			
			preparedStatement.setString(1, c.getName());
			
			Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(2, ts1);
			preparedStatement.setTimestamp(3, ts2);
			preparedStatement.setInt(4, c.getCompany_id().getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			Database.close();
		}
	}

	public ArrayList<Computer> searchComputer(int idComputer) throws Exception {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement(rqtSearch);
			preparedStatement.setInt(1,idComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			return MapperComputer.writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {
			Database.close();
		}
	}

	public void updateComputer(int idComputer, Computer c) throws Exception {

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
		Database.close();
	}

	public void deleteComputer(int idComputer) throws Exception {
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(rqtDeleteById);
		preparedStatement.setInt(1, idComputer);
		preparedStatement.executeUpdate();
		Database.close();

	}
	public int nbComputer()throws Exception{
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(rqtNbComputer);
		ResultSet resultSet  = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int id = Integer.parseInt (resultSet.getString(1));
			return id ;
		}
		return 0;
	}
	
}
