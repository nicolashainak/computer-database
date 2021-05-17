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

	public static ArrayList<Computer> readDatabase(int i,int nbParPage) throws Exception {
		try {
			
			Connection connection = Database.getConnection();
			int limit = nbParPage;
			int offset = nbParPage * i;
			PreparedStatement preparedStatement = connection.prepareStatement("select * from computer limit "+limit +" offset "+ offset);
			ResultSet resultSet = preparedStatement.executeQuery();
//			preparedStatement.setInt(1, 20);
//			preparedStatement.setInt(2, 20);
			
			return (MapperComputer.writeResultSet(resultSet));

		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	public static void newComputer(Computer c) throws Exception {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO computer( name,introduced,discontinued,company_id)" + "VALUES(?,?,?,?);");
			
			preparedStatement.setString(1, c.getName());
			
			Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
			Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
			preparedStatement.setTimestamp(2, ts1);
			preparedStatement.setTimestamp(3, ts2);
			preparedStatement.setInt(4, c.getCompany_id());
			preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
			System.out
					.println("INSERT INTO computer( name,introduced,discontinued,company_id)" + "VALUES(" + c.getName()
							+ "," + c.getIntroduced() + "," + c.getDiscontinued() + "," + c.getCompany_id() + ");");
		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	public static ArrayList<Computer> searchComputer(int idComputer) throws Exception {
		try {
			Connection connection = Database.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from computer where id =" + idComputer);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("search computer n° : " + idComputer);
			return MapperComputer.writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {

		}
	}

	public static void updateComputer(int idComputer, Computer c) throws Exception {

		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"update computer set name = ?,introduced = ? , discontinued = ?, company_id = ? where id = ?");
		preparedStatement.setString(1, c.getName());
		Timestamp ts1 = new Timestamp(Date.valueOf(c.getIntroduced()).getTime());
		Timestamp ts2 = new Timestamp(Date.valueOf(c.getDiscontinued()).getTime());
		preparedStatement.setTimestamp(2, ts1);
		preparedStatement.setTimestamp(3, ts2);
		preparedStatement.setInt(4, c.getCompany_id());
		preparedStatement.setInt(5, idComputer);
		preparedStatement.executeUpdate();

	}

	public static void deleteComputer(int idComputer) throws Exception {
		Connection connection = Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("delete from computer where id=?");
		preparedStatement.setInt(1, idComputer);
		preparedStatement.executeUpdate();

	}
	public static int nbComputer()throws Exception{
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM computer ;");
		ResultSet resultSet  = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int id = Integer.parseInt (resultSet.getString(1));
			return id ;
		}
		return 0;
	}
}
