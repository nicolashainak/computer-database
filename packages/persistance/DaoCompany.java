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

	public static ArrayList<Company> readDatabase(int i ) throws Exception {
		try {

			Connection connection = Database.getConnection();
			int limit = 20;
			int offset = 20 * i;
			PreparedStatement preparedStatement = connection.prepareStatement("select * from company limit "+limit +" offset "+ offset);
			ResultSet resultSet = preparedStatement.executeQuery();
			return MapperCompany.writeResultSet(resultSet);

		} catch (Exception e) {
			throw e;
		} finally {

		}
	}
	public static int nbCompany() throws Exception  {
		Connection connection=Database.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM company ;");
		ResultSet resultSet  = preparedStatement.executeQuery();
		if (resultSet.next()) {
			int id = Integer.parseInt (resultSet.getString(1));
			return id ;
		}
		return 0;
	}
	public static int nbPageCompany () throws Exception {
		int nbCompany = DaoCompany.nbCompany();
		int nbPageCompany = (nbCompany - (nbCompany%20)) /20;
		return nbPageCompany;
	}

}
